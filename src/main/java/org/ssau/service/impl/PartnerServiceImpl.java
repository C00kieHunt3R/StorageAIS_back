package org.ssau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssau.model.Partner;
import org.ssau.model.Product;
import org.ssau.repository.PartnerRepository;
import org.ssau.service.PartnerService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional(readOnly = true)
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<Partner> getAll() {
        return partnerRepository.findAll();
    }

    @Override
    public Partner getById(Long id) {
        AtomicReference<Partner> partnerRet = new AtomicReference<>();
        partnerRepository.findById(id).ifPresent(partnerRet::set);
        return partnerRet.get();
    }

    @Override
    @Transactional
    public Partner update(Long id, Partner partner) {
        AtomicReference<Partner> partnerRet = new AtomicReference<>();
        partnerRepository.findById(id).ifPresent(p -> {
            partner.setId(p.getId());
            partnerRet.set(partnerRepository.saveAndFlush(partner));
        });
        return partnerRet.get();
    }

    @Override
    @Transactional
    public Partner create(Partner partner) {
        partner.setId(null);
        return partnerRepository.saveAndFlush(partner);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }
}
