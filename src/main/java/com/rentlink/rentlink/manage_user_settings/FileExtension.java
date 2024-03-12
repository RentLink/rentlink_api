package com.rentlink.rentlink.manage_user_settings;

public enum FileExtension {
    PDF,
    DOCX,
    DOC,
    XLSX,
    XLS,
    OTHER;

    public static FileExtension fromString(String extension) {
        return switch (extension) {
            case "pdf" -> PDF;
            case "docx" -> DOCX;
            case "doc" -> DOC;
            case "xlsx" -> XLSX;
            case "xls" -> XLS;
            default -> OTHER;
        };
    }
}
