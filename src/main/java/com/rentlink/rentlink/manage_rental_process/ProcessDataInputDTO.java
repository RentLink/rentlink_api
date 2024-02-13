package com.rentlink.rentlink.manage_rental_process;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.util.Set;

record ProcessDataInputDTO<T>(
        String label,
        ProcessDataInputType type,
        boolean isOptional,
        int order,
        T value,
        Set<ProcessDataInputSelectValueDTO> selectionValues) {

    public static ProcessDataInputDTO<String> createLiteralProcessEntryValue(
            String label, boolean isOptional, int order, String value) {
        return new ProcessDataInputDTO<>(label, ProcessDataInputType.INPUT, isOptional, order, value, null);
    }

    public static ProcessDataInputDTO<LocalDate> createDateProcessEntryValue(
            String label, boolean isOptional, int order, LocalDate value) {
        return new ProcessDataInputDTO<>(label, ProcessDataInputType.DATE, isOptional, order, value, null);
    }

    public static ProcessDataInputDTO<String> createEnumeratedProcessEntryValue(
            String label, boolean isOptional, int order, Set<ProcessDataInputSelectValueDTO> values, String value) {
        return new ProcessDataInputDTO<>(label, ProcessDataInputType.SELECT, isOptional, order, value, values);
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
                .append(selectionValues, that.selectionValues)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(label)
                .append(type)
                .append(isOptional)
                .append(order)
                .append(selectionValues)
                .toHashCode();
    }
}
