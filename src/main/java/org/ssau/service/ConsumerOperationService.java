package org.ssau.service;

import org.ssau.model.ConsumerOperation;

import java.util.Date;
import java.util.List;

public interface ConsumerOperationService {

    ConsumerOperation getById(Long id);

    List<ConsumerOperation> getAll();

    ConsumerOperation update(Long id, ConsumerOperation consumerOperation);
    ConsumerOperation create(ConsumerOperation consumerOperation);
    void delete(Long id);

}
