package com.example.xmallcart.controller;

import com.example.xmallcart.service.CartService;
import com.example.xmallcart.vo.Cart;
import com.example.xmallcart.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @ResponseBody
    @GetMapping("/currentUserCartItems")
    public List<CartItem> getCurrentUserCartItems(){
        return cartService.getUserCartItems();
    }

    @GetMapping("/cartList.html")
    public String CartList(Model model) throws ExecutionException, InterruptedException {
//        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
//
//        UserInforTo userInforTo = CartInterceptor.threadLocal.get();
        Cart cart = cartService.getCart();
        model.addAttribute("cartList", cart);

        return "cartList";
    }

    @GetMapping("/success.html")
    public String CartSuccess() {
        return "success";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {
        cartService.addToCart(skuId, num);
        redirectAttributes.addAttribute("skuId", skuId);
        return "redirect:http://192.168.56.1:22000/addToCartSuccess.html";
    }

    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam("skuId") Long skuId,
                                       Model model) {
        CartItem item = cartService.getCartItem(skuId);
        model.addAttribute("skuInfo", item);
        return "success";
    }

    @GetMapping("/checkItem")
    public String checkItem(@RequestParam("skuId") Long skuId, @RequestParam("checked") Integer checked) {
        cartService.checkItem(skuId, checked);
        return "redirect:http://192.168.56.1:22000/cartList.html";
    }

    @GetMapping("/countItem")
    public String countItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.changeItemCount(skuId, num);
        return "redirect:http://192.168.56.1:22000/cartList.html";
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return "redirect:http://192.168.56.1:22000/cartList.html";
    }
}
