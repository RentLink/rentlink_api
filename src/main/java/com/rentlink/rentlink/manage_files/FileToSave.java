package com.rentlink.rentlink.manage_files;

import org.springframework.web.multipart.MultipartFile;

public record FileToSave(String subdirectory, MultipartFile file) {}
