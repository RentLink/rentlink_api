package com.rentlink.rentlink.manage_user_settings;

import com.rentlink.rentlink.common.DocumentDTO;
import com.rentlink.rentlink.manage_files.FileMetadata;
import com.rentlink.rentlink.manage_files.FileToSave;
import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SettingsManagement implements SettingsExternalAPI {

    private final FilesManagerInternalAPI filesManagerInternalAPI;

    @Override
    public void uploadFiles(Set<MultipartFile> multipartFiles, UUID accountId) {
        filesManagerInternalAPI.saveFiles(multipartFiles.stream()
                .map(mp -> new FileToSave(accountId.toString(), mp))
                .collect(Collectors.toSet()));
    }

    @Override
    public void deleteFiles(Set<String> fileNames, UUID accountId) {
        filesManagerInternalAPI.deleteFiles(accountId.toString(), fileNames);
    }

    @Override
    public SettingsDTO getSettings(UUID accountId) {
        List<DocumentDTO> files = filesManagerInternalAPI.getFileNames(accountId.toString()).stream()
                .map(DocumentDTO::fromFileMetadata)
                .collect(Collectors.toList());
        return new SettingsDTO(files);
    }

    @Override
    public List<String> listFiles(UUID accountId) {
        return filesManagerInternalAPI.getFileNames(accountId.toString()).stream()
                .map(FileMetadata::name)
                .collect(Collectors.toList());
    }
}
