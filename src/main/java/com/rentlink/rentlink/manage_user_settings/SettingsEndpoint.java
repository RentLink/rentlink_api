package com.rentlink.rentlink.manage_user_settings;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingsEndpoint {

    private final SettingsExternalAPI settingsExternalAPI;

    @PutMapping(
            value = "/upload-documents",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestHeader(value = X_USER_HEADER) UUID accountId, @RequestPart(value = "files") MultipartFile[] files) {
        settingsExternalAPI.uploadFiles(Set.of(files), accountId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-document/{fileName}")
    public void deleteFile(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable String fileName) {
        settingsExternalAPI.deleteFiles(Set.of(fileName), accountId);
    }

    @GetMapping
    public SettingsDTO getSettings(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return settingsExternalAPI.getSettings(accountId);
    }

    @GetMapping("/list-files")
    public List<String> listFiles(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return settingsExternalAPI.listFiles(accountId);
    }
}
