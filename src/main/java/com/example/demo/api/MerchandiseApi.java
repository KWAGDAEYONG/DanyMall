package com.example.demo.api;

import com.example.demo.model.Merchandise;
import com.example.demo.model.Sold;
import com.example.demo.model.User;
import com.example.demo.repository.ItempRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.SoldRepository;
import com.example.demo.staticUtility.DateUtil;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MerchandiseApi {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    @Autowired
    ItempRepository itempRepository;

    @Autowired
    SoldRepository soldRepository;

    public String buy(Long id, String color, String size, HttpSession httpSession, Sold sold) {
        if (!SessionUtil.isLogin(httpSession)) {
            return "로그인 정보가 끊겼습니다. 다시 로그인해주세요.";
        }
        Merchandise target = merchandiseRepository.findByItemAndColorAndSize(itempRepository.findOne(id), color, size);

        if (target==null||!target.release()) {
            return "재고가 없습니다.";
        }
        User loginUser = (User) httpSession.getAttribute("loginUser");
        sold.setBuyer(loginUser);
        sold.setSoldDate(DateUtil.getTodayDate());
        sold.setSoldMerchandise(target);
        target.updateSalesVolume();
        soldRepository.save(sold);

        return "정상적으로 구매신청이 완료되었습니다";
    }

    public Merchandise addMerchandise(Merchandise merchandise){
        return merchandiseRepository.save(merchandise);
    }

    public List<Merchandise> getMerchandiseList(){
        return merchandiseRepository.findAll();
    }

    public Merchandise getMerchandise(Long id){
        return merchandiseRepository.findOne(id);
    }


}
