package com.dunedin.sensorx.questionario.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Scripte")
@NamedQueries({
	@NamedQuery(name = "Scripte.find",    query = "from Scripte s where s.scriptId = :scriptId"),
    @NamedQuery(name = "Scripte.list", query = "from Scripte s order by s.scriptId"),
    @NamedQuery(name = "Scripte.delete",  query = "delete Scripte s where s.scriptId = :scriptId")    
})
public class Scripte implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SCRIPTID", nullable=false)
	private int scriptId;

	@Column(name="NOME", length=100, nullable=false)
	private String nome;

	@Column(name="BODY", length=5000, nullable=false)
	private String body;

	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
