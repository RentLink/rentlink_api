package com.rentlink.rentlink.manage_user_settings;

import com.rentlink.rentlink.manage_files.FileToSave;
import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SettingsManagement implements SettingsExternalAPI {

    private final FilesManagerInternalAPI filesManagerInternalAPI;

    @Override
    public void uploadFiles(Set<MultipartFile> multipartFiles) {
        filesManagerInternalAPI.saveFiles(
                multipartFiles.stream().map(mp -> new FileToSave("default", mp)).collect(Collectors.toSet()));
    }
}
