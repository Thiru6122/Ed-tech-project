package com.example.hepl_demo.controller;


import com.example.hepl_demo.model.HeplEmployee;
import com.example.hepl_demo.service.HeplEmployeeService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/file_upload")
public class HeplEmployeeController {
@Autowired
private HeplEmployeeService heplEmployeeService;


private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PostMapping("/save")
    public ResponseEntity<?> saveEmployeeData(@RequestBody HeplEmployee heplEmployee){

try{

}catch (Exception e){
    logger.error("Bad Request");
}
        return ResponseEntity.ok(heplEmployeeService.saveHeplEmployee(heplEmployee));
    }

    @GetMapping("/get")
    public  ResponseEntity<?> getEmployee(){

        List<HeplEmployee> heplEmployee = heplEmployeeService.getHeplEmployee();
        return ResponseEntity.ok(heplEmployee);
    }
    @PutMapping("/put")
    public  ResponseEntity<?> updateEmplyee(@RequestParam int id,@RequestParam String empName){

        List<HeplEmployee> heplEmployee =  heplEmployeeService.updateHeplEmployee(id,empName);
        return ResponseEntity.ok(heplEmployee);
    }
    @DeleteMapping("/delete")
    public  ResponseEntity<?> deleteEmplyee(@RequestParam int id){
        heplEmployeeService.deleteEmployee(id);
        HeplEmployee heplEmployee =new HeplEmployee();
        return ResponseEntity.ok(heplEmployee);
    }


    private static final String ERROR_MESSAGE = "File too Large.";

    private static final long FILE_SIZE = 250000L;

    @PostMapping(value="upload")
    public ApiResponse uploadFile(@ModelAttribute MultipartFile file){

        logger.info("File Upload "+file.getOriginalFilename());
        String result = null ;
        if (!file.isEmpty()) {
            if(file.getSize()>FILE_SIZE){
                System.out.println("File upload:"+"file too large");
                logger.info("File too Large. ");
            }
            result =   heplEmployeeService.uploadFileToFolder(file,"hepl_employee_note");
        }
        ApiResponse responce = new ApiResponse();
        responce.setStatus(true);
        responce.setData(result);
        return  responce;
    }

    public static  final long FILE_SIZES = 2000000l;

    @PostMapping(value = "/uploadNew")
    public ApiResponse fileUpload(@ModelAttribute MultipartFile file){
        String result=null;
        ApiResponse apiResponse = new ApiResponse();
        if(!file.isEmpty()){
            if(file.getSize()>FILE_SIZE)
            {
            logger.error("file is too large");
        }else{ try {
            this.uploadFile(file, "Employee");
            apiResponse.status=true;
        }catch (IOException e){
            e.printStackTrace();
        }
        }}



        return apiResponse;
    }



    public String uploadFile(MultipartFile file, String folderName) throws IOException {
Path filelocation = null;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path folderPath = Paths.get(System.getProperty("user.home")+File.separator+folderName);
Path filePath = Paths.get(System.getProperty("user.home")+File.separator+folderName,File.separator,fileName);
        if(!Files.exists(folderPath)){
            Set<PosixFilePermission> permissionSet = PosixFilePermissions.fromString("rwxr--r--");
            FileAttribute<Set<PosixFilePermission>> fileAttribute =PosixFilePermissions.asFileAttribute(permissionSet);

            Files.createDirectories(folderPath);

logger.info("directory created succesfully", LocalDateTime.now());
        }
        if(!Files.exists(filePath)){
            filelocation = filePath;
        }else{
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-MM"));
            fileName = fileName+" "+time;
            filelocation= Paths.get(System.getProperty("user.home")+File.separator+folderName,File.separator,fileName);
        }

try{

    Files.copy(file.getInputStream(),filelocation,StandardCopyOption.REPLACE_EXISTING);

    if(logger.isWarnEnabled()){
        logger.warn("cant able to decode multipart file");
    }
    logger.info("file saved on file location: "+ filelocation , LocalDateTime.now());
}catch(IOException e){e.printStackTrace();}

return folderName+"/"+fileName;
    }
}
