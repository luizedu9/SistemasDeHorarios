package modelo;

public enum SolicitacaoStatus {
	
	REGISTRADA("Solicitação Registrada"),
	SOLICITADA("Solicitação Enviada"),
	ACEITA("Solicitação Aceita"),
	RECUSADA("Solicitação Recusada");
	
	private String descricao;
	private SolicitacaoStatus(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}