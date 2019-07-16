package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Professor;
import service.ProfessorService;

@FacesConverter(forClass=Professor.class)
public class ProfessorConverter implements Converter{

	private ProfessorService service = new ProfessorService();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor obj = null;
		if ((value != null) && (!value.isEmpty()) )
			obj = service.buscarPorCodigo(Integer.parseInt(value));		
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Professor aux = (Professor)value;
			Integer cod = aux.getCodigo();
			return  cod.toString();
		}
		else 
		   return null;
	}	
	
}
