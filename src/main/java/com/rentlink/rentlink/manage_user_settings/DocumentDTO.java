package com.rentlink.rentlink.manage_user_settings;

import com.rentlink.rentlink.manage_files.FileMetadata;

public record DocumentDTO(String name, FileExtension extension) {

    public static DocumentDTO fromFileMetadata(FileMetadata fileMetadata) {
        return new DocumentDTO(fileMetadata.name(), FileExtension.fromString(fileMetadata.extension()));
    }
}
