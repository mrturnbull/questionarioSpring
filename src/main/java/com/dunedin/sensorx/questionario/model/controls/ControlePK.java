package com.dunedin.sensorx.questionario.model.controls;

import java.io.Serializable;

public class ControlePK implements Serializable {
	
	private int perguntaId;
	private int alternativaId;
	
	public ControlePK() {}
	
	public boolean equals(Object other) {
		
		if (other instanceof ControlePK) {
			
			final ControlePK otherControlePK = (ControlePK) other;
			
			return (otherControlePK.perguntaId == perguntaId && otherControlePK.alternativaId == alternativaId);
			
		}
		
		return false;
		
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	

}
