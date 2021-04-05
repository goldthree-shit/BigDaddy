##启动方法 
    先使用create.sql中的sql命令创建数据库
    在com.hx.BigdaddyApplication中启动java程序
    已实现的功能。允许可以多文件上传。
    上传前检测是否有登录。登录，注册。按照当前的用户分区存储。

##基本需求分析：
 #####1.上传（多文件，带进度条）
 #####2.需要验证登录
    2.1登陆功能
    2.2注册功能

###创建springboot项目。

我采用的是MVC的设计模式进行的设计

首先创建数据库在可使用根目录下的create.sql
有两张表。分别为用户表和用户文件表。
    user表三个字段分别为,uid,username,password。用于存储用户的基本信息
    user_file表有四个字段，存储,fid,uid(对应着哪个用户上传的)，
filename,destpath(存储位置)
创建相应的实体类

由于不擅长前端。只把最基本的上传页面的样子做了出来^_^

上传的控制层UploadController
```java
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
```
其中调用了业务层(FileUploadService)完成具体的上传逻辑
```java
package com.hx.service;

import com.hx.dao.UserFileDao;
import com.hx.pojo.User;
import com.hx.pojo.UserFile;
import com.hx.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Autowired
    UserFileDao userFileDao;
    private final String UPLOAD_PATH = "File/upload/";

    public boolean keepFiles(MultipartFile[] files, User user) throws IOException {
        System.out.println(user);
        for (MultipartFile file : files) {
            // 判断文件是否有内容
            if (file.isEmpty()) {
                System.out.println("该文件无任何内容!!!");
                return false;
            }
            String fileName = FileNameUtils.getFileName(file);
            System.out.println("fileName:" + fileName);
            Path directory = Paths.get(UPLOAD_PATH + user.getUserName());
            // 判断目录是否存在，不存在创建
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            try {
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, directory.resolve(fileName));
                userFileDao.saveUserFile(new UserFile(null,user.getUid(),fileName,UPLOAD_PATH+'/'+fileName));
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
```
文件的存储位置为根目录下的File文件夹下的username文件夹。如果不存在会自动创建

####完成登录校验：
       不太擅长前端。所以每次上传前只能在后台存储一个session来验证有没有登陆过
    登录的controller会调用service层，service又会调用dao层进行查询有没有登录
    使用mybatis进行dao层的设计。
       会提示是由于用户名密码错误还是该用户名未被注册。

####注册
    注册会先查询用户名有否有被注册过。如果没有才能允许注册。
   
有个utils因为MultipartFile对象的方法getOriginalFilename会根据使用的浏览器不同有不同的返回值。
  故需要进行特殊处理。并且同时会在文件名后加上时间戳防止重复文件不能上传的不提。
