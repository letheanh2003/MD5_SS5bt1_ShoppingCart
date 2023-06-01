package rikkei.academy.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Cart;
import rikkei.academy.model.Order;
import rikkei.academy.model.Product;
import rikkei.academy.service.order.IOrderService;
import rikkei.academy.service.product.IProductService;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderService orderService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping({"/shop", "/"})
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/createProduct");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @Value("${file-upload}")
    private String fileUpload;

    @PostMapping("/create")
    public String saveCustomer(@RequestParam("image") MultipartFile image,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("price") double price,
                               Model model) throws IOException {
        File file = new File(fileUpload);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        FileCopyUtils.copy(image.getBytes(), new File(fileUpload + File.separator + fileName));
        productService.save(new Product(name, fileName, price, description));
        return "redirect:/";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        if (action.equals("increase")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        } else if (action.equals("decrease")) {
            cart.removeProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }

        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

    @GetMapping("delete/{id}")
    public String deleteCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        } else if (action.equals("delete")) {
            cart.deleteItemCart(productOptional.get());
            return "redirect:/shopping-cart";
        }
        return "redirect:/shop";
    }

    @GetMapping("order")
    public ModelAndView order() {
        ModelAndView modelAndView = new ModelAndView("/createOrder");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("addOrder")
    public String addOrder(@SessionAttribute("cart") Cart cart,
                           @RequestParam("nameRec") String nameRec,
                           @RequestParam("phone") String phone,
                           @RequestParam("address") String address,
                           Model model
    ) {
        Set<Product> productSet = cart.getProducts().keySet();
        for (Product p : productSet
        ) {
            Order o = new Order();
            o.setNameRec(nameRec);
            o.setPhone(phone);
            o.setAddress(address);
            o.setTotal(cart.countTotalPayment());
            o.setNameProduct(p.getName());
            o.setPriceOld(p.getPrice());
            o.setImageProduct(p.getImage());
            o.setQuantityBuy(cart.getProducts().get(p));
            orderService.save(o);
        }
        model.addAttribute("cart",new Cart());
        return "/success";
    }

}
