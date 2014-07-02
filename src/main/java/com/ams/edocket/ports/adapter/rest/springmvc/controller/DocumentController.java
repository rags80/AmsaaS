/**
 *
 */
package com.ams.edocket.ports.adapter.rest.springmvc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ams.edocket.application.api.ManageDocument;
import com.ams.edocket.domain.model.File;
import com.ams.edocket.domain.model.Folder;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 */

@Controller
public class DocumentController
{
	@Autowired
	private ManageDocument	manageDocument;

	@RequestMapping(value = "docs/folders/{folderPath}",method = RequestMethod.POST)
	@ResponseBody
	public Folder createNewFolder(@PathVariable String folderPath)
	{
		return this.manageDocument.newDocumentsFolder(this.getFolderOwner(), this.translatedPath(folderPath, PathStrategy.ANGULAR));

	};

	@RequestMapping(value = "docs/folders/{folderPath}",method = RequestMethod.GET)
	@ResponseBody
	public Folder getFolderContents(@PathVariable String folderPath)
	{
		return this.manageDocument.getFolderContents(this.getFolderOwner(), this.translatedPath(folderPath, PathStrategy.ANGULAR));
	}

	private Person getFolderOwner()
	{
		Person p = new Person("Raghav", "Badiger");
		return p;
	}

	@RequestMapping(value = "docs/folders",method = RequestMethod.GET)
	@ResponseBody
	public Folder getRootFolderContents()
	{

		return this.manageDocument.getFolderContents(this.getFolderOwner(), "");
	}

	@RequestMapping(value = "docs/folders/{folderPath}",method = RequestMethod.DELETE)
	@ResponseBody
	public Folder removeFolder(@PathVariable String folderPath)
	{
		return this.manageDocument.removeDocumentsFolder(this.getFolderOwner(), this.translatedPath(folderPath, PathStrategy.ANGULAR));
	}

	private String translatedPath(String reltvPath, PathStrategy ps)
	{
		String replcmntToken;
		switch (ps)
		{
		case ANGULAR:
			replcmntToken = ">";
			break;
		case HORIZONTAL:
			replcmntToken = "-";
			break;
		default:
			throw new IllegalArgumentException("Invalid path strategy!! ");
		}

		return reltvPath.replace(replcmntToken, "/");

	}

	@RequestMapping(value = "docs/folders/{folderName}/files",method = RequestMethod.POST)
	@ResponseBody
	public Folder uploadFiles(@PathVariable String folderName, MultipartHttpServletRequest multipartrequest) throws IOException
	{
		Iterator<String> fileNames = multipartrequest.getFileNames();
		Folder folder = new Folder(folderName.substring(folderName.lastIndexOf(">") + 1), folderName);
		File file = null;

		while (fileNames.hasNext())
		{
			MultipartFile mf = multipartrequest.getFile(fileNames.next());
			System.out.println("file name:" + mf.getOriginalFilename());
			file = new File(mf.getOriginalFilename(), mf.getContentType(), mf.getSize(), mf.getBytes(), new Date());
			folder.addFile(file);
		}

		return this.manageDocument.saveFiles(this.getFolderOwner(), folder);

	}

	enum PathStrategy
	{
		ANGULAR, HORIZONTAL
	}
}
