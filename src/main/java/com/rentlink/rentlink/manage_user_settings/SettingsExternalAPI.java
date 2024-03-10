package com.rentlink.rentlink.manage_user_settings;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface SettingsExternalAPI {

    void uploadFiles(Set<MultipartFile> multipartFiles, UUID accountId);

    SettingsDTO getSettings(UUID accountId);

    List<String> listFiles(UUID accountId);
}
