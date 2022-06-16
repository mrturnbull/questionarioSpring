package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class RadioButton extends Controle {

	public RadioButton(){
		this.tipoHTML = "RADIO";
	}

}