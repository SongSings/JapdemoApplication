package com.datajpa.jpademo.web;

import com.datajpa.jpademo.Util.IPUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/")
public class UploadFileController {

    private String UPLOADED_FOLDER  = "D:\\";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/xiangqi")
    public String xiangqi() {
        return "xiangqiGame";
    }

    @GetMapping("/feixue")
    public String feixue() {
        return "feixue";
    }

    @GetMapping("/biaobai")
    public String biaobai() {
        return "biaobai";
    }


    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @RequestMapping("getIp")
    public String toIndex(HttpServletRequest request){
        //获取访客ip
        String ip = IPUtil.getIpAddr(request);
        System.out.printf("当前访客的ip是"+ip);
        return "redirect:book";
    }
}
