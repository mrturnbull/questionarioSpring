package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class Checkbox extends Controle {

	public Checkbox(){
		this.tipoHTML = getClass().getSimpleName().toUpperCase();
	}

}