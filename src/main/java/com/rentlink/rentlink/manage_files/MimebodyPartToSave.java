package com.rentlink.rentlink.manage_files;

import jakarta.mail.internet.MimeBodyPart;

public record MimebodyPartToSave(String subdirectory, MimeBodyPart mimeBodyPart) {}
