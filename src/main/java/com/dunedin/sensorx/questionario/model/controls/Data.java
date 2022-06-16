package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class Data extends Controle {

	public Data(){
		this.tipoHTML = "DATA";
		this.maxLength = 10;//COM SEPARADOR, MAS GRAVA SEM
	}

}
