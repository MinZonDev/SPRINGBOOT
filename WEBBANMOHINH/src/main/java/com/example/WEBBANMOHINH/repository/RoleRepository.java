package com.example.WEBBANMOHINH.repository;

import com.example.WEBBANMOHINH.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
