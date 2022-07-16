package org.ssau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssau.model.SupplierOperation;
import org.ssau.repository.SupplierOperationRepository;
import org.ssau.service.SupplierOperationService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional(readOnly = true)
public class SupplierOperationServiceImpl implements SupplierOperationService {

    @Autowired
    SupplierOperationRepository supplierOperationRepository;

    @Override
    public List<SupplierOperation> getAll() {
        return supplierOperationRepository.findAll();
    }

    @Override
    @Transactional
    public SupplierOperation update(Long id, SupplierOperation supplierOperation) {
        AtomicReference<SupplierOperation> operationRet = new AtomicReference<>();
        supplierOperationRepository.findById(id).ifPresent(p -> {
            supplierOperation.setId(p.getId());
            operationRet.set(supplierOperationRepository.saveAndFlush(supplierOperation));
        });
        return operationRet.get();
    }

    @Override
    @Transactional
    public SupplierOperation create(SupplierOperation supplierOperation) {
        supplierOperation.setId(null);
        return supplierOperationRepository.saveAndFlush(supplierOperation);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        supplierOperationRepository.deleteById(id);
    }

    @Override
    public SupplierOperation getById(Long id) {
        AtomicReference<SupplierOperation> operationRet = new AtomicReference<>();
        supplierOperationRepository.findById(id).ifPresent(operationRet::set);
        return operationRet.get();
    }
}
