package com.example.demo.controller;

import com.example.demo.api.CategoryApi;
import com.example.demo.api.CommonApi;
import com.example.demo.api.ItemApi;
import com.example.demo.model.Item;
import com.example.demo.staticUtility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CategoryApi categoryApi;

    @Autowired
    CommonApi commonApi;

    @Autowired
    ItemApi itemApi;

    @GetMapping("/")
    public String adminIndex(){
        return "/admin/index";
    }

    @GetMapping("/addItem")
    public String addItem(Model model, HttpSession httpSession){
        commonApi.setCommonModel(httpSession,model);
        return "/admin/addItem";
    }

    @PostMapping("/addItemProcess")
    public String addItemProcess(Item item, @RequestParam("file")MultipartFile file, String categoryPart){
        item.setCategory(categoryApi.getCategoryByPart(categoryPart));
        item.setRelease(DateUtil.getTodayDate());
        Item saveItem = itemApi.save(item);
        Long imgNum = saveItem.getId();
        upLoad(file, imgNum);
        saveItem.setImg('"'+"../img/merchandise/"+imgNum+".PNG"+'"');
        itemApi.save(saveItem);
        return "/admin/index";
    }

    public void upLoad( MultipartFile file, Long imgNum){
        if(file.isEmpty()){
            System.out.println("파일이 없어요");
            //파일 없을때 예외처리
        }
        try{
            byte[] bytes = file.getBytes();
            String fileName = Long.toString(imgNum)+".PNG";
            Path path = Paths.get("D://Danymall/src/main/resources/static/img/merchandise//"+fileName);
            Files.write(path, bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
