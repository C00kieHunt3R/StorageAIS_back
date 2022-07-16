package org.ssau.service;

import org.ssau.model.Partner;

import java.util.List;

public interface PartnerService {
    List<Partner> getAll();
    Partner getById(Long id);
    Partner update(Long id, Partner partner);
    Partner create(Partner partner);
    void delete(Long id);
}
