package com.rentlink.rentlink.manage_user_settings;

import java.util.List;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

public interface SettingsExternalAPI {

    void uploadFiles(Set<MultipartFile> multipartFiles);

    List<String> listFiles();
}
