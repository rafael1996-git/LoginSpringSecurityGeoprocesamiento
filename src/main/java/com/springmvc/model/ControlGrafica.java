package com.springmvc.model;

public class ControlGrafica {
	
	private String entidadID;
	private String estatusServicio;
	private String mensajeSalida;
	private String msgExceptionDao;
	
	public String getEntidadID() {
		return entidadID;
	}
	public void setEntidadID(String entidadID) {
		this.entidadID = entidadID;
	}
	public String getEstatusServicio() {
		return estatusServicio;
	}
	public void setEstatusServicio(String estatusServicio) {
		this.estatusServicio = estatusServicio;
	}
	public String getMensajeSalida() {
		return mensajeSalida;
	}
	public void setMensajeSalida(String mensajeSalida) {
		this.mensajeSalida = mensajeSalida;
	}
	public String getMsgExceptionDao() {
		return msgExceptionDao;
	}
	public void setMsgExceptionDao(String msgExceptionDao) {
		this.msgExceptionDao = msgExceptionDao;
	}
	@Override
	public String toString() {
		return "ControlGrafica [entidadID=" + entidadID + ", estatusServicio=" + estatusServicio + ", mensajeSalida="
				+ mensajeSalida + ", msgExceptionDao=" + msgExceptionDao + "]";
	}
	
	

}
