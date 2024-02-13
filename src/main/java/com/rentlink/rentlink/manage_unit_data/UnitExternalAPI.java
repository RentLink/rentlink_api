package com.rentlink.rentlink.manage_unit_data;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

interface UnitExternalAPI {
    UnitDTO getUnit(UUID unitId);

    Set<UnitDTO> getUnits(Integer page, Integer pageSize);

    UnitDTO addUnit(UnitDTO unitDTO);

    UnitDTO updateUnit(UUID unitId, UnitDTO unitDTO);

    void deleteUnit(UUID unitId);

    void uploadImages(UUID unitId, UUID rentalOptionId, Set<MultipartFile> multipartFiles);
}
