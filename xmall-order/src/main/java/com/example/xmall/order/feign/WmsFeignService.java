package com.example.xmall.order.feign;

import com.example.common.utils.R;
import com.example.xmall.order.vo.WareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("xmall-ware")
public interface WmsFeignService {
    @RequestMapping("ware/waresku/hasstock")
    public R getSkusHasStock(@RequestBody List<Long> skuIds);

    @GetMapping(value = "ware/wareinfo/fare")
    public R getFare(@RequestParam("addrId") Long addrId);

    @PostMapping("ware/waresku/lock/order")
    R orderLockStcok(@RequestBody WareSkuLockVo vo);
}
