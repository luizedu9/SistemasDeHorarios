package modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("COORDENADOR")
public class Coordenador extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="codCurso")
	private Curso curso;
	
	public Coordenador() {
		
	}

	public Coordenador(Curso curso) {
		super();
		this.curso = curso;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
