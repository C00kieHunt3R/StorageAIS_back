package org.ssau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssau.model.ConsumerOperation;
import org.ssau.model.Partner;
import org.ssau.model.Product;
import org.ssau.repository.ConsumerOperationRepository;
import org.ssau.service.ConsumerOperationService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional(readOnly = true)
public class ConsumerOperationServiceImpl implements ConsumerOperationService {

    @Autowired
    ConsumerOperationRepository consumerOperationRepository;

    @Override
    public ConsumerOperation getById(Long id) {
        AtomicReference<ConsumerOperation> operationRet = new AtomicReference<>();
        consumerOperationRepository.findById(id).ifPresent(operationRet::set);
        return operationRet.get();
    }

    @Override
    public List<ConsumerOperation> getAll() {
        return consumerOperationRepository.findAll();
    }

    @Override
    @Transactional
    public ConsumerOperation update(Long id, ConsumerOperation consumerOperation) {
        AtomicReference<ConsumerOperation> operationRet = new AtomicReference<>();
        consumerOperationRepository.findById(id).ifPresent(p -> {
            consumerOperation.setId(p.getId());
            operationRet.set(consumerOperationRepository.saveAndFlush(consumerOperation));
        });
        return operationRet.get();
    }

    @Override
    @Transactional
    public ConsumerOperation create(ConsumerOperation consumerOperation) {
        consumerOperation.setId(null);
        return consumerOperationRepository.saveAndFlush(consumerOperation);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        consumerOperationRepository.deleteById(id);
    }
}
