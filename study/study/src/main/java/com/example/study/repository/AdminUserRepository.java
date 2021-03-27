package com.example.study.repository;

import com.example.study.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//아이디에 해당하는 Long타입
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {
}
