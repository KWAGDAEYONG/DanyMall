package com.example.demo.controller;

import com.example.demo.api.*;
import com.example.demo.model.Category;
import com.example.demo.model.Item;
import com.example.demo.model.Merchandise;
import com.example.demo.model.Sold;
import com.example.demo.staticUtility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private int mer_num = 0;

    @Autowired
    CategoryApi categoryApi;

    @Autowired
    CommonApi commonApi;

    @Autowired
    ItemApi itemApi;

    @Autowired
    MerchandiseApi merchandiseApi;

    @Autowired
    SoldApi soldApi;

    @Autowired
    UserApi userApi;


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
        //아이템 추가
        item.setCategory(categoryApi.getCategoryByPart(categoryPart));
        item.setRelease(DateUtil.getTodayDate());
        Item saveItem = itemApi.save(item);
        Long imgNum = saveItem.getId();
        upLoad(file, imgNum);
        saveItem.setImg('"' + "D:danymallimg\\" + imgNum + ".PNG" + '"');
        itemApi.save(saveItem);

        //상품 추가
        String [] colors = item.getColor().split(",");
        String [] sizes = item.getSize().split(",");

        for(String color : colors){
            for(String size : sizes){
                Merchandise merchandise = new Merchandise();
                merchandise.setColor(color);
                merchandise.setSize(size);
                merchandise.setItem(item);
                merchandise.setAmount(0);

                mer_num = mer_num+1;

                merchandise.setNumber(item.getId()+"_"+mer_num);

                merchandiseApi.addMerchandise(merchandise);
            }
        }
        mer_num = 0;

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

    @GetMapping("/delivery")
    public String delivery(Model model){
        model.addAttribute("soldList",soldApi.getSoldMerchandiseList());

        return "/admin/delivery";
    }

    @PostMapping("/updateDelivery")
    public String updateDelivery(Long id[], String state[]){
        for(int i = 0; i<id.length; i++){
            Sold sold = soldApi.getSold(id[i]);
            sold.updateState(state[i]);
            //update문
            soldApi.save(sold);
        }
        return "redirect:/admin/";
    }

    @GetMapping("/amount")
    public String amount(Model model){
        model.addAttribute("merList",merchandiseApi.getMerchandiseList());
        return "/admin/amount";
    }

    @PostMapping("/updateAmount")
    public String updateAmount(Long id[], int amount[]){
        for(int i = 0; i<id.length; i++){
            Merchandise merchandise = merchandiseApi.getMerchandise(id[i]);
            merchandise.updateAmount(amount[i]);
            //update문
            merchandiseApi.addMerchandise(merchandise);
        }
        return "redirect:/admin/";
    }

    @GetMapping("/sales")
    public String sales(Model model){
        List<Merchandise> merchandises = merchandiseApi.getMerchandiseList();

        //판매량 순 정렬
        Collections.sort(merchandises);

        model.addAttribute("salesRank",merchandises);

        List<Sold> soldList = soldApi.getSoldMerchandiseList();

        int income = 0;

        for(Sold sold : soldList){
            income += sold.getSoldMerchandise().getItem().getPrice();
        }

        model.addAttribute("income",income);
        model.addAttribute("itemList",itemApi.findAll());
        return "/admin/sales";

    }

    @GetMapping("/salesForDate")
    public String salesForDate(Date date1, Date date2, Model model){
        List<Sold> soldList = soldApi.getSoldByDate(date1,date2);
        List<Merchandise> merchandiseList = new ArrayList<Merchandise>();

        int income = 0;

        for(Sold sold : soldList){
            Merchandise merchandise = sold.getSoldMerchandise();
            if(merchandiseList.isEmpty()){
                merchandiseList.add(merchandise);
            }else{
                if(duplicateChecker(merchandiseList, merchandise)){
                    merchandiseList.add(merchandise);
                }
            }
            income += sold.getSoldMerchandise().getItem().getPrice();
        }

        Collections.sort(merchandiseList);
        model.addAttribute("salesRank", merchandiseList);
        model.addAttribute("goTotal","goTotal");
        model.addAttribute("income",income);
        return "/admin/sales";
    }

    @PostMapping("/updatePrice")
    public String updatePrice(Long id[], int price[]){
        for(int i = 0; i<id.length; i++){
            Item item = itemApi.findOne(id[i]);
            item.updatePrice(price[i]);
            itemApi.save(item);
        }
        return "redirect:/admin/sales";
    }

    @GetMapping("/stopSale/{id}")
    public String stopSale(@PathVariable Long id){
        Merchandise merchandise = merchandiseApi.getMerchandise(id);

        merchandise.setStopSale(true);
        merchandiseApi.addMerchandise(merchandise);

        return "redirect:/admin/sales";
    }


    public boolean duplicateChecker(List<Merchandise> merchandises, Merchandise merchandise){
        for(int i = 0; i<merchandises.size(); i++){
            if(merchandises.get(i).equals(merchandise)){
                return false;
            }
        }
        return true;
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
