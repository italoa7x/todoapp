package br.edu.ifpb.util;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(
				loginPage = "/index.xhtml",
				errorPage = "/index.xhtml"
				)
		)
//@DatabaseIdentityStoreDefinition(
//		dataSourceLookup = "java:/livrariaDS",
//		callerQuery = "select senha from livraria.Usuario where email = ?",
//		groupsQuery = "select papeis from livraria.Usuario_papeis where Usuario_email = ?"//,
//		//hashAlgorithm = Pbkdf2PasswordHash.class
//		)
@ApplicationScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class JSF2_3Ativator {

}
