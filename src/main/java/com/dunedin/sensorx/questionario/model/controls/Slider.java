package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class Slider extends Controle{

	public Slider(){
		this.tipoHTML = "RANGE";
	}
	
}