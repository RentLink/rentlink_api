package com.rentlink.rentlink.manage_unit_data;

import com.rentlink.rentlink.manage_files.FileToSave;
import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import com.rentlink.rentlink.manage_rental_options.RentalOptionDTO;
import com.rentlink.rentlink.manage_rental_options.RentalOptionFoundException;
import com.rentlink.rentlink.manage_rental_options.RentalOptionInternalAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
class UnitManagement implements UnitExternalAPI {

    private static final String unitsSubdir = "/units";

    private final UnitRepository unitRepository;
    private final RentalOptionInternalAPI rentalOptionInternalAPI;
    private final UnitMapper unitMapper;
    private final FilesManagerInternalAPI filesManagerInternalAPI;

    @Override
    @Transactional(readOnly = true)
    public UnitDTO getUnit(UUID unitId) {
        UnitDTO unit = unitRepository.findById(unitId).map(unitMapper::map).orElseThrow(UnitNotFoundException::new);
        Set<RentalOptionDTO> rentalOptions = rentalOptionInternalAPI.getRentalOptionsByUnitId(unitId);
        return unit.withRentalOptions(rentalOptions);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<UnitDTO> getUnits(Integer page, Integer pageSize) {
        Stream<Unit> stream;
        if (page != null && pageSize != null) {
            Pageable pageable = PageRequest.of(page, pageSize);
            stream = StreamSupport.stream(unitRepository.findAll(pageable).spliterator(), false);
        } else {
            stream = unitRepository.findAll().stream();
        }

        return stream.map(unitMapper::map)
                .map(unitDTO -> {
                    Set<RentalOptionDTO> rentalOptions = rentalOptionInternalAPI.getRentalOptionsByUnitId(unitDTO.id());
                    return unitDTO.withRentalOptions(rentalOptions);
                })
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public UnitDTO addUnit(UnitDTO unitDTO) {
        UnitDTO result = unitMapper.map(unitRepository.save(unitMapper.map(unitDTO)));
        if (result.rentalType().equals(RentalType.WHOLE)) {
            Set<RentalOptionDTO> rentalOptionResult =
                    Set.of(rentalOptionInternalAPI.upsert(new RentalOptionDTO(null, "Całe miejsce"), result.id()));
            return result.withRentalOptions(rentalOptionResult);
        } else {
            Set<RentalOptionDTO> rentalOptionResult = IntStream.range(1, result.roomsNo() + 1)
                    .mapToObj(no -> rentalOptionInternalAPI.upsert(
                            new RentalOptionDTO(null, "Pokój nr %s".formatted(no)), result.id()))
                    .collect(Collectors.toSet());
            return result.withRentalOptions(rentalOptionResult);
        }
    }

    @Override
    @Transactional
    public UnitDTO updateUnit(UUID id, UnitDTO unitDTO) {
        Unit unit = unitRepository.findById(id).orElseThrow(UnitNotFoundException::new);
        unitMapper.update(unitDTO, unit);
        UnitDTO result = unitMapper.map(unitRepository.save(unit));
        if (unitDTO.rentalOptions() != null) {
            unitDTO.rentalOptions().forEach(rentalOptionDTO -> rentalOptionInternalAPI.upsert(rentalOptionDTO, id));
        }
        return result.withRentalOptions(rentalOptionInternalAPI.getRentalOptionsByUnitId(id));
    }

    @Override
    @Transactional
    public void deleteUnit(UUID unitId) {
        unitRepository.deleteById(unitId);
    }

    @Override
    public void uploadImages(UUID unitId, UUID rentalOptionId, Set<MultipartFile> multipartFiles) {
        if (rentalOptionId == null) {
            uploadUnitImages(unitId, multipartFiles);
        } else {
            uploadRentalOptionsImages(unitId, rentalOptionId, multipartFiles);
        }
    }

    private void uploadUnitImages(UUID unitId, Set<MultipartFile> multipartFiles) {
        unitRepository.findById(unitId).orElseThrow(UnitNotFoundException::new);
        var unitSubdir = "%s/%s".formatted(unitsSubdir, unitId);
        filesManagerInternalAPI.saveImages(multipartFiles.stream()
                .map(mp -> new FileToSave(unitSubdir, mp))
                .collect(Collectors.toSet()));
    }

    private void uploadRentalOptionsImages(UUID unitId, UUID rentalOptionId, Set<MultipartFile> multipartFiles) {
        unitRepository.findById(unitId).orElseThrow(UnitNotFoundException::new);
        rentalOptionInternalAPI.getRentalOptionsById(rentalOptionId).orElseThrow(RentalOptionFoundException::new);
        var unitSubdir = "%s/%s/rentalOptions/%s".formatted(unitsSubdir, unitId, rentalOptionId);
        filesManagerInternalAPI.saveImages(multipartFiles.stream()
                .map(mp -> new FileToSave(unitSubdir, mp))
                .collect(Collectors.toSet()));
    }
}
