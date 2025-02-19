package com.testtask.innowise.repository;

import com.testtask.innowise.model.Addres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresRepository extends JpaRepository<Addres, Long> {
}
