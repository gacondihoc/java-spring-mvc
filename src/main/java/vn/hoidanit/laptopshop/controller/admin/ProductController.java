package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductController {

    private final ProductService productService;

    private final UploadService uploadService;

    ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @RequestMapping("/admin/product")
    public String getProDuctPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products1", products);
        return "admin/product/show";
    }

    @RequestMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @RequestMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product currentPro = this.productService.getProductById(id);
        model.addAttribute("newProduct", currentPro);
        return "/admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model, @ModelAttribute("newProduct") Product product,
            @RequestParam("productImageFile") MultipartFile file) {
        // TODO: process POST request
        String productImage = this.uploadService.handleSaveUploadFile(file, "product");
        Product currentPro = this.productService.getProductById(product.getId());
        if (currentPro != null) {
            currentPro.setName(product.getName());
            currentPro.setDetailDesc(product.getDetailDesc());
            currentPro.setPrice(product.getPrice());
            currentPro.setShortDesc(product.getShortDesc());
            currentPro.setQuantity(product.getQuantity());
            currentPro.setProductImage(productImage);
            currentPro.setFactory(product.getFactory());
            currentPro.setTarget(product.getTarget());
            this.productService.handleSaveProduct(currentPro);

        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newProduct", new Product());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("newProduct") Product product) {
        // TODO: process POST request
        this.productService.deleteById(product.getId());

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productImageFile") MultipartFile file) {

        // List<FieldError> errors = newProductBindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println(">>>>> " + error.getField() + " - " +
        // error.getDefaultMessage());
        // }

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }

        String productImage = this.uploadService.handleSaveUploadFile(file, "product");

        product.setProductImage(productImage);
        this.productService.handleSaveProduct(product);

        return "redirect:/admin/product";
    }

}
