package com.example.demo.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daier on 2018/3/6.
 */
@RestController
@RequestMapping("file")
public class FileController {

    @RequestMapping("toUpload")
    public ModelAndView toUpload(){
        return new ModelAndView("upload");
    }

    @RequestMapping(value = "uploadResult", method = RequestMethod.POST)
    public ModelAndView uploadResult(HttpServletRequest request,MultipartFile  file, Model model) {
//  for (MultipartFile file:files) {

        String path = "D:/temp/upload";//request.getSession().getServletContext().getRealPath("/")+"/upload/";

        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
        String fileName = file.getOriginalFilename();
        String saveFileName = new SimpleDateFormat("yyyy-DD-dd").format(new Date())+"@"+fileName;

        File upFile = new File(dir+"/"+saveFileName);
        try {
            file.transferTo(upFile);
            model.addAttribute("result","success");
            return new ModelAndView("result");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("result","error");
            return new ModelAndView("result");
        }
//   }
    }

    public void getFiles(File file,Map<String, Object> map){
        if(!file.isFile()){
            File[] files = file.listFiles();
            for(File f : files){
                getFiles(f,map);
            }
        }
        else{
            String filename = file.getName().substring(file.getName().lastIndexOf("@")+1);
            map.put(file.getName(), filename);
        }
    }

    @RequestMapping(value = "downloadList",method = RequestMethod.GET)
    public ModelAndView toDownload(Model model){
        Map<String, Object> map = new HashMap<>();
        String path = "D:/temp/upload";

        File dir = new File(path);

        getFiles(dir,map);

        model.addAttribute("files",map);
        return new ModelAndView("download");
    }

    @RequestMapping(value = "download",method = RequestMethod.GET,produces = "text/html;charset=utf-8")
    public ResponseEntity<byte[]> downloadResult(HttpServletRequest request, String filename, Model model) throws IOException{
        String path = "D:/temp/upload";

        File file = new File(path+File.separator+filename);

        HttpHeaders headers = new HttpHeaders();
        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");

        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
    }
}
