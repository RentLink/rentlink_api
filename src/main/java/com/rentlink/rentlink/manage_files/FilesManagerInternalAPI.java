package com.rentlink.rentlink.manage_files;

import java.util.List;
import java.util.Set;

public interface FilesManagerInternalAPI {

    void saveImages(Set<FileToSave> files);

    void saveFiles(Set<FileToSave> files);

    List<FileName> getFileNames(String subdirectory);

    List<FileDTO> getFiles(String subdirectory, Set<FileName> fileNames);
}
