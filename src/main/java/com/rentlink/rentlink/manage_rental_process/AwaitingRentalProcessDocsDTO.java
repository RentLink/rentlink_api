package com.rentlink.rentlink.manage_rental_process;

import com.rentlink.rentlink.common.DocumentDTO;
import java.util.List;

public record AwaitingRentalProcessDocsDTO(List<DocumentDTO> documentsFromTenant) {}
