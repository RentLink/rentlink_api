package com.rentlink.rentlink.manage_fines;

import org.springframework.web.multipart.MultipartFile;

public record FileToSave(String subdirectory, MultipartFile file) {}
