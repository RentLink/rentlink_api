package com.rentlink.rentlink.manage_archive;

import java.util.List;
import java.util.UUID;

public interface ArchiveExternalAPI {

    void createArchiveRecordFromRentalProcess(UUID accountId, UUID rentalProcessId);

    List<ArchiveRecordDTO> getArchiveRecords(UUID accountId);
}
