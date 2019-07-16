package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Solicitacao;
import service.SolicitacaoService;
import util.NegocioException;

@FacesConverter(forClass=Solicitacao.class)
public class SolicitacaoConverter implements Converter{

	private SolicitacaoService service = new SolicitacaoService();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Solicitacao obj = null;
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
			Solicitacao aux = (Solicitacao)value;
			Integer cod = aux.getCodigo();
			return  cod.toString();
		}
		else 
		   return null;
	}	
	
}
