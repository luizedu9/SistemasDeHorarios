package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Curso;
import service.CursoService;
import util.NegocioException;

@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {

	private CursoService service = new CursoService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Curso obj = null;
		if ((value != null) && (!value.isEmpty()))
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
		if (value != null) {
			Curso aux = (Curso) value;
			Integer cod = aux.getCodigo();
			return cod.toString();
		} else
			return null;
	}

}
