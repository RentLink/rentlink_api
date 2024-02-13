package com.rentlink.rentlink.manage_user_settings;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingsEndpoint {

    private final SettingsExternalAPI settingsExternalAPI;

    @PutMapping(
            value = "/upload-files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart(value = "files") MultipartFile[] files) {
        settingsExternalAPI.uploadFiles(Set.of(files));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
