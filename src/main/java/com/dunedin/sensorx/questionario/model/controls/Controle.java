package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.Alternativa;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@IdClass(ControlePK.class)
@Table(name="CONTROLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
	@NamedQuery(name = "Controle.findMaxId", query = "SELECT MAX(c.perguntaId) FROM Controle c"),
    @NamedQuery(name = "Controle.findPerId", query = "SELECT c FROM Controle c WHERE c.perguntaId = :perguntaId AND c.alternativaId = :alternativaId"),
    @NamedQuery(name = "Controle.findAll",   query = "SELECT c FROM Controle c ORDER BY c.perguntaId, c.alternativaId"),
    @NamedQuery(name = "Controle.delete",    query = "DELETE FROM Controle c WHERE c.perguntaId = :perguntaId AND c.alternativaId = :alternativaId")
})
public class Controle implements Serializable{

	@Id
	@Column(name="CPERGUNTAID", nullable=false)
	protected int perguntaId;

	@Id
	@Column(name="CALTERNATIVAID", nullable=false)
	protected int alternativaId;

	//Macete: atributo que faltava pra permitir a serializacao/deserializacao entre browser e servidor
	@Column(name="DESCRICAO", length=50, nullable=false)
	private String descricao = "";

	//Macete pra evitar que classes como Checkbox e Radiobutton estejam vazias
	@Column(name="CTIPOHTML", length=50, nullable=false)
	protected String tipoHTML;	

	@Column(name="VALOR", length=50, nullable=true)
	protected String valor;	

	@Column(name="LIMITEMINIMO", nullable=true)
	protected long limMinimo;

	@Column(name="LIMITEMAXIMO", nullable=true)
	protected long limMaximo;

	//COM SEPARADOR
	@Column(name="MAXLENGTH", nullable=true)
	protected int maxLength;

	@Column(name="ISREQUIRED", nullable=true)
	protected int isRequired;

	@Column(name="ISCHECKED", nullable=true) /* Usado em radiobutton e checkbox */
	protected int isChecked;

	@Column(name="CLASSEMASCARA", nullable=true) /* Usado nos campos Text */
	protected String classeMascara;

	@Column(name="FORMATOMASCARA", nullable=true) /* Usado nos campos Text */
	protected String formatoMascara;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	protected List<Opcao> opcoes;
	
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

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public String getTipoHTML(){
		return tipoHTML;
	}

	public void setTipoHTML(String tipoHTML){
		this.tipoHTML = tipoHTML;
	}

	public String getValor(){
		return valor;
	}

	public void setValor(String valor){
		this.valor = valor;
	}

	public long getLimMinimo(){
		return limMinimo;
	}

	public void setLimMinimo(long limMinimo){
		this.limMinimo = limMinimo;
	}

	public long getLimMaximo(){
		return limMaximo;
	}

	public void setLimMaximo(long limMaximo){
		this.limMaximo = limMaximo;
	}

	public int getMaxLength(){
		return maxLength;
	}

	public void setMaxLength(int maxLength){
		this.maxLength = maxLength;		
	}

	public List<Opcao> getOpcoes(){
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes){
		this.opcoes = opcoes;
	}

	public int getIsRequired(){
		return isRequired;
	}

	public void setIsRequired(int isRequired){
		this.isRequired = isRequired;
	}

	public int getIsChecked(){
		return isChecked;
	}

	public void setIsChecked(int isChecked){
		this.isChecked = isChecked;
	}

	public String getClasseMascara(){
		return classeMascara;
	}

	public void setClasseMascara(String classeMascara){
		this.classeMascara = classeMascara;
	}

	public String getFormatoMascara(){
		return formatoMascara;
	}

	public void setFormatoMascara(String formatoMascara){
		this.formatoMascara = formatoMascara;
	}
	
}