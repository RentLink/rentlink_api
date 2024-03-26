package com.rentlink.rentlink.common;

import com.rentlink.rentlink.manage_files.FileMetadata;
import com.rentlink.rentlink.manage_user_settings.FileExtension;

public record DocumentDTO(String name, FileExtension extension) {

    public static DocumentDTO fromFileMetadata(FileMetadata fileMetadata) {
        return new DocumentDTO(fileMetadata.name(), FileExtension.fromString(fileMetadata.extension()));
    }
}
