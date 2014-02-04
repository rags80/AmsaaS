package com.ams.sharedkernel.ports.adapter.webclient.springmvc.controller;

public class ServerMessage
{
	private String	operationStatus;

	public ServerMessage(String status)
	{
		this.operationStatus = status;
	}

	public String getOperationStatus()
	{
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus)
	{
		this.operationStatus = operationStatus;
	}
}
