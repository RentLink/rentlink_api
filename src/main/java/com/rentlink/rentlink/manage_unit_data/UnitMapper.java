package com.rentlink.rentlink.manage_unit_data;

import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AssociatedRoomMapper.class, UtilityMapper.class, UnitEquipmentMapper.class})
interface UnitMapper {

    UnitDTO map(Unit unit);

    @Mapping(target = "accountId", ignore = true)
    Unit map(UnitDTO unitDTO);

    @InheritConfiguration
    void update(UnitDTO unitDTO, @MappingTarget Unit unit);

    @AfterMapping
    default void setUnit(@MappingTarget Unit unit) {
        List<AssociatedRoom> associatedRooms = unit.getAssociatedRoom();
        List<Utility> utilities = unit.getUtilities();
        List<UnitEquipment> unitEquipments = unit.getUnitEquipment();
        if (associatedRooms != null) {
            associatedRooms.forEach(ar -> ar.setUnit(unit));
        }
        if (utilities != null) {
            utilities.forEach(ut -> ut.setUnit(unit));
        }
        if (unitEquipments != null) {
            unitEquipments.forEach(ue -> ue.setUnit(unit));
        }
    }
}
