package com.KpApp.SpringBootApp.validator;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator {


    public static boolean validate(MultipartFile uploadedFile, Model model){
        boolean isValid = true;
        if(uploadedFile.isEmpty() || uploadedFile.getSize()==0){
            if(model!=null)
                model.addAttribute("imageError", "Please select a file");
            isValid = false;
        }
        if(!(uploadedFile.getContentType().toLowerCase().equals("image/jpg")
                || uploadedFile.getContentType().toLowerCase().equals("image/jpeg")
                || uploadedFile.getContentType().toLowerCase().equals("image/png"))){
            if(model!=null)
                model.addAttribute("imageError", "jpg/png file types are only supported");
            isValid = false;
        }
        if(uploadedFile.getSize()>41681549){
            isValid = false;
            if(model!=null)
                model.addAttribute("imageError", "file is too long");
        }
        return isValid;
    }
}

