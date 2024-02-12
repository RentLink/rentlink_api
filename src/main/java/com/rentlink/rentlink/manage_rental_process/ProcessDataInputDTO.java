package com.rentlink.rentlink.manage_rental_process;

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
        return new ProcessDataInputDTO<>(label, ProcessDataInputType.LITERAL, isOptional, order, value, null);
    }

    public static ProcessDataInputDTO<LocalDate> createDateProcessEntryValue(
            String label, boolean isOptional, int order, LocalDate value) {
        return new ProcessDataInputDTO<>(label, ProcessDataInputType.DATE, isOptional, order, value, null);
    }

    public static ProcessDataInputDTO<String> createEnumeratedProcessEntryValue(
            String label, boolean isOptional, int order, Set<ProcessDataInputSelectValueDTO> values, String value) {
        return new ProcessDataInputDTO<>(label, ProcessDataInputType.ENUMERATED, isOptional, order, value, values);
    }
}
