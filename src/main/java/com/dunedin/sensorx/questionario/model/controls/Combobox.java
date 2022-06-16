package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;
import com.dunedin.sensorx.questionario.model.controls.Opcao;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Combobox extends Controle implements Serializable {

	public Combobox(){
		this.tipoHTML = "SELECT";
	}

}