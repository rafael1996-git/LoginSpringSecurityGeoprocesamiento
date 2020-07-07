package com.springmvc.model;

import java.util.Date;

public class Fecha {
	
	private String REMESA;
	private int SEMANA;
	Date FECHA_INICIAL;
	Date FECHA_FINAL;
	Date FINAL_CORTE;
	private int TIPO_CAMPAÑA;
	
	
	public String getREMESA() {
		return REMESA;
	}
	public void setREMESA(String rEMESA) {
		REMESA = rEMESA;
	}
	public int getSEMANA() {
		return SEMANA;
	}
	public void setSEMANA(int sEMANA) {
		SEMANA = sEMANA;
	}
	public Date getFECHA_INICIAL() {
		return FECHA_INICIAL;
	}
	public void setFECHA_INICIAL(Date fECHA_INICIAL) {
		FECHA_INICIAL = fECHA_INICIAL;
	}
	public Date getFECHA_FINAL() {
		return FECHA_FINAL;
	}
	public void setFECHA_FINAL(Date fECHA_FINAL) {
		FECHA_FINAL = fECHA_FINAL;
	}
	public Date getFINAL_CORTE() {
		return FINAL_CORTE;
	}
	public void setFINAL_CORTE(Date fINAL_CORTE) {
		FINAL_CORTE = fINAL_CORTE;
	}
	public int getTIPO_CAMPAÑA() {
		return TIPO_CAMPAÑA;
	}
	public void setTIPO_CAMPAÑA(int tIPO_CAMPAÑA) {
		TIPO_CAMPAÑA = tIPO_CAMPAÑA;
	}

	@Override
	public String toString() {
		return "Fecha [REMESA=" + REMESA + ", SEMANA=" + SEMANA + ", FECHA_INICIAL=" + FECHA_INICIAL + ", FECHA_FINAL="
				+ FECHA_FINAL + ", FINAL_CORTE=" + FINAL_CORTE + ", TIPO_CAMPAÑA=" + TIPO_CAMPAÑA + "]";
	}

}
