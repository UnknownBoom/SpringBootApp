package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Equipment;
import com.KpApp.SpringBootApp.model.User;
import com.KpApp.SpringBootApp.service.EquipmentService;
import com.KpApp.SpringBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("table/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser(String id, Model model){
        Iterable<User> users = userService.findUsers(id);
        model.addAttribute("users",users);
        return "user";

    }
    @PostMapping("/add")
    public String addUser(User user, @RequestParam(name = "user_role_enum") String user_role_enum,
                               @RequestParam(required = false,name="user_photo") MultipartFile file,
                               Model model){
        userService.addUser(user,user_role_enum,file);
        return "redirect:/table/user";
    }

    @PostMapping("/edit")
    public String editUser(User user,@RequestParam(name = "user_role_enum",required = false) String user_role_enum,
                                @RequestParam(name="user_photo",required = false) MultipartFile file,
                                Model model){
        userService.editUser(user,user_role_enum,file);
        return "redirect:/table/user";
    }

    @PostMapping("/delete")
    public String deleteUser(User user,Model model){
        userService.deleteUser(user);
        return "redirect:/table/user";
    }

    @GetMapping("/{id}/{file_name}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("file_name") String fileName,
                                                       HttpServletResponse response,
                                                       @PathVariable Long id) throws IOException {
        InputStream photo_is = userService.getFile(id, fileName);
        if(photo_is==null){
            throw new IOException("IOError open file to input stream");
        }
        try{
            return ResponseEntity.ok()
                    .contentLength(photo_is.available())
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(photo_is));
        }catch (IOException e){
            throw new IOException("IOError writing file to output stream");
        }
    }
}
