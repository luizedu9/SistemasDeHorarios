package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Coordenador;
import service.CoordenadorService;
import util.NegocioException;

@FacesConverter(forClass=Coordenador.class)
public class CoordenadorConverter implements Converter{

	private CoordenadorService service = new CoordenadorService();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Coordenador obj = null;
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
			Coordenador aux = (Coordenador)value;
			Integer cod = aux.getCodigo();
			return  cod.toString();
		}
		else 
		   return null;
	}	
	
}
