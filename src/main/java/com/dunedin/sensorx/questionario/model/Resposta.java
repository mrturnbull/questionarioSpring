package com.dunedin.sensorx.questionario.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="UsuarioResposta")
@IdClass(RespostaPK.class)
@NamedQueries({
	@NamedQuery(name = "Resposta.getMaxPerguntaId", query = "select max(r.perguntaId) AS maxPerguntaId from Resposta r where r.usuarioId = :usuarioId"),
	@NamedQuery(name = "Resposta.findById", query = "from Resposta r where r.usuarioId = :usuarioId and r.perguntaId = :perguntaId and r.respostaId = :respostaId"),
	@NamedQuery(name = "Resposta.findByPergunta", query = "from Resposta r where r.usuarioId = :usuarioId and r.perguntaId = :perguntaId")
})
public class Resposta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="USUARIOID", nullable=false)
	private int usuarioId;

	@Id
	@Column(name="PERGUNTAID", nullable=false)
	private int  perguntaId;

	@Id
	@Column(name="RESPOSTAID", nullable=false)
	private int respostaId;

	@Column(name="RESPOSTA", length=5000, nullable=false)
	private String resposta;

	@Column(name="DATAINICIO", nullable=true)
	private String dataInicio;

	@Column(name="DATAFIM", nullable=true)
	private String dataFim;

	public Resposta(){
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getPerguntaId() {
		return perguntaId;
	}

	public void setPerguntaId(int perguntaId) {
		this.perguntaId = perguntaId;
	}

	public int getRespostaId() {
		return respostaId;
	}

	public void setRespostaId(int respostaId) {
		this.respostaId = respostaId;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

}
