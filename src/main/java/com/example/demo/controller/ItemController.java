package com.example.demo.controller;

import com.example.demo.api.*;
import com.example.demo.criteria.PageCriteria;
import com.example.demo.criteria.Paging;
import com.example.demo.criteria.SearchCriteria;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.lang.Math.toIntExact;

@Controller
public class ItemController {

    @Autowired
    ItemApi itemApi;

    @Autowired
    CommonApi commonApi;

    @Autowired
    MerchandiseApi merchandiseApi;

    @Autowired
    CategoryApi categoryApi;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession httpSession,String buyResult){
        model.addAttribute("item",itemApi.getDetail(id));
        if(!"".equals(buyResult)) {
            model.addAttribute("buyResult", buyResult);
        }
        commonApi.setCommonModel(httpSession,model);
        return "/merchandise/detail";
    }

    /*@GetMapping("/{id}")
    public String category(@PathVariable Long id, Model model, HttpSession httpSession, PageCriteria pageCri){
        commonApi.setCommonModel(httpSession, model);

        SearchCriteria searchCriteria = new SearchCriteria();

        //카테고리검색을 했다면,
        if(id!=2290) {
            searchCriteria.setCategory(categoryApi.getCategory(id).getPart());
        }

        List<Item> items = itemApi.search(searchCriteria);
        model.addAttribute("categoryItem",items);

        model.addAttribute("hiddenCategory", categoryApi.getCategory(id));

        Paging paging = new Paging();
        paging.setCri(pageCri);
        paging.setTotalCount(toIntExact(itemApi.getTotalCount(searchCriteria)));
        model.addAttribute("paging",paging);
        return "/merchandise/merchandises";
    }*/

    @GetMapping("/search")
    public String searchItem(SearchCriteria searchCriteria, HttpSession httpSession, Model model, PageCriteria pageCri){
        commonApi.setCommonModel(httpSession,model);

        model.addAttribute("categoryItem",itemApi.search(searchCriteria));
        model.addAttribute("search", searchCriteria);
        Paging paging = new Paging();
        paging.setCri(pageCri);
        paging.setTotalCount(toIntExact(itemApi.getTotalCount(searchCriteria)));
        model.addAttribute("paging",paging);
        return "/merchandise/merchandises";
    }
}
