package org.ssau.service;

import org.ssau.model.SupplierOperation;

import java.util.Date;
import java.util.List;

public interface SupplierOperationService {

    List<SupplierOperation> getAll();
    SupplierOperation update(Long id, SupplierOperation supplierOperation);
    SupplierOperation create(SupplierOperation supplierOperation);
    void delete(Long id);

    SupplierOperation getById(Long id);
}
