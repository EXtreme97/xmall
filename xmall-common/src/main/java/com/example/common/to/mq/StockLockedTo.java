package com.example.common.to.mq;

import lombok.Data;

import java.util.List;

@Data
public class StockLockedTo {

    /** 库存工作单的id **/
    private Long id;

    /** 工作单详情的所有信息 **/
    private StockDetailTo detail;
}
