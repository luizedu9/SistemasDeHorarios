package modelo;

public enum TipoSala {

	PRESENCIAL("Presencial"),
	LABORATORIO("Laboratório"),
	AUDITORIO("Auditório");
	
	private String descricao;
	
	private TipoSala(String descricaco){
		this.descricao = descricaco;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
}
