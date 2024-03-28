package com.rentlink.rentlink.manage_incoming_events;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomingEventManagement implements IncomingEventInternalAPI, IncomingEventExternalAPI {

    private final IncomingEventRepository incomingEventRepository;

    private final IncomingEventMapper incomingEventMapper;

    @Transactional
    @Override
    public void createIncomingEvent(IncomingEventDTO incomingEventDTO) {
        log.info("Creating incoming event: {}", incomingEventDTO);
        IncomingEvent incomingEvent = incomingEventMapper.map(incomingEventDTO);
        incomingEventRepository.save(incomingEvent);
    }

    @Transactional(readOnly = true)
    @Override
    public List<IncomingEventDTO> getIncomingEvents(UUID accountId) {
        log.info("Getting incoming events for account: {}", accountId);
        return incomingEventRepository
                .findByAccountId(accountId)
                .map(incomingEventMapper::map)
                .collect(toList());
    }
}
