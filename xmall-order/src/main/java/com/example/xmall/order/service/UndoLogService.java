package com.example.xmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.utils.PageUtils;
import com.example.xmall.order.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-16 22:54:17
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

