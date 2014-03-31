/**
 * 
 */
package com.ams.edocket.application.api.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Raghavendra Badiger
 * 
 */
public class Folder
{
	private String			name;
	private String			path;
	private Date			lastModifiedDate;
	private List<Folder>	subFolderList;
	private List<File>		fileList;

	public Folder()
	{}

	public Folder(String name, String path)
	{
		this.name = name;
		this.path = path;
		this.subFolderList = new ArrayList<Folder>(5);
		this.fileList = new ArrayList<File>(5);
	}

	public Folder(String name, String path, Date modifyDate)
	{
		this.name = name;
		this.path = path;
		this.lastModifiedDate = modifyDate;
		this.subFolderList = new ArrayList<Folder>(5);
		this.fileList = new ArrayList<File>(5);
	}

	public void addFolder(Folder folder)
	{
		if (!folder.equals(null))
		{
			this.subFolderList.add(folder);
		}
	}

	public void addFile(File file)
	{
		if (!file.equals(null))
		{
			this.fileList.add(file);
		}
	}

	public String getName()
	{
		return this.name;
	}

	private void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return this.path;
	}

	private void setPath(String path)
	{
		this.path = path;
	}

	public Date getLastModifiedDate()
	{
		return this.lastModifiedDate;
	}

	private void setLastModifiedDate(Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}

	public List<Folder> getSubFolderList()
	{
		return this.subFolderList;
	}

	private void setSubFolderList(List<Folder> subFolderList)
	{
		this.subFolderList = subFolderList;
	}

	public List<File> getFileList()
	{
		return this.fileList;
	}

	private void setFileList(List<File> fileList)
	{
		this.fileList = fileList;
	}

}
