package com.dunedin.sensorx.questionario.model;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@IdClass(AlternativaPK.class)
@Table(name="PerguntaAlternativa")
@NamedQueries({
	@NamedQuery(name = "Alternativa.findMaxId", query = "SELECT MAX(a.id) FROM Alternativa a WHERE a.perguntaId = :perguntaId"),
    @NamedQuery(name = "Alternativa.findAllPerPerguntaId", query = "SELECT a FROM Alternativa a WHERE a.perguntaId = :perguntaId ORDER BY a.id"),
    @NamedQuery(name = "Alternativa.findAlternativaPerId", query = "SELECT a FROM Alternativa a WHERE a.perguntaId = :perguntaId AND a.id = :alternativaId"),
    @NamedQuery(name = "Alternativa.delAlternativa", query = "DELETE FROM Alternativa a WHERE a.perguntaId = :perguntaId AND a.id = :alternativaId"),
    @NamedQuery(name = "Alternativa.saveRegraComparacao", query = "UPDATE Alternativa a SET a.perguntaIDdeComparacao = :perguntaIDdeComparacao, a.scriptIDdeComparacao = :scriptIDdeComparacao WHERE a.perguntaId = :perguntaId AND a.id = 1")
})
public class Alternativa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ALTERNATIVAPERGUNTAID", nullable=false)
	private int id;

	@Id
	@Column(name="PERGUNTAID", nullable=false)
	private int perguntaId;

	@Column(name="DESCRICAO", length=50, nullable=false)
	private String descricao = "";

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Controle controle;

    @Column(name="PERGUNTAIDDECOMPARACAO", nullable=true)
	private int perguntaIDdeComparacao;

	@Column(name="SCRIPTIDDECOMPARACAO", nullable=true)
	private int scriptIDdeComparacao;
	
	@Column(name="PROLOG", length=50, nullable=true)
	private String prolog = "";

	@Column(name="VISIVEL", nullable=false)
	private int visivel = 1;

	public Alternativa(){
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public int getPerguntaId(){
		return perguntaId;
	}

	public void setPerguntaId(int perguntaId){
		this.perguntaId = perguntaId;
	}
	
	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
		
	public Controle getControle(){
		return controle;
	}

	public void setControle(Controle controle){
		this.controle = controle;
	}

	public int getPerguntaIDdeComparacao(){
		return perguntaIDdeComparacao;
	}

	public void setPerguntaIDdeComparacao(int perguntaIDdeComparacao){
		this.perguntaIDdeComparacao = perguntaIDdeComparacao;
	}

	public int getScriptIDdeComparacao(){
		return scriptIDdeComparacao;
	}

	public void setScriptIDdeComparacao(int scriptIDdeComparacao){
		this.scriptIDdeComparacao = scriptIDdeComparacao;
	}

	public String getProlog() {
		return prolog;
	}
	
	public void setProlog(String prolog) {
		this.prolog = prolog;
	}
	
	public int getVisivel() {
		return visivel;
	}
	
	public void setVisivel(int visivel) {
		this.visivel = visivel;
	}
	
}
