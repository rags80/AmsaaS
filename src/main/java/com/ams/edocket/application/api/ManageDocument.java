/**
 *
 */
package com.ams.edocket.application.api;

import java.util.List;

import com.ams.edocket.domain.model.File;
import com.ams.edocket.domain.model.Folder;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 */
public interface ManageDocument
{

	File deleteFile(Person fileOwner, String filePath);

	List<File> getAllFilesFromFolder(Folder folder);

	Folder getFolderContents(Person folderOwner, String folderPath);

	Folder newDocumentsFolder(Person folderOwner, String folderPath);

	Folder removeDocumentsFolder(Person folderOwner, String folderPath);

	Folder saveFiles(Person fileOwner, Folder fileData);

	Folder updateDocumentsFolderDetails(Folder folderData);

	File updateFileDetails(File fileDetails);
}
