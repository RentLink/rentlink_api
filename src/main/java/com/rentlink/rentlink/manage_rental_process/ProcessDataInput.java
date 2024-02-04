package com.rentlink.rentlink.manage_rental_process;

import java.time.LocalDate;
import java.util.Set;

record ProcessDataInput<T>(
        String label,
        ProcessDataInputType type,
        boolean isOptional,
        int order,
        T value,
        Set<ProcessDataInputSelectValue> selectionValues) {

    public static ProcessDataInput<String> createLiteralProcessEntryValue(
            String label, boolean isOptional, int order, String value) {
        return new ProcessDataInput<>(label, ProcessDataInputType.LITERAL, isOptional, order, value, null);
    }

    public static ProcessDataInput<LocalDate> createDateProcessEntryValue(
            String label, boolean isOptional, int order, LocalDate value) {
        return new ProcessDataInput<>(label, ProcessDataInputType.DATE, isOptional, order, value, null);
    }

    public static ProcessDataInput<String> createEnumeratedProcessEntryValue(
            String label, boolean isOptional, int order, Set<ProcessDataInputSelectValue> values, String value) {
        return new ProcessDataInput<>(label, ProcessDataInputType.ENUMERATED, isOptional, order, value, values);
    }
}
