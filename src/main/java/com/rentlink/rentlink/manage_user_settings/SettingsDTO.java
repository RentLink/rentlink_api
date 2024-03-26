package com.rentlink.rentlink.manage_user_settings;

import com.rentlink.rentlink.common.DocumentDTO;
import java.util.List;

public record SettingsDTO(List<DocumentDTO> generalDocuments) {}
