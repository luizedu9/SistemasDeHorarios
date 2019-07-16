package modelo;

public enum TipoDisciplina {

	OBRIGATORIA("Disciplina obrigatória"),
    OPTATIVA("Disciplina optativa"),
    EXTRACURRICULAR("Atividade extracurricular");
	
	private String descricao;
	
	private TipoDisciplina(String descricaco){
		this.descricao = descricaco;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
}
