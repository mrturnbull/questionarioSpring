package com.dunedin.sensorx.questionario.model.controls;

import com.dunedin.sensorx.questionario.model.controls.Controle;

import javax.persistence.Entity;

@Entity
public class FileUpload extends Controle {

	public FileUpload(){
		this.tipoHTML = "FILEUPLOAD";
	}

}