package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Disciplina;
import service.DisciplinaService;
import util.NegocioException;

@FacesConverter(forClass=Disciplina.class)
public class DisciplinaConverter implements Converter{

	private DisciplinaService service = new DisciplinaService();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Disciplina obj = null;
		if ((value != null) && (!value.isEmpty()) )
			try {
				obj = service.buscarPorCodigo(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NegocioException e) {
				e.printStackTrace();
			}		
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Disciplina aux = (Disciplina)value;
			Integer cod = aux.getCodigo();
			return  cod.toString();
		}
		else 
		   return null;
	}	
	
}
