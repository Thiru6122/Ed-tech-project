package com.example.hepl_demo.service;

import com.example.hepl_demo.model.HeplEmployee;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface HeplEmployeeService {
    public HeplEmployee saveHeplEmployee(HeplEmployee heplEmployee);

    public List<HeplEmployee> getHeplEmployee();

    public List<HeplEmployee> updateHeplEmployee(int id, String empName);
    public String uploadFileToFolder(MultipartFile file, String foulderName);

    public void deleteEmployee(int id);
}
