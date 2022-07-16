package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.ssau.model.Partner;
import org.ssau.service.PartnerService;

import java.util.List;

@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
@CrossOrigin
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public List<Partner> getAll() {
        return partnerService.getAll();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public Partner getById(@PathVariable("id") Long id) {
        return partnerService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public Partner create(@RequestBody Partner partner) {
        return partnerService.create(partner);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public Partner update(@PathVariable("id") Long id, @RequestBody Partner partner) {
        return partnerService.update(id, partner);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        partnerService.delete(id);
    }
}
