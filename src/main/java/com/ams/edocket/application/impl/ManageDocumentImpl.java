package com.ams.edocket.application.impl;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.ams.edocket.application.api.ManageDocument;
import com.ams.edocket.application.api.file.File;
import com.ams.edocket.application.api.file.Folder;
import com.ams.sharedkernel.application.api.exception.ServiceException;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */

@Service("ManageDocument")
public final class ManageDocumentImpl implements ManageDocument
{
	static class FileRepository
	{
		static FileRepository resourceAtLocation(Person user, String path)
		{
			return new FileRepository(user, path);

		}

		private String	reltvPath;

		private Person	fileOwner;

		private FileRepository()
		{}

		private FileRepository(Person user, String path)
		{
			this.fileOwner = user;
			this.reltvPath = path;
		}

		java.io.File createFolders()
		{
			java.io.File file = new java.io.File(this.getActualResourcePath(this.fileOwner, this.reltvPath));
			if (file.mkdirs())
			{
				return file;
			}
			else
			{
				throw new ServiceException("Folder creation failed!!");
			}
		}

		public java.io.File deleteFolder()
		{
			java.io.File searchFolder = new java.io.File(this.getActualResourcePath(this.fileOwner, this.reltvPath));
			if (searchFolder.isDirectory() && FileUtils.deleteQuietly(searchFolder))
			{
				return searchFolder;
			}
			else
			{
				throw new ServiceException("Folder:" + searchFolder + " delete failed!!");
			}

		}

		/**
		 * 
		 */
		java.io.File[] findAllFilesAndFolders()
		{
			java.io.File searchFolder = new java.io.File(this.getActualResourcePath(this.fileOwner, this.reltvPath));

			if (searchFolder.isDirectory())
			{
				return searchFolder.listFiles();
			}
			else
			{
				throw new ServiceException("Folder named:" + this.reltvPath + " doesn't exist!!");
			}

		}

		private String getActualResourcePath(Person user, String reltvPath)
		{
			/**
			 * 
			 * Implement strategy to find actual path from relative path of
			 * folder of document owner
			 * 
			 * */
			String actlpath = "D:/" + user.getPersnFirstName() + "/" + reltvPath;
			System.out.println("Actual Path:" + actlpath);
			return actlpath;
		}

		private String getActualResourcePath(Person user, String reltvPath, String fileName)
		{
			/**
			 * 
			 * Implement strategy to find actual path from relative path of
			 * folder of document owner
			 * 
			 * */
			String actlpath = "D:/" + user.getPersnFirstName() + "/" + reltvPath + "/" + fileName;
			System.out.println("Actual Path:" + actlpath);
			return actlpath;
		}

		boolean storeFiles(List<File> fileList)
		{

			Iterator<File> fileItr = fileList.iterator();
			FileOutputStream fo;

			File file = fileItr.next();

			try
			{
				while (fileItr.hasNext())
				{
					fo = new FileOutputStream(new java.io.File(this.getActualResourcePath(this.fileOwner, this.reltvPath, file.getName())));
					fo.write(file.getFileObject());
					fo.close();

				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return true;
		}

	}

	@Override
	public File deleteFile(Person owner, String reltvPath)
	{
		throw new ServiceException("Functionality not implemented!!");
	}

	@Override
	public List<File> getAllFilesFromFolder(Folder folder)
	{
		throw new ServiceException("Functionality not implemented!!");
	}

	@Override
	public Folder getFolderContents(Person user, String folderPath)
	{

		Folder folder = new Folder(folderPath.substring(folderPath.lastIndexOf("/") + 1), folderPath);
		java.io.File[] filesNfolders = FileRepository.resourceAtLocation(user, folderPath).findAllFilesAndFolders();

		for (java.io.File fl : filesNfolders)
		{
			if (fl.isDirectory())
			{
				folder.addFolder(new Folder(fl.getName(), folderPath + "/" + fl.getName(), new Date(fl.lastModified())));

			}
			else if (fl.isFile())
			{

				folder.addFile(new File(fl.getName(), FilenameUtils.getExtension(fl.getName()), fl.length(), new Date(fl.lastModified())));
			}

		}

		return folder;
	}

	/**
	 * 
	 * { name:My Pics, folders:[{name:festival},{name:outdoor},{name:funny}],
	 * files:[
	 * {name:profile-1.jpg,size:20kb,type:jpg,lastModifiedOn:15/04/2014},
	 * {name:profile-2.jpg,size:40kb,type:jpg,lastModifiedOn:15/03/2014} ] }
	 * 
	 * */

	@Override
	public Folder newDocumentsFolder(Person folderOwner, String folderPath)
	{
		java.io.File fl = FileRepository.resourceAtLocation(folderOwner, folderPath).createFolders();
		if (fl != (null))
		{
			return new Folder(fl.getName(), folderPath);
		}
		else
		{
			throw new ServiceException("Folder creation failed!!");
		}
	}

	@Override
	public Folder removeDocumentsFolder(Person folderOwner, String folderPath)
	{
		java.io.File fl = FileRepository.resourceAtLocation(folderOwner, folderPath).deleteFolder();

		if (fl != null)
		{
			return new Folder(fl.getName(), folderPath);
		}
		else
		{
			throw new ServiceException("Unable to delete folder:" + folderPath);
		}
	}

	@Override
	public Folder saveFiles(Person fileOwner, Folder folder)
	{
		try
		{
			FileRepository.resourceAtLocation(fileOwner, folder.getPath()).storeFiles(folder.getFileList());

		} catch (Exception e)
		{

			e.printStackTrace();
			throw new ServiceException("Files saving failed!!");
		}

		throw new ServiceException("Functionality not implemented!!");
	}

	@Override
	public Folder updateDocumentsFolderDetails(Folder folder)
	{
		throw new ServiceException("Folder update functionality not implemented!!");
	}

	@Override
	public File updateFileDetails(File fileDetails)
	{
		throw new ServiceException("Functionality not implemented!!");
	}

}
