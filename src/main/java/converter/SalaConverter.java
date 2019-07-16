package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Sala;
import service.SalaService;
import util.NegocioException;

@FacesConverter("salaConverter")
public class SalaConverter implements Converter {

	private SalaService service = new SalaService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Sala obj = null;
		if ((value != null) && (!value.isEmpty()))
			try {
				obj = service.buscarPorCodigo(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NegocioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Sala aux = (Sala) value;
			Integer cod = aux.getCodigo();
			return cod.toString();
		} else
			return null;
	}

}
