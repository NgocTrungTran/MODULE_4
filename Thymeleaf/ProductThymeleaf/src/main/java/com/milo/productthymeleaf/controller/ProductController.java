package com.milo.productthymeleaf.controller;

import com.milo.productthymeleaf.model.Product;
import com.milo.productthymeleaf.service.IProductService;
import com.milo.productthymeleaf.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService = new ProductService ();
    // Hien thi danh sach san pham
    @GetMapping("")
    public String index(Model model) {

        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "/index";
    }

    // Form them san pham
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product ());
        return "/create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("customer") Product product, RedirectAttributes redirect) {
        product.setId(System.currentTimeMillis () / 1000);
        productService.save(product);
        redirect.addFlashAttribute("success", "Create customer successfully!");
        return "redirect:/product";
    }
    // Sua thong tin san pham
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/edit";
    }
    @PostMapping("/update")
    public String update(Product product) {
        productService.update(product.getId(), product);
        return "redirect:/product";
    }
    // Xoa san pham
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Removed product successfully!");
        return "redirect:/product";
    }
    // xem thong tin chi tiet
    @GetMapping("/{id}/view")
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }
}
