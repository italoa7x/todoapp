package br.edu.ifpb.ejb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.domain.Tarefa;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.domain.enun.StatusTarefa;
import br.edu.ifpb.util.ExceptionSistema;

/*
 * Autor: Italo Alves <contato.italo1020@gmail.com>
 *
 **/

@Stateless
public class TarefaEjb implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public Tarefa salvar(Tarefa t) throws ExceptionSistema {
		try {
			entityManager.persist(t);
			return t;
		} catch (Exception e) {
			throw new ExceptionSistema("Erro ao salvar tarefa\n" + e);
		}
	}

	public Tarefa buscaPeloId(Integer id) {
		return entityManager.find(Tarefa.class, id);
	}

	public void adicionarTarefaParaUsuario(Usuario u, Tarefa t) {
		System.out.println("TarefaEjb - adicionaTarefaParaUsuario.");
		u = entityManager.merge(u);
		u.setTarefas(Arrays.asList(t));

	}

	public Tarefa atualizarTarefa(Tarefa t) throws ExceptionSistema {
		try {
			entityManager.merge(t);
			return t;
		} catch (Exception e) {
			throw new ExceptionSistema("Erro ao atualizar dados da tarefa\n" + e);
		}
	}

	public void excluirTarefa(Tarefa t) {

		System.out.println("TarefaEjb - excluirTarefa com id: " + t);
		entityManager.remove(entityManager.merge(t));
	}

	@PreDestroy
	public void fechaEntityManger() {
		entityManager.close();
	}

	public boolean finalizarTarefa(Tarefa tarefa) {
		tarefa.setStatus(StatusTarefa.FINALIZADA);
		tarefa.setDtFinalizacao(new Date());
		entityManager.merge(tarefa);
		return true;
	}
}
