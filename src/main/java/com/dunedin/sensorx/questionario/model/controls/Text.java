package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class Text extends Controle {

	public Text(int maxLength){
		this.tipoHTML = getClass().getSimpleName().toUpperCase();
		this.maxLength = maxLength;
	}

}