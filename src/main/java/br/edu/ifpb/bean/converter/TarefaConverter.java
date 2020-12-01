package br.edu.ifpb.bean.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.edu.ifpb.domain.Tarefa;
import br.edu.ifpb.ejb.TarefaEjb;

@FacesConverter(managed = true, forClass = Tarefa.class)
public class TarefaConverter implements Converter<Tarefa> {

	@EJB
	private TarefaEjb servico;
	// recebe um ID e retorna um objeto tarefa, é utilizado na pagina de home quando vai editar uma tarefa
	@Override
	public Tarefa getAsObject(FacesContext context, UIComponent component, String id) {
		if(id.equals("") || id == null)
			return null;
		
		System.out.println("TarefaConverter (String -> Tarefa) - id: "+id);
		
		Integer idTarefaInt = Integer.parseInt(id);

		Tarefa tarefa = servico.buscaPeloId(idTarefaInt);
		if (tarefa == null) {
			return null;
		}
		return tarefa;
	}
	// recebe um objeto tarefa e retorna seu id
	@Override
	public String getAsString(FacesContext context, UIComponent component, Tarefa tarefa) {
		System.out.println("TarefaConverter (Tarefa -> String) - id: "+tarefa);

		if (tarefa == null) {
			return "";
		}
		return String.valueOf(tarefa.getId());
	}

}
