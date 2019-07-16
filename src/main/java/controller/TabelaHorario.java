package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modelo.Horario;

public class TabelaHorario {

	private List<CelulaHorario> celulas;

	public TabelaHorario(List<Horario> listaHorarios) {
		this.setCelulasWithHorarios(listaHorarios);
	}
	
	public TabelaHorario() {
		super();
	}

	private CelulaHorario insertInfoCelula(CelulaHorario celula, String dia, String info) {
		switch(dia) {
			case "Segunda": celula.addInfoSegunda(info); break;
			case "Terça": celula.addInfoTerca(info); break;
			case "Quarta": celula.addInfoQuarta(info); break;
			case "Quinta": celula.addInfoQuinta(info); break;
			case "Sexta": celula.addInfoSexta(info); break;
			case "Sabado": celula.addInfoSabado(info); break;
		}
		return celula;
	}
	
	private String montaStringInfo(Horario horario) {
		return horario.getTurma().toString() + " - " + horario.getSala().toString();
	}
		
	private CelulaHorario searchHorario(String horario) {
		for(CelulaHorario celula: this.celulas) {
			if(celula.getHorario().equals(horario)) {
				return celula;
			}
		}
		return null;
	}
	
	private String montaStringHorario(Horario horario) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String strInicio = format.format(horario.getInicio());
		String strFim = format.format(horario.getFim());
		return strInicio + " - " + strFim;
	}
	

	public List<CelulaHorario> getCelulas() {
		return celulas;
	}

	public void setCelulas(List<CelulaHorario> celulas) {
		this.celulas = celulas;
	}
	
	public void setCelulasWithHorarios(List<Horario> listaHorarios) {
		this.celulas = new ArrayList<>();
		
		CelulaHorario celula;
		String strHorario = "";
		String info = "";
		String dia = "";
		for(Horario horario: listaHorarios) {
			strHorario = this.montaStringHorario(horario);
			celula = this.searchHorario(strHorario);
			if(celula == null) {
				celula = new CelulaHorario();
				celula.setHorario(strHorario);
				this.celulas.add(celula);
			}
			info = this.montaStringInfo(horario);
			dia = horario.getDia();
			celula = insertInfoCelula(celula, dia, info);
		}
	}

	@Override
	public String toString() {
		return "TabelaHorario [celulas=" + celulas + "]";
	}

}
