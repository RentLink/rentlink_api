package com.rentlink.rentlink.manage_rental_process.side_effects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoOpSideEffect implements RentalProcessStepSideEffect {

    @Override
    public void execute(RentalProcessStepSideEffectContext context) {
        log.info("No-op side effect for step");
    }
}
