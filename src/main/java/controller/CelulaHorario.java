package controller;

import java.util.ArrayList;
import java.util.List;

public class CelulaHorario {
	
	private String horario;
	private List<String> segunda;
	private List<String> terca;
	private List<String> quarta;
	private List<String> quinta;
	private List<String> sexta;
	private List<String> sabado;
	
	public CelulaHorario(String horario, List<String> segunda, List<String> terca, List<String> quarta,
			List<String> quinta, List<String> sexta, List<String> sabado) {
		super();
		this.horario = horario;
		this.segunda = segunda;
		this.terca = terca;
		this.quarta = quarta;
		this.quinta = quinta;
		this.sexta = sexta;
		this.sabado = sabado;
	}

	public CelulaHorario() {
		super();
		this.segunda = new ArrayList<>();
		this.terca = new ArrayList<>();
		this.quarta = new ArrayList<>();
		this.quinta = new ArrayList<>();
		this.sexta = new ArrayList<>();
		this.sabado = new ArrayList<>();
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public List<String> getSegunda() {
		return segunda;
	}

	public void setSegunda(List<String> segunda) {
		this.segunda = segunda;
	}

	public List<String> getTerca() {
		return terca;
	}

	public void setTerca(List<String> terca) {
		this.terca = terca;
	}

	public List<String> getQuarta() {
		return quarta;
	}

	public void setQuarta(List<String> quarta) {
		this.quarta = quarta;
	}

	public List<String> getQuinta() {
		return quinta;
	}

	public void setQuinta(List<String> quinta) {
		this.quinta = quinta;
	}

	public List<String> getSexta() {
		return sexta;
	}

	public void setSexta(List<String> sexta) {
		this.sexta = sexta;
	}

	public List<String> getSabado() {
		return sabado;
	}

	public void setSabado(List<String> sabado) {
		this.sabado = sabado;
	}

	public void addInfoSegunda(String info) {
		this.segunda = this.addInfoLista(this.segunda, info);
	}
	
	public void addInfoTerca(String info) {
		this.terca = this.addInfoLista(this.terca, info);
	}
	
	public void addInfoQuarta(String info) {
		this.quarta = this.addInfoLista(this.quarta, info);
	}
	
	public void addInfoQuinta(String info) {
		this.quinta = this.addInfoLista(this.quinta, info);
	}
	
	public void addInfoSexta(String info) {
		this.sexta = this.addInfoLista(this.sexta, info);
	}
	
	public void addInfoSabado(String info) {
		this.sabado = this.addInfoLista(this.sabado, info);
	}
	
	private List<String> addInfoLista(List<String> lista, String info) {
		lista.add(info);
		return lista;
	}

	@Override
	public String toString() {
		return "CelulaHorario [horario=" + horario + ", segunda=" + segunda + ", terca=" + terca + ", quarta=" + quarta
				+ ", quinta=" + quinta + ", sexta=" + sexta + ", sabado=" + sabado + "]";
	}
	
}
