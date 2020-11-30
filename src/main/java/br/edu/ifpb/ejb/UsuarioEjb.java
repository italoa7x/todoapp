package br.edu.ifpb.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.domain.Tarefa;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.ejb.service.CryptSenhaService;
import br.edu.ifpb.util.ExceptionSistema;

/*
 * Autor: Italo Alves <contato.italo1020@gmail.com>
 *
 **/
@Stateless
public class UsuarioEjb {
	@PersistenceContext
	private EntityManager entityManager;

	public Usuario salvar(Usuario u) throws ExceptionSistema {
		String senhaCriptografada = CryptSenhaService.criptografar(u.getSenha());
		u.setSenha(senhaCriptografada);
		try {
			if (u.getId() == null) {
				entityManager.persist(u);
			} else {
				entityManager.merge(u);
			}
			return u;
		} catch (Exception e) {
			throw new ExceptionSistema(e.getMessage(), e.getCause());
		}
	}

	public Usuario logar(String login, String senha) throws ExceptionSistema {
		try {
			Usuario usuarioEncontrado = (Usuario) entityManager
					.createQuery("SELECT u FROM Usuario u WHERE u.login = :login").setParameter("login", login)
					.getSingleResult();

			if (usuarioEncontrado != null && CryptSenhaService.compararSenha(senha, usuarioEncontrado.getSenha())) {
				return usuarioEncontrado;
			}

			return null;
		} catch (Exception e) {
			throw new ExceptionSistema(e.getMessage(), e.getCause());
		}
	}
	
	public List<Tarefa> buscarTarefasUsuario(Usuario usuarioLogado) {
		Usuario usuarioRecuperado = entityManager.find(Usuario.class,usuarioLogado.getId());
		return usuarioRecuperado.getTarefas();
	}

}
