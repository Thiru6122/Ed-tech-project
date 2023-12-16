package com.example.hepl_file_upload.repository;


import com.example.hepl_file_upload.model.Hepl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Hepl, Long> {
}
