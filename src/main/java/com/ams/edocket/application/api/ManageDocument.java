/**
 *
 */
package com.ams.edocket.application.api;

import com.ams.edocket.application.api.file.File;
import com.ams.edocket.application.api.file.Folder;
import com.ams.users.domain.model.Person;

import java.util.List;

/**
 * @author Raghavendra Badiger
 */
public interface ManageDocument {
    File deleteFile(Person fileOwner, String filePath);

    List<File> getAllFilesFromFolder(Folder folder);

    Folder getFolderContents(Person folderOwner, String folderPath);

    Folder newDocumentsFolder(Person folderOwner, String folderPath);

    Folder removeDocumentsFolder(Person folderOwner, String folderPath);

    Folder saveFiles(Person fileOwner, Folder fileData);

    Folder updateDocumentsFolderDetails(Folder folderData);

    File updateFileDetails(File fileDetails);

}
