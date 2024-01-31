package org.assessment.payment.repo;

import org.assessment.payment.entity.FeeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeTransactionRepository extends JpaRepository<FeeTransaction, Long> {
}
