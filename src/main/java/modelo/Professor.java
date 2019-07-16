package modelo;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="professor")
@DiscriminatorValue("PROFESSOR")
public class Professor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Professor() {
		
	}

}
