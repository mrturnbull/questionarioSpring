package com.dunedin.sensorx.questionario.model;

import java.io.Serializable;
//import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findById",    query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByCPF",   query = "SELECT u FROM Usuario u WHERE u.cPF = :cPF")
})
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USUARIOID")
	private long id;

	@Column(name="CPF", length=11, nullable=true)
	private String cPF;

	@Column(name="SENHA", length=8, nullable=true)
	private String senha;

	public Usuario(){}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getCPF(){
		return cPF;
	}

	public void setCPF(String cPF){
		this.cPF = cPF;
	}

	public String getSenha(){
		return senha;
	}

	public void setSenha(String senha){
		this.senha = senha;
	}

}
