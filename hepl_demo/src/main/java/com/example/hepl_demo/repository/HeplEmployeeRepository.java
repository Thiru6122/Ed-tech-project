package com.example.hepl_demo.repository;

import com.example.hepl_demo.model.HeplEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HeplEmployeeRepository extends JpaRepository<HeplEmployee, Long> {



@Query(value = "select * from hepl_employee",nativeQuery = true)
List<HeplEmployee> findAllBy(@Param("id") Long id);


@Query(value = "select * from hepl_employee where id=?1 ", nativeQuery = true)
    HeplEmployee findAllById(@Param("id") Long id);
}
