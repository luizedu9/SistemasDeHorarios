package modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DIRECAO_ENSINO")
public class DirecaoEnsino extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	public DirecaoEnsino() {
		
	}

}
