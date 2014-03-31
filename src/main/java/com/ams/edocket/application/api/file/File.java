/**
 * 
 */
package com.ams.edocket.application.api.file;

import java.util.Date;

/**
 * @author Raghavendra Badiger
 * 
 */
public class File
{
	private String	name;
	private String	type;
	private long	size;
	private Date	lastModifiedDate;
	private byte[]	fileObject;

	public File()
	{}

	public File(String name, String type, long size, Date modifyDate)
	{
		this.name = name;
		this.type = type;
		this.size = size;
		this.lastModifiedDate = modifyDate;
	}

	public File(String name, String type, long size, byte[] fileData, Date modifyDate)
	{
		this.name = name;
		this.type = type;
		this.size = size;
		this.lastModifiedDate = modifyDate;
		this.fileObject = fileData;
	}

	public String getName()
	{
		return this.name;
	}

	private void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return this.type;
	}

	private void setType(String type)
	{
		this.type = type;
	}

	public long getSize()
	{
		return this.size;
	}

	private void setSize(long size)
	{
		this.size = size;
	}

	public Date getLastModifiedDate()
	{
		return this.lastModifiedDate;
	}

	private void setLastModifiedDate(Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}

	public byte[] getFileObject()
	{
		return this.fileObject;
	}

	private void setFileObject(byte[] fileObject)
	{
		this.fileObject = fileObject;
	}

}
