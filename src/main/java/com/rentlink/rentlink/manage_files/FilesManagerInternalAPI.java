package com.rentlink.rentlink.manage_files;

import java.util.List;
import java.util.Set;

public interface FilesManagerInternalAPI {

    void saveImages(Set<FileToSave> files);

    void saveMimeFiles(Set<MimebodyPartToSave> files);

    void saveFiles(Set<FileToSave> files);

    List<FileMetadata> getFileNames(String subdirectory);

    List<FileDTO> getFiles(String subdirectory, Set<String> fileMetadata);

    void deleteFiles(String subdirectory, Set<String> fileMetadata);
}
