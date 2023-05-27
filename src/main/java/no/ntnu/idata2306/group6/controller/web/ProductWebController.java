package no.ntnu.idata2306.group6.controller.web;

import com.google.common.collect.Iterables;
import no.ntnu.idata2306.group6.entity.Category;
import no.ntnu.idata2306.group6.entity.Info;
import no.ntnu.idata2306.group6.entity.Product;
import no.ntnu.idata2306.group6.entity.Review;
import no.ntnu.idata2306.group6.service.InfoService;
import no.ntnu.idata2306.group6.service.ProductService;
import no.ntnu.idata2306.group6.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductWebController {
    @Autowired
    private ProductService productService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String getProducts(@RequestParam(required = false, name = "category") String categoryName, Model model) {

        Iterable<Product> products = null;
        Iterable<Info> infos = null;
        if (categoryName == null || categoryName.equals("all")) {
            products = this.productService.getAll();
            infos = infoService.getAll();
        } else {
            products = this.productService.getAllByCategory(categoryName);
            for (Product p: products) {
                infos = Iterables.concat(infos, infoService.findByProdId(p.getProductId()));
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("infos", infos);
        model.addAttribute("separator", ",");
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getOneProduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("products", this.productService.findById(id));
        model.addAttribute("infos", infoService.findById(id));
        model.addAttribute("reviews", reviewService.findById(id));
        Info info = infoService.findById(id);
        model.addAttribute("info", info);
        return "singleProductPage";
    }
}