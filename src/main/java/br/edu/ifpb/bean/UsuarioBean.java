package br.edu.ifpb.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.ejb.UsuarioEjb;
import br.edu.ifpb.util.ExceptionSistema;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioEjb usuarioService;

	private Usuario usuario = new Usuario();
	
	@Inject
	private FacesContext context;

	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	// salva um novo usuario
	public String salvarUsuario() {

		try {
			Usuario result = this.usuarioService.salvar(usuario);
			System.out.println("usuario salvo " + result.getNome());
			if (result != null) {
				adicionarMensagem("cadastro finalizado");
				return "/index.xhtml?faces-redirect=true";
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

	// adiciona mensagens na tela
	private void adicionarMensagem(String mensagem) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

}
