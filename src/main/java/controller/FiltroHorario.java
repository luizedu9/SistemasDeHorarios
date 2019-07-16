package controller;

import modelo.Curso;
import modelo.Professor;
import modelo.Sala;
import modelo.Turma;

public class FiltroHorario {
	private Curso curso;
	private Integer ano;
	private Integer semestre;
	private String nomePeriodo;
	private Sala sala;
	private Turma turma;
	private Professor professor;
	
	public FiltroHorario(Curso curso, Integer ano, Integer semestre, String nomePeriodo, Sala sala, Turma turma,
			Professor professor) {
		super();
		this.curso = curso;
		this.ano = ano;
		this.semestre = semestre;
		this.nomePeriodo = nomePeriodo;
		this.sala = sala;
		this.turma = turma;
		this.professor = professor;
	}
	
	public FiltroHorario() {
		super();
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Integer getAno() {
		return ano;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public Integer getSemestre() {
		return semestre;
	}
	
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	
	public String getNomePeriodo() {
		return nomePeriodo;
	}
	
	public void setNomePeriodo(String nomePeriodo) {
		this.nomePeriodo = nomePeriodo;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public Turma getTurma() {
		return turma;
	}
	
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
}
