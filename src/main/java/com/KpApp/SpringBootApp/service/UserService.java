package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Equipment;
import com.KpApp.SpringBootApp.model.Equipment_type;
import com.KpApp.SpringBootApp.model.Role;
import com.KpApp.SpringBootApp.model.User;
import com.KpApp.SpringBootApp.repo.UserRepo;
import com.KpApp.SpringBootApp.validator.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Value("${upload_path}")
    private String upload_path;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public boolean isExist(User user){
        return userRepo.findUsersById(user.getId()) != null;
    }

    public Iterable<User> findUsers(String id) {
        Iterable<User> users;
        if(id!=null && !id.isEmpty()){
            try {
                long idL = Long.parseLong(id);
                ArrayList<User> userList = new ArrayList<>();
                userList.add(userRepo.findUsersById(idL));
                users = userList;
            }catch (Exception e){
                users = new ArrayList<User>();
            }
        }else {
            users = userRepo.findAll();
        }
        return users;
    }
    public User findUser(User user) {
        User byId = userRepo.findUsersById(user.getId());
        return byId;
    }

    public User findUser(String username) {
        User byId = userRepo.findByUsername(username);
        return byId;
    }

    public boolean addUser(User user, String user_role_enum, MultipartFile file) {
        if(user==null) return false;

        if(isExist(user)){
            System.out.println("User AlREADY EXISTS");
            return false;
        }

        try{
            user.setRoles(Collections.singleton(Role.valueOf(user_role_enum)));
            if(file !=null && !file.isEmpty()){
                if(ImageValidator.validate(file,null)){
                    saveFile(user,file);
                }else{
                    return false;
                }
            }
            userRepo.save(user);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean addUser(User user){
        if(user==null) return false;
        if(isExist(user)){
            System.out.println("User AlREADY EXISTS");
            return false;
        }
        userRepo.save(user);
        return true;
    }

    public boolean editUser(User user,String user_role_enum,MultipartFile file){
        if(user==null) return false;

        if(!isExist(user)){
            System.out.println("User NOT EXISTS");
            return false;
        }
        try {
            if(user_role_enum!=null){
                user.setRoles(Collections.singleton(Role.valueOf(user_role_enum)));
            }
            if(file !=null && !file.isEmpty()){
                if(ImageValidator.validate(file,null)){
                    saveFile(user,file);
                }else{
                    return false;
                }
            }
            userRepo.save(user);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean editUser(User user){
        if(user==null) return false;

        if(!isExist(user)){
            System.out.println("User NOT EXISTS");
            return false;
        }
        try {
            userRepo.save(user);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean deleteUser(User user){
        if(user==null) return false;

        if(!isExist(user)){
            System.out.println("User NOT EXISTS");
            return false;
        }
        userRepo.delete(user);
        return true;
    }

    public User saveFile(User user,MultipartFile file) throws IOException {
        if(file!=null){
            File up_dir = new File(upload_path);
            if(!up_dir.exists()){
                if(!up_dir.mkdir()){
                    throw new IOException("Unable to create directory");
                }
            }
            String uuid = UUID.randomUUID().toString();
            String result_photo_name = uuid+"."+user.getUsername()+"."+file.getOriginalFilename();
            user.setPhoto_name(result_photo_name);
            file.transferTo(Paths.get(upload_path+result_photo_name));
            return user;
        }
        return null;
    }

    public InputStream getFile(Long id, String filename){
        InputStream is;
        try {
            is = new BufferedInputStream(new FileInputStream(upload_path+filename));
        } catch (IOException ex) {
            return null;
        }
        return is;
    }


}
