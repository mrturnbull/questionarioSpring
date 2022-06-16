package com.dunedin.sensorx.questionario.model.controls;

import java.io.Serializable;

public class OpcaoPK implements Serializable {
	
	private int perguntaId;
	private int alternativaId;
	private int opcaoId;
	
	public OpcaoPK() {}
	
	public boolean equals(Object other) {
		
		if (other instanceof OpcaoPK) {
			
			final OpcaoPK otherOpcaoPK = (OpcaoPK) other;
			
			return (otherOpcaoPK.opcaoId == opcaoId && otherOpcaoPK.perguntaId == perguntaId && otherOpcaoPK.alternativaId == alternativaId);
			
		}
		
		return false;
		
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	

}
