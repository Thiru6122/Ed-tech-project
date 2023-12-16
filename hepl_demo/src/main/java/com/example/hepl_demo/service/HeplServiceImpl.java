package com.example.hepl_demo.service;



import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import com.example.hepl_demo.model.HeplEmployee;
import com.example.hepl_demo.repository.HeplEmployeeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HeplServiceImpl implements  HeplEmployeeService{


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HeplEmployeeRepository heplEmployeeRepository;

    public HeplEmployee saveHeplEmployee(HeplEmployee heplEmployee){

        if(heplEmployee != null){
            return heplEmployeeRepository.save(heplEmployee);

        }else{
            return heplEmployee;
        }

    }


    public List<HeplEmployee> getHeplEmployee(){

            return heplEmployeeRepository.findAll();


    }


    public List<HeplEmployee> updateHeplEmployee(int id, String empName){
        List<HeplEmployee> list = new ArrayList<HeplEmployee>();

        HeplEmployee heplEmployee = new HeplEmployee();

        try{
            if(id != 0) {
                Optional<HeplEmployee> heplEmployee1 = heplEmployeeRepository.findById((long)id);
                if(!heplEmployee1.isEmpty()){
                    heplEmployee1.get().setEmpName1(empName);
                    heplEmployeeRepository.save(heplEmployee1.get());
                }else{


                    list.add(heplEmployee);
                    return list;
                }

            }else{

                list.add(heplEmployee);
            }
        }catch (Exception e){
            list.add(heplEmployee);
        }


        return list;



    }


    public void deleteEmployee(int id){
         heplEmployeeRepository.deleteById((long)id);
    }



    public String uploadFileToFolder( MultipartFile file, String folderName){

        Path fileLocation = null;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());


        Path path = Paths.get(System.getProperty("user.home")+ File.separator+folderName);


        if(!Files.exists(path)){
            try {
                Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxr--r--");
                FileAttribute<Set<PosixFilePermission>> fileAttributes = PosixFilePermissions.asFileAttribute(permissions);
                Files.createDirectory(path);
                logger.info("Initializing upload Directory" + System.getProperty("user.home")+ File.separator+folderName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path filePath = Paths.get(System.getProperty("user.home")+File.separator+folderName, File.separator,fileName);
        if(!Files.exists(filePath)){
            fileLocation = filePath;
        }else{
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm"));
            fileName = time+fileName;
            fileLocation = Paths.get(System.getProperty("user.home")+ File.separator+folderName, File.separator,fileName);
        }

        try {
            Files.copy(file.getInputStream(),fileLocation, StandardCopyOption.REPLACE_EXISTING);

            if (logger.isWarnEnabled()) {
                logger.warn("Could not decode multipart item using platform default");
            }

            logger.info("File saved on server {} and savedTime {}",fileLocation.toString(), LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  folderName+"/"+fileName;
    }

}
