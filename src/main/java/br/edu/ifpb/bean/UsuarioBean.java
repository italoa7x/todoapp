package br.edu.ifpb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

import br.edu.ifpb.domain.Tarefa;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.ejb.UsuarioEjb;
import br.edu.ifpb.util.AdicionarMensagem;
import br.edu.ifpb.util.ExceptionSistema;
import br.edu.ifpb.util.UsuarioPrincipal;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioEjb usuarioService;

	@Inject
	private SecurityContext securityContext;

	private Usuario usuario = new Usuario();

	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	public String salvarUsuario() {

		try {
			Usuario result = this.usuarioService.salvar(usuario);
			adicionarMensagem("cadastro finalizado");
			if (result != null) {
				return "/index.xhtml?faces-redirect=true";
			} else {
				adicionarMensagem("erro no cadastro. Verifique os dados");
			}

		} catch (ExceptionSistema e) {
			adicionarMensagem("erro no cadastro. Verifique os dados");
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private void adicionarMensagem(String mensagem) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(null, null, mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

}
