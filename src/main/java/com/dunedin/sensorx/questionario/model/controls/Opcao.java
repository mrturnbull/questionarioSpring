package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;
import com.dunedin.sensorx.questionario.model.controls.OpcaoPK;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

@Entity
@IdClass(OpcaoPK.class)
@Table(name="COMBOBOXOPCAO")
@NamedQueries({
	@NamedQuery(name = "Opcao.findMaxOpcaoId", query = "SELECT MAX(op.opcaoId) FROM Opcao op WHERE op.perguntaId = :perguntaId AND op.alternativaId = :alternativaId"),
    @NamedQuery(name = "Opcao.findPerId", query = "SELECT op FROM Opcao op WHERE op.perguntaId = :perguntaId AND op.alternativaId = :alternativaId AND op.opcaoId = :opcaoId"),
    @NamedQuery(name = "Opcao.findAll",   query = "SELECT op FROM Opcao op ORDER BY op.perguntaId, op.alternativaId, op.opcaoId"),
    @NamedQuery(name = "Opcao.deleteUma",    query = "DELETE FROM Opcao op WHERE op.perguntaId = :perguntaId AND op.alternativaId = :alternativaId AND op.opcaoId = :opcaoId"),
    @NamedQuery(name = "Opcao.deleteAll", query = "DELETE FROM Opcao op WHERE op.perguntaId = :perguntaId AND op.alternativaId = :alternativaId")
})
public class Opcao implements Serializable {	
	
	@Id
	@Column(name="OPPERGUNTAID", nullable=false)
	private int perguntaId;

	@Id
	@Column(name="OPALTERNATIVAID", nullable=false)
	private int alternativaId;
	
	@Id
	@Column(name="OPCAOID", nullable=false)
	private int    opcaoId;

	@Column(name="ROTULO", length=500, nullable=false)
	private String rotulo;

	@Column(name="VALOR",  length=500,  nullable=false)
	private String valor;

	public Opcao(){}

	public Opcao(int perguntaId, int alternativaId, int opcaoId, String rotulo, String valor){
		this.perguntaId 	= perguntaId;
		this.alternativaId  = alternativaId;		
		this.opcaoId    	= opcaoId;
		this.rotulo     	= rotulo;
		this.valor      	= valor;
	}

	/*
	getters/setters
	*/

	public int getPerguntaId(){
		return perguntaId;
	}

	public void setPerguntaId(int perguntaId){
		this.perguntaId = perguntaId;
	}


	public int getAlternativaId(){
		return alternativaId;
	}

	public void setAlternativaId(int alternativaId){
		this.alternativaId = alternativaId;
	}

	public int getOpcaoId(){
		return opcaoId;
	}

	public void setOpcaoId(int opcaoId){
		this.opcaoId = opcaoId;
	}

	public String getRotulo(){
		return rotulo;
	}

	public void setRotulo(String rotulo){
		this.rotulo = rotulo;
	}

	public String getValor(){
		return valor;
	}

	public void setValor(String valor){
		this.valor = valor;
	}	

}