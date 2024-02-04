package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

record ProcessStep(UUID stepId, String name, int order, List<ProcessDataInput<?>> inputs) {}
