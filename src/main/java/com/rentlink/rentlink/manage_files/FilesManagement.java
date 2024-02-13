package com.rentlink.rentlink.manage_files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class FilesManagement implements FilesManagerInternalAPI {

    @Value("${rentlink.images.dir}")
    private String rootImagesDir;

    @Value("${rentlink.files.dir}")
    private String rootFilesDir;

    @Override
    public void saveImages(Set<FileToSave> files) {
        files.stream().filter(Objects::nonNull).forEach(file -> saveFile(file, rootImagesDir));
    }

    @Override
    public void saveFiles(Set<FileToSave> files) {
        files.stream().filter(Objects::nonNull).forEach(file -> saveFile(file, rootFilesDir));
    }

    private void saveFile(FileToSave file, String dir) {
        try {
            if (file.file().isEmpty()) {
                return;
            }
            Path destination = Paths.get(dir + file.subdirectory())
                    .resolve(file.file().getOriginalFilename())
                    .normalize()
                    .toAbsolutePath();
            Files.createDirectories(destination.getParent());
            Files.copy(file.file().getInputStream(), destination);
        } catch (FileAlreadyExistsException existsException) {
            log.warn("File already exist exception");
        } catch (IOException e) {
            throw new RuntimeException("Store exception", e);
        }
    }
}
