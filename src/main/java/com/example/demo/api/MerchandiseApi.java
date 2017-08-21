package com.example.demo.api;

import com.example.demo.model.Item;
import com.example.demo.model.Merchandise;
import com.example.demo.model.User;
import com.example.demo.repository.ItempRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MerchandiseApi {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    @Autowired
    ItempRepository itempRepository;

    public String buy(Long id, String color, String size, HttpSession httpSession){
        if(!SessionUtil.isLogin(httpSession)){
            return "로그인 정보가 끊겼습니다. 다시 로그인해주세요.";
        }
        Merchandise target = merchandiseRepository.findByItemAndColorAndSize(itempRepository.findOne(id),color,size);
        if(!target.choolgo()){
            return "재고가 없습니다.";
        }
        User loginUser = (User)httpSession.getAttribute("loginUser");
        target.addBuyer(loginUser);
        merchandiseRepository.save(target);

        return "정상적으로 구매신청이 완료되었습니다";
    }

}
