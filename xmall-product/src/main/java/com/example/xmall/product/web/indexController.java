package com.example.xmall.product.web;

import com.example.xmall.product.entity.CategoryEntity;
import com.example.xmall.product.service.CategoryService;
import com.example.xmall.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class indexController {
    @Autowired
    CategoryService categoryService;
    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {
       List<CategoryEntity> categoryEntities= categoryService.getLevel1Categories();
       model.addAttribute("categories",categoryEntities);
        return "index";
    }

    @ResponseBody
    @GetMapping("/index/catelog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson(){
        Map<String, List<Catelog2Vo>> catelogJson=categoryService.getCatelogJson();
        return catelogJson;
    }
}
