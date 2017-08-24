package com.example.demo.controller;

import com.example.demo.api.CategoryApi;
import com.example.demo.api.CommonApi;
import com.example.demo.api.ItemApi;
import com.example.demo.model.Category;
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
import java.util.List;

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
    public String adminIndex() {
        return "/admin/index";
    }

    @GetMapping("/addItem")
    public String addItem(Model model, HttpSession httpSession) {
        commonApi.setCommonModel(httpSession, model);
        return "/admin/addItem";
    }

    @PostMapping("/addItemProcess")
    public String addItemProcess(Item item, @RequestParam("file") MultipartFile file, String categoryPart) {
        item.setCategory(categoryApi.getCategoryByPart(categoryPart));
        item.setRelease(DateUtil.getTodayDate());
        Item saveItem = itemApi.save(item);
        Long imgNum = saveItem.getId();
        upLoad(file, imgNum);
        saveItem.setImg('"' + "D:danymallimg\\" + imgNum + ".PNG" + '"');
        itemApi.save(saveItem);
        return "/admin/index";
    }

    @GetMapping("/category")
    public String category(HttpSession httpSession, Model model) {
        commonApi.setCommonModel(httpSession, model);
        return "/admin/category";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(String parts[]) {
        //만약 어떤 카테고리를 제거하면, 그 카테고리에 포함되어있던 아이템들은 어떻게 처리할래?
        List<Category> categories = categoryApi.getCategoryList();

        for (String part : parts) {
            if (containCategory(part, categories)) {
                continue;
            }else {
                Category category = new Category();
                category.setPart(part);
                categoryApi.addCategory(category);
            }
        }

        for(Category isRemoveTarget : categories){
            removeCateogry(isRemoveTarget, parts);
        }


        return "/admin/index";
    }

    public void removeCateogry(Category category, String parts[]){
        for(String part : parts){
            if(category.getPart().equals(part)){
                return;
            }
        }
        categoryApi.remove(category);
    }


    public boolean containCategory(String part, List<Category> categories) {

        for (Category category : categories) {
            if (category.getPart().equals(part)) {
                return true;
            }
        }
        return false;
    }



    public void upLoad(MultipartFile file, Long imgNum) {
        if (file.isEmpty()) {
            System.out.println("파일이 없어요");
            //파일 없을때 예외처리
        }
        try {
            byte[] bytes = file.getBytes();
            String fileName = Long.toString(imgNum) + ".PNG";
            Path path = Paths.get("D://danymallimg//" + fileName);
            Files.write(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
