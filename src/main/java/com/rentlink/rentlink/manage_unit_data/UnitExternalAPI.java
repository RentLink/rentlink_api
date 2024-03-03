package com.rentlink.rentlink.manage_unit_data;

import java.util.Set;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

interface UnitExternalAPI {
    UnitDTO getUnit(UUID unitId, UUID accountId);

    Set<UnitDTO> getUnits(UUID accountId, Integer page, Integer pageSize);

    UnitDTO addUnit(UnitDTO unitDTO, UUID accountId);

    UnitDTO updateUnit(UUID unitId, UUID accountId, UnitDTO unitDTO);

    void deleteUnit(UUID unitId, UUID accountId);

    void uploadImages(UUID unitId, UUID accountId, UUID rentalOptionId, Set<MultipartFile> multipartFiles);
}
