package br.edu.ifpb.ejb.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.ejb.UsuarioEjb;
import br.edu.ifpb.util.ExceptionSistema;
import br.edu.ifpb.util.UsuarioPrincipal;

//Custom Identity Store
@ApplicationScoped
public class ServicoUsuarioIdentityStore implements IdentityStore {

	@Inject
	private UsuarioEjb servicoUsuarios;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		System.out.println("ServicoUsuarioIdentityStore.");
		
		UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
		CredentialValidationResult resultadoValidacao = CredentialValidationResult.INVALID_RESULT;
		
		String login_str = login.getCaller();
		String senha = login.getPasswordAsString();
		
		Usuario usuarioRecuperado = null;
		try {
			usuarioRecuperado = servicoUsuarios.logar(login_str, senha);
		} catch (ExceptionSistema e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ServicoUsuarioIdentityStore - Usuario Recuperado: "+usuarioRecuperado);
		
		if (usuarioRecuperado!=null) {
			resultadoValidacao = new CredentialValidationResult(new UsuarioPrincipal(usuarioRecuperado),usuarioRecuperado.getPapeisString());
		}

		System.out.println("Resultado Validacao: "+resultadoValidacao.getStatus());
		return resultadoValidacao;
	}

	
}
