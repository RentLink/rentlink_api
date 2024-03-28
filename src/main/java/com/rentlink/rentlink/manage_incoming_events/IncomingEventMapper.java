package com.rentlink.rentlink.manage_incoming_events;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface IncomingEventMapper {

    IncomingEvent map(IncomingEventDTO incomingEventDTO);

    IncomingEventDTO map(IncomingEvent incomingEvent);
}
