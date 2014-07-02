/**
 *
 */
package com.ams.edocket.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Id;

/**
 * @author Raghavendra Badiger
 */

// @Entity @Access(AccessType.FIELD)@Table(name = "T_DOCKET_FOLDER")
public class Folder
{
	@Id
	private String			name;
	private String			path;
	private Date			lastModifiedDate;
	private List<Folder>	subFolderList;
	@ElementCollection
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

	public void addFile(File file)
	{
		if (file != null)
		{
			this.fileList.add(file);
		}
	}

	public void addFolder(Folder folder)
	{
		if (folder != null)
		{
			this.subFolderList.add(folder);
		}
	}

	public List<File> getFileList()
	{
		return this.fileList;
	}

	private void setFileList(List<File> fileList)
	{
		this.fileList = fileList;
	}

	public Date getLastModifiedDate()
	{
		return this.lastModifiedDate;
	}

	private void setLastModifiedDate(Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
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

	public List<Folder> getSubFolderList()
	{
		return this.subFolderList;
	}

	private void setSubFolderList(List<Folder> subFolderList)
	{
		this.subFolderList = subFolderList;
	}

}
