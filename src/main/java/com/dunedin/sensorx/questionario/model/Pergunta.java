package com.dunedin.sensorx.questionario.model;

import com.dunedin.sensorx.questionario.model.Alternativa;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PerguntaEnunciado")
@NamedQueries({
	@NamedQuery(name = "Pergunta.findMaxOrdem", query = "SELECT MAX(pe.ordem) FROM Pergunta pe"),
    @NamedQuery(name = "Pergunta.findPerId",   query = "SELECT pe FROM Pergunta pe WHERE pe.id = :id"),
    @NamedQuery(name = "Pergunta.findAll",    query = "SELECT pe FROM Pergunta pe ORDER BY pe.ordem"),
    @NamedQuery(name = "Pergunta.delPergunta",   query = "DELETE FROM Pergunta pe WHERE pe.id = :perguntaId")
})
public class Pergunta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PERGUNTAENUNCIADOID", nullable=false)
	private int id;

	@Column(name="ENUNCIADO", nullable=false)
	private String enunciado;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	//@JoinColumns(value={@JoinColumn(name="ALTERNATIVAPERGUNTAID"), @JoinColumn(name="PERGUNTAID")})
	//@JoinColumn(name="PERGUNTAID")
	//private List<Alternativa> alternativas = new ArrayList<Alternativa>();
	private List<Alternativa> alternativas;
	
	@Column(name="TIPO", nullable=false)
	private int tipo;

	@Column(name="ORDEM", nullable=true)
	private int ordem;

	@Column(name="VISIVEL", nullable=false)
	private int visivel = 1;

	@Column(name="ISREQUIRED", nullable=true)
	private int isRequired;

	public Pergunta(){}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getEnunciado(){
		return enunciado;
	}

	public void setEnunciado(String enunciado){
		this.enunciado = enunciado;
	}

	public List<Alternativa> getAlternativas(){
		return alternativas;
	}
	
	public void setAlternativas(List<Alternativa> alternativas){
		this.alternativas = alternativas;
	}
	
	public int getTipo(){
		return tipo;
	}

	public void setTipo(int tipo){
		this.tipo = tipo;
	}

	public int getOrdem(){
		return ordem;
	}

	public void setOrdem(int ordem){
		this.ordem = ordem;
	}

	public int getVisivel(){
		return visivel;
	}

	public void setVisivel(int visivel){
		this.visivel = visivel;
	}

	public int getIsRequired(){
		return isRequired;
	}

	public void setIsRequired(int isRequired){
		this.isRequired = isRequired;
	}

}
