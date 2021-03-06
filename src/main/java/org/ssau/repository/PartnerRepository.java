package org.ssau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssau.model.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
