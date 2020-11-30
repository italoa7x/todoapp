package br.edu.ifpb.bean;

import java.util.ArrayList;
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
import br.edu.ifpb.ejb.TarefaEjb;
import br.edu.ifpb.ejb.UsuarioEjb;
import br.edu.ifpb.util.AdicionarMensagem;
import br.edu.ifpb.util.ExceptionSistema;
import br.edu.ifpb.util.UsuarioPrincipal;

@Named
@RequestScoped
public class TarefaBean {

	@EJB
	private TarefaEjb tarefaService;
	@EJB
	private UsuarioEjb usuarioService;

	private String tarefaId;

	@Inject
	private SecurityContext securityContext;

	private Tarefa tarefa = new Tarefa();

	private List<Tarefa> tarefasDoUsuarioLogado;

	public TarefaBean() {
	}

	@PostConstruct
	public void init() {
		System.out.println("TarefaBean criado.");
		Usuario usuarioLogado = getUsuarioLogado();
		if (usuarioLogado == null) {
			tarefasDoUsuarioLogado = new ArrayList<Tarefa>();
		}
		tarefasDoUsuarioLogado = usuarioService.buscarTarefasUsuario(usuarioLogado).size() == 0
				|| usuarioService.buscarTarefasUsuario(usuarioLogado) == null ? new ArrayList<Tarefa>()
						: usuarioService.buscarTarefasUsuario(usuarioLogado);
	}

	public Usuario getUsuarioLogado() {
		Usuario nomeUsuarioLogado = null;
		Optional<UsuarioPrincipal> usuarioLogado = securityContext.getPrincipalsByType(UsuarioPrincipal.class).stream()
				.findFirst();
		if (usuarioLogado.isPresent()) {
			nomeUsuarioLogado = usuarioLogado.get().getUsuario();
		}

		return nomeUsuarioLogado;
	}

	public String salvar() {
		try {
			Usuario usuarioLogado = getUsuarioLogado();
			System.out.println("TarefaBean - salvar tarefa do usuario: " + usuarioLogado.getNome());
			tarefa.setUsuario(usuarioLogado);
			Tarefa result = this.tarefaService.salvar(tarefa);

			if (result != null) {
				adicionarMensagem("tarefa salva");
				tarefasDoUsuarioLogado.add(result);
				return "/perfilUsuario/home.xhtml?faces-redirect=true";
			} else {
				adicionarMensagem("erro ao salvar tarefa");
			}

		} catch (ExceptionSistema e) {
			AdicionarMensagem.add("Erro", e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return "";
	}

	public String atualizarTarefa() {
		try {
			tarefaService.atualizarTarefa(tarefa);
			return "/perfilUsuario/home.xhtml?faces-redirect=true";
		} catch (ExceptionSistema e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String excluirTarefa(Tarefa t) {
		System.out.println("clicou em excluir a tarefa" + t.getNome());
		tarefaService.excluirTarefa(t);
		tarefasDoUsuarioLogado.remove(t);

		return "/perfilUsuario/home.xhtml?faces-redirect=true";
	}

	public void finalizarTarefa(Tarefa t) {
		tarefaService.finalizarTarefa(t);
	}

	public String getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(String tarefaId) {
		this.tarefaId = tarefaId;
	}

	public List<Tarefa> getTarefasDoUsuarioLogado() {
		return tarefasDoUsuarioLogado;
	}

	public void setTarefasDoUsuarioLogado(List<Tarefa> tarefasDoUsuarioLogado) {
		this.tarefasDoUsuarioLogado = tarefasDoUsuarioLogado;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	private void adicionarMensagem(String mensagem) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(null, mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

}