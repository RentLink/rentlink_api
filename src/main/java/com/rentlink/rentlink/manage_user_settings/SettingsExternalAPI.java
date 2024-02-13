package com.rentlink.rentlink.manage_user_settings;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface SettingsExternalAPI {

    void uploadFiles(Set<MultipartFile> multipartFiles);
}
