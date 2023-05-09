package com.example.xmall.order.vo;

import com.example.xmall.order.entity.OrderEntity;
import lombok.Data;

@Data
public class SubmitResponseVo {
    private OrderEntity order;
    private  Integer code;
}
