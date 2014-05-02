/**
 * 
 */
package com.ams.edocket.application.api.file;

import java.util.Date;

import javax.persistence.Lob;

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

	public File(String name, String type, long size, byte[] fileData, Date modifyDate)
	{
		this.name = name;
		this.type = type;
		this.size = size;
		this.lastModifiedDate = modifyDate;
		this.fileObject = fileData;
	}

	public File(String name, String type, long size, Date modifyDate)
	{
		this.name = name;
		this.type = type;
		this.size = size;
		this.lastModifiedDate = modifyDate;
	}

	@Lob
	public byte[] getFileObject()
	{
		return this.fileObject;
	}

	public Date getLastModifiedDate()
	{
		return this.lastModifiedDate;
	}

	public String getName()
	{
		return this.name;
	}

	public long getSize()
	{
		return this.size;
	}

	public String getType()
	{
		return this.type;
	}

	private void setFileObject(byte[] fileObject)
	{
		this.fileObject = fileObject;
	}

	private void setLastModifiedDate(Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}

	private void setName(String name)
	{
		this.name = name;
	}

	private void setSize(long size)
	{
		this.size = size;
	}

	private void setType(String type)
	{
		this.type = type;
	}

}
