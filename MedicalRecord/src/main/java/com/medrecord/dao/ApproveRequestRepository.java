package com.medrecord.dao;

import com.medrecord.Entity.ApproveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApproveRequestRepositery extends JpaRepository<ApproveRequest,Integer> {
}
