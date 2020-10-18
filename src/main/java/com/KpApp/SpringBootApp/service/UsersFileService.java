package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.User;
import com.KpApp.SpringBootApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class UsersFileService {

    @Autowired
    private UserRepo usersRepo;

    @Autowired
    private UserService userService;


    @Value("${upload_path}")
    private String upload_path;

    public User saveFile(MultipartFile file, User user) throws IOException, SQLException {
        if(file!=null){
            File up_dir = new File(upload_path);
            if(!up_dir.exists()){
                if(!up_dir.mkdir()){
                    throw new IOException("Unable to create directory");
                }
            }
            String uuid = UUID.randomUUID().toString();
            String result_photo_name = uuid+"."+file.getOriginalFilename();
            user.setPhoto_name(result_photo_name);
            file.transferTo(Paths.get(upload_path+result_photo_name));
            return user;
        }
        return null;
    }


    public InputStream getFile(Long id, String fileName) throws IOException {
        InputStream is;
        try {
            is = new BufferedInputStream(new FileInputStream(upload_path+fileName));
        } catch (IOException ex) {
            return null;
        }
        return is;
    }

}
