package com.example.demo.api;

import com.example.demo.model.Item;
import com.example.demo.model.Merchandise;
import com.example.demo.model.Sold;
import com.example.demo.model.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.SoldRepository;
import com.example.demo.staticUtility.DateUtil;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MerchandiseApi {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    SoldRepository soldRepository;

    public Sold  buy(Merchandise target, HttpSession httpSession, int qty) {
        User loginUser = (User) httpSession.getAttribute("loginUser");
        Sold sold = new Sold(loginUser,target,DateUtil.getTodayDate(),qty);
        target.release(qty);
        target.updateSalesVolume(qty);

        return soldRepository.save(sold);
    }

    public Merchandise addMerchandise(Merchandise merchandise) {
        return merchandiseRepository.save(merchandise);
    }

    public Merchandise getBuyTarget(Long id, String color, String size){
        return merchandiseRepository.findByItemAndColorAndSize(itemRepository.findOne(id), color, size);
    }

    public List<Merchandise> getMerchandiseList() {
        return merchandiseRepository.findAll();
    }

    public Merchandise getMerchandise(Long id) {
        return merchandiseRepository.findOne(id);
    }


}
