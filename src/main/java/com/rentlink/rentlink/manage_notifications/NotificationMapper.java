package com.rentlink.rentlink.manage_notifications;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface NotificationMapper {

    NotificationDTO map(Notification unit);

    Notification map(NotificationDTO unitDTO);
}
