package com.blogApplication.blogapis.services.impl;

import com.blogApplication.blogapis.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String Path, MultipartFile file) throws IOException {

        //File Name
        String fileName=file.getOriginalFilename();
        //abc.png

        //random name generate file
        String randomID= UUID.randomUUID().toString();
        String file1=randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

        //Full path
        String filePath=Path+ File.separator+file1;

        //create Folder if not created
        File f=new File(Path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return file1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullPath);
        //db logic to return inputstream
        return is;
    }
}
