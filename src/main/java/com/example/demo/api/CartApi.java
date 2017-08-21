package com.example.demo.api;


import com.example.demo.model.Cart;
import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItempRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class CartApi {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItempRepository itempRepository;

    @Autowired
    CommonApi commonApi;

    public String addItemIntoCart(User user, Item item){

        Cart cart = user.getCart();
        if(cart.alreadyContainItemInCart(item)){
            return "카트에 해당 물품이 이미 있습니다";
        }
        cart.addItem(item);
        cartRepository.save(cart);

        return "카트에 물품이 추가되었습니다.";
    }

    public List<Item> getMyCartList(User user){
        return itempRepository.findByCart(cartRepository.findByUser(user));
    }
}
