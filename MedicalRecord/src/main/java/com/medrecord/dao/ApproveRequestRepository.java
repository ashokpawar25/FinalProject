package com.medrecord.dao;

import com.medrecord.Entity.ApproveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApproveRequestRepository extends JpaRepository<ApproveRequest,Integer> {
}
