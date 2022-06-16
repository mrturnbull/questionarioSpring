package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class TelefoneCelular extends Controle {
	
	public TelefoneCelular(){
		this.tipoHTML = "TELEFONECELULAR";
		this.maxLength = 14; //COM SEPARADOR, MAS GRAVA SEM
	}
	
}