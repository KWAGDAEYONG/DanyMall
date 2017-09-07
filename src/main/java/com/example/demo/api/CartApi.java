package com.example.demo.api;


import com.example.demo.model.Cart;
import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartApi {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;


    public String addItemIntoCart(User user, Item item){

        Cart cart = cartRepository.findByUser(user);
        if(cart.alreadyContainItemInCart(item)){
            return "카트에 해당 물품이 이미 있습니다";
        }
        cart.addItem(item);
        cartRepository.save(cart);

        return "success";
    }

    public List<Item> getMyCartList(User user){
        return itemRepository.findByCart(cartRepository.findByUser(user));
    }

    public void removeCart(User user, Item item){
        Cart cart = cartRepository.findByUser(user);
        List<Item> items = cart.getItem();
        items.remove(item);
        cart.setItem(items);
        cartRepository.save(cart);
    }
}
