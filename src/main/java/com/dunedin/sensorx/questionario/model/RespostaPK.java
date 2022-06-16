package com.dunedin.sensorx.questionario.model;

import java.io.Serializable;

public class RespostaPK implements Serializable {
	
	private int usuarioId;
	private int perguntaId;
	private int respostaId;
	
	public RespostaPK() {}
	
	public boolean equals(Object other) {
		
		if (other instanceof RespostaPK) {
			
			final RespostaPK otherRespostaPK = (RespostaPK) other;
			
			return ( otherRespostaPK.usuarioId == usuarioId &&
					 otherRespostaPK.perguntaId == perguntaId &&
					 otherRespostaPK.respostaId == respostaId);
					
		}
		
		return false;
		
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	

}

