package com.rentlink.rentlink.manage_rental_process;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

record ProcessDataInputDTO<T>(
        String label,
        ProcessDataInputIdentifier identifier,
        ProcessDataInputType type,
        boolean isOptional,
        int order,
        T value,
        Set<ProcessDataInputSelectValueDTO> selectionValues) {

    public static ProcessDataInputDTO<String> createLiteralProcessEntryValue(
            String label, ProcessDataInputIdentifier identifier, boolean isOptional, int order) {
        return new ProcessDataInputDTO<>(label, identifier, ProcessDataInputType.INPUT, isOptional, order, null, null);
    }

    public static ProcessDataInputDTO<LocalDate> createDateProcessEntryValue(
            String label, ProcessDataInputIdentifier identifier, boolean isOptional, int order, LocalDate value) {
        return new ProcessDataInputDTO<>(label, identifier, ProcessDataInputType.DATE, isOptional, order, value, null);
    }

    public static ProcessDataInputDTO<String> createSelectValue(
            String label,
            ProcessDataInputIdentifier identifier,
            boolean isOptional,
            int order,
            Set<ProcessDataInputSelectValueDTO> values,
            String value) {
        return new ProcessDataInputDTO<>(
                label, identifier, ProcessDataInputType.SELECT, isOptional, order, value, values);
    }

    public static ProcessDataInputDTO<List<String>> createMultiSelectValue(
            String label,
            ProcessDataInputIdentifier identifier,
            boolean isOptional,
            int order,
            Set<ProcessDataInputSelectValueDTO> values,
            List<String> value) {
        return new ProcessDataInputDTO<>(
                label, identifier, ProcessDataInputType.MULTISELECT, isOptional, order, value, values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProcessDataInputDTO<?> that = (ProcessDataInputDTO<?>) o;

        return new EqualsBuilder()
                .append(isOptional, that.isOptional)
                .append(order, that.order)
                .append(label, that.label)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(label)
                .append(type)
                .append(isOptional)
                .append(order)
                .toHashCode();
    }
}
