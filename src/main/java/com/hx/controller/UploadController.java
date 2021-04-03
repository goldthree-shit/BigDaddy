package com.hx.controller;

import com.hx.pojo.User;
import com.hx.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping("/")
    public String index(Model model,HttpSession session) {
        model.addAttribute("state","选择要上传的文件");
        return "upload";
    }

    @RequestMapping("/begin_upload")
    public String uplodaContoller(@RequestParam("fileNames") MultipartFile[] files,
                                   HttpSession session,
                                   Model model) throws IOException {

        Object presentUser = session.getAttribute("PresentUser");
        if(presentUser == null) {
            return "login.html";
        }

        System.out.println(presentUser);

        boolean success = fileUploadService.keepFiles(files, (User)presentUser);

        if(success) {
            model.addAttribute("state","上传成功可以继续上传");
            return "upload";
        }
        model.addAttribute("state","上传失败请重新尝试");
        return "upload";
    }
}
