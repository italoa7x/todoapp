package br.edu.ifpb.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AdicionarMensagem {

	public static void add(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(tipoErro, sumario, detalhe));
	}
}
