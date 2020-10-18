package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.User;
import com.KpApp.SpringBootApp.repo.UserRepo;
import com.KpApp.SpringBootApp.service.UserService;
import com.KpApp.SpringBootApp.service.UsersFileService;
import com.KpApp.SpringBootApp.validator.ImageValidator;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/upload/users")
public class UsersFileController {

    @Autowired
    private UserRepo usersRepo;

    @Autowired
    private UsersFileService usersFileService;

    @Value("${upload_path}")
    private String upload_path;

    @Autowired
    private UserService userService;

    @PostMapping
    public  String HandleFileUpload(@AuthenticationPrincipal User user,
                                    @RequestParam MultipartFile file,
                                    Model model) throws IOException {
        userService.saveFile(user,file);
        return "redirect:/user_profile";
    }


    @GetMapping("/{id}/{file_name}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFile(@PathVariable Long id,
                        @PathVariable("file_name") String fileName,
                        HttpServletResponse response) throws IOException {
        InputStream photo_is = usersFileService.getFile(id, fileName);
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
