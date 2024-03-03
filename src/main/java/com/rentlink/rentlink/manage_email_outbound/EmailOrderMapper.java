package com.rentlink.rentlink.manage_email_outbound;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface EmailOrderMapper {

    InternalEmailOrderDTO map(EmailOrder emailOrder);

    @Mapping(target = "id", ignore = true)
    EmailOrder map(InternalEmailOrderDTO emailOrder);
}
