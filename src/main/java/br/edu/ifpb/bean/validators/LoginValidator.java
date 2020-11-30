package br.edu.ifpb.bean.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.ifpb.domain.Usuario;

@FacesValidator(value = "loginValidator", managed = true)
public class LoginValidator implements Validator<Usuario> {

	@Override
	public void validate(FacesContext context, UIComponent component, Usuario usuario) throws ValidatorException {
		if (usuario.getLogin() == null) {
			throw new ValidatorException(new FacesMessage("Login inválido"));
		}

	}

}
