package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.ProductsWithCountVo;
import com.rhinoceros.mall.manager.manager.ProductManager;
import com.rhinoceros.mall.service.service.ProductService;
import com.rhinoceros.mall.web.support.web.annotation.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 创建商品控制器
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    //定义要调用的逻辑业务对象
    @Autowired
    private ProductService productService;

    /**
     * 定义方法找到商品并转换为json格式返回
     * ?page=1&size=10&sort=saleNum,DESC/ASC&sort=price,DESC
     *
     * @param pageQuery
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public ProductsWithCountVo productList(@PageDefault(required = false) PageQuery pageQuery, @RequestParam(value = "categoryId") Long categoryId) {
        List<Product> productList = productService.findDeepByCategoryId(categoryId, pageQuery);
        Long count= productService.countDeepByCategoryId(categoryId);
        ProductsWithCountVo productsWithCountVo = new ProductsWithCountVo();
        productsWithCountVo.setProducts(productList);
        productsWithCountVo.setCount(count);
        return productsWithCountVo;
    }

    /**
     * 显示商品
     *
     * @return
     */
    @RequestMapping
    public String showProduct() {
        return "include/product";
    }

//    @RequestMapping("/edict")
//    public Product updateSelectionProduct() {
//        return ProductManager.updateSelectionById();
//    }

    /**
     * 删除商品
     *
     * @param product
     */
    @ResponseBody
    @RequestMapping("/delete")
    public void delete(Product product) {
       productService.deleteById(product.getId());
    }

    /**
     * 修改商品
     *
     * @param product
     */
    @ResponseBody
    @RequestMapping("/update")
    public void update(Product product) {
        productService.updateSelectionById(product);
    }

    /**
     * 增加商品
     *
     * @param product
     */
    @ResponseBody
    @RequestMapping("/add")
    public void add(Product product) {
        productService.addSelectionById(product);
    }
}