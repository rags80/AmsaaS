/**
 * 
 */
package com.ams.edocket.application.api;

import java.util.List;

import com.ams.edocket.application.api.file.File;
import com.ams.edocket.application.api.file.Folder;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */
public interface ManageDocument
{
	Folder newDocumentsFolder(Person folderOwner, String folderPath);

	Folder updateDocumentsFolderDetails(Folder folderData);

	Folder removeDocumentsFolder(Person folderOwner, String folderPath);

	Folder getFolderContents(Person folderOwner, String folderPath);

	Folder saveFiles(Person fileOwner, Folder fileData);

	File updateFileDetails(File fileDetails);

	File deleteFile(Person fileOwner, String filePath);

	List<File> getAllFilesFromFolder(Folder folder);

}
