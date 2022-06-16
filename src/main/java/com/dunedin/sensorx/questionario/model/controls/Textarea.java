package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public abstract class Textarea extends Controle{
	
	public Textarea(){		
		this.tipoHTML = getClass().getSimpleName().toUpperCase();
		this.maxLength = 500;		
	}

}