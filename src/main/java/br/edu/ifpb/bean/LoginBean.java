package br.edu.ifpb.bean;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.util.UsuarioPrincipal;

@Named
@RequestScoped
public class LoginBean {

	private Usuario usuario;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ExternalContext externalContext;

	@Inject
	private SecurityContext securityContext;

	public LoginBean() {
		usuario = new Usuario();
	}
	// realiza o login no sistema
	public String login() {
		externalContext.getFlash().setKeepMessages(true);
		AuthenticationStatus status = executaAutenticacao();
		switch (status) {
		case SEND_CONTINUE:
			if (securityContext.getCallerPrincipal() != null) {
				facesContext.responseComplete();
			} else {
				adicionarMensagem("ocorreu um erro no login", "erro");
			}
			break;
		case SEND_FAILURE:
			adicionarMensagem("erro no login", "error");
			break;
		case SUCCESS:
			adicionarMensagem("login efetuado. Bem vindo (a)", "info");
			return "/perfilUsuario/home.xhtml?faces-redirect=true";
		case NOT_DONE:
			// NADA
			break;
		}
		return null;
	}

	// faz logout no sistema
	public String logout() {
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		try {
			request.logout();
			return "/index.xhtml?faces-redirect=true";
		} catch (ServletException e) {
			adicionarMensagem("erro ao realizar logout", "error");
		}
		adicionarMensagem("logout realizado", "info");
		request.getSession().invalidate();
		return null;

	}
	// executa a autenticaçao para o usuario
	private AuthenticationStatus executaAutenticacao() {
		return securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
				(HttpServletResponse) externalContext.getResponse(), AuthenticationParameters.withParams()
						.credential(new UsernamePasswordCredential(usuario.getLogin(), usuario.getSenha())));
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// busca na sessão o usuario logado
	public Usuario getUsuarioLogado() {
		Usuario nomeUsuarioLogado = null;
		Optional<UsuarioPrincipal> usuarioLogado = securityContext.getPrincipalsByType(UsuarioPrincipal.class).stream()
				.findFirst();
		if (usuarioLogado.isPresent()) {
			nomeUsuarioLogado = usuarioLogado.get().getUsuario();
		}

		return nomeUsuarioLogado;
	}
	// adiciona mensagens na tela
	private void adicionarMensagem(String mensagem, String alerta) {
		Severity alert = FacesMessage.SEVERITY_INFO;

		if (!alerta.equals("info")) {
			alert = FacesMessage.SEVERITY_ERROR;
		}
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(alert, null, mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

}
