package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="disciplina")
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	private String nome;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipo;
	private Integer carga_horaria;
	@Enumerated(EnumType.STRING)
	private TipoSala tipo_sala_requerida;
	private Integer ano;
	
	@ManyToMany(cascade = { 
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	})
	@JoinTable(name = "disciplina_curso",
	        joinColumns = @JoinColumn(name = "codDisciplina"),
	        inverseJoinColumns = @JoinColumn(name = "codCurso")
    )
	private List<Curso> cursos;
	
	@OneToMany(mappedBy="disciplina", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Turma> turmas;
	
	public Disciplina () {
		cursos = new ArrayList<Curso>();
		turmas = new ArrayList<Turma>();
	}

	public Disciplina(Integer codigo, String nome, TipoDisciplina tipo, Integer carga_horaria, TipoSala tipo_sala_requerida,
			Integer ano, List<Curso> cursos, List<Turma> turmas) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.tipo = tipo;
		this.carga_horaria = carga_horaria;
		this.tipo_sala_requerida = tipo_sala_requerida;
		this.ano = ano;
		this.cursos = cursos;
		this.turmas = turmas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDisciplina getTipo() {
		return tipo;
	}

	public void setTipo(TipoDisciplina tipo) {
		this.tipo = tipo;
	}

	public Integer getCarga_horaria() {
		return carga_horaria;
	}

	public void setCarga_horaria(Integer carga_horaria) {
		this.carga_horaria = carga_horaria;
	}

	public TipoSala getTipo_sala_requerida() {
		return tipo_sala_requerida;
	}

	public void setTipo_sala_requerida(TipoSala tipo_sala_requerida) {
		this.tipo_sala_requerida = tipo_sala_requerida;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public void addTurma(Turma turma) {
		if(!this.turmas.contains(turma)) {
			turma.setDisciplina(this);
			this.turmas.add(turma);
		}
	}
	
	public void removeTurma(Turma turma) {
		if(this.turmas.contains(turma)) {
			turma.setDisciplina(null);
			this.turmas.remove(turma);
		}
	}
	
	public void addCurso(Curso c) {
		if(!this.cursos.contains(c)) {
			this.cursos.add(c);
		}
	}
	
	public void removeCurso(Curso c) {
		if(this.cursos.contains(c)) {
			this.cursos.remove(c);
		}
	}

	@Override
	public String toString() {
		return nome;
	}

}
