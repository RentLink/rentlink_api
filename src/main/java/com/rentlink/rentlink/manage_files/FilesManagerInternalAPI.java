package com.rentlink.rentlink.manage_files;

import java.util.Set;

public interface FilesManagerInternalAPI {

    void saveImages(Set<FileToSave> files);

    void saveFiles(Set<FileToSave> files);
}
