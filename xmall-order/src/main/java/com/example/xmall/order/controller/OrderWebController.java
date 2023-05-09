package com.example.xmall.order.controller;

import com.example.common.exception.NoStockException;
import com.example.common.utils.R;
import com.example.xmall.order.feign.WmsFeignService;
import com.example.xmall.order.service.OrderService;
import com.example.xmall.order.vo.OrderConfirmVo;
import com.example.xmall.order.vo.OrderSubmitVo;
import com.example.xmall.order.vo.SubmitResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

@Controller
public class OrderWebController {
    @Autowired
    OrderService orderService;
    @Autowired
    WmsFeignService wmsFeignService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirmData", confirmVo);
        System.out.println(confirmVo.toString());
        return "confirm";
    }

    @ResponseBody
    @GetMapping("/wareinfor")
    public R getWareInfor(@RequestParam("addrId") Long addrId) {
        return wmsFeignService.getFare(addrId);
    }

    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo vo, Model model, RedirectAttributes attributes) {
        try {


            SubmitResponseVo res = orderService.submitOrder(vo);
            System.out.println("提交订单数据" + vo.toString() + " " + res.getCode());

            if (res.getCode() == 0) {
                model.addAttribute("submitOrderResp", res);
                return "pay";
            } else {
                String msg = "下单失败";
                switch (res.getCode()) {
                    case 1:
                        msg += "令牌订单信息过期，请刷新再次提交";
                        break;
                    case 2:
                        msg += "订单商品价格发生变化，请确认后再次提交";
                        break;
                    case 3:
                        msg += "库存锁定失败，商品库存不足";
                        break;
                }
                attributes.addFlashAttribute("msg", msg);
                return "redirect:http://192.168.56.1:9000/toTrade";
            }
        } catch (Exception e) {
            if (e instanceof NoStockException) {
                String message = ((NoStockException) e).getMessage();
                attributes.addFlashAttribute("msg", message);
            }
            return "redirect:http://192.168.56.1:9000/toTrade";
        }
    }
}
