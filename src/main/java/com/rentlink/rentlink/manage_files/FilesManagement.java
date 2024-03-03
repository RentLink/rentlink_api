package com.rentlink.rentlink.manage_files;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Override
    public List<FileName> getFileNames(String subdirectory) {
        try (var stream = Files.list(Paths.get(rootFilesDir + subdirectory))) {
            return stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .map(FileName::new)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FileDTO> getFiles(String subdirectory, Set<FileName> fileNames) {
        if (fileNames.isEmpty()) {
            return List.of();
        }
        try (var stream = Files.list(Paths.get(rootFilesDir + subdirectory))) {
            var names = fileNames.stream().map(FileName::name).toList();
            return stream.filter(path -> !Files.isDirectory(path))
                    .filter(path -> names.contains(path.getFileName().toString()))
                    .map(path -> new File(path.toUri()))
                    .map(file -> new FileDTO(file.getName(), file))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFile(FileToSave file, String dir) {
        try {
            if (file.file().isEmpty()) {
                return;
            }
            Path destination = Paths.get(dir + file.subdirectory())
                    .resolve(Objects.requireNonNull(file.file().getOriginalFilename()))
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
