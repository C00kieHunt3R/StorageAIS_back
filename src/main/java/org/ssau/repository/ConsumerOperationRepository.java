package org.ssau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssau.model.ConsumerOperation;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsumerOperationRepository extends JpaRepository<ConsumerOperation, Long> {

}
