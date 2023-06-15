package com.example.WEBBANMOHINH.controller;

import jakarta.servlet.http.HttpSession;
import com.example.WEBBANMOHINH.entity.Product;
import com.example.WEBBANMOHINH.models.CartItem;
import com.example.WEBBANMOHINH.repository.UserRepository;
import com.example.WEBBANMOHINH.services.CartService;
import com.example.WEBBANMOHINH.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepo;
    @GetMapping("")
    public String getCartItems(Model model, HttpSession session) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        // Calculate the total price
        long totalPrice = cartItems.stream()
                .mapToLong(cartItem -> cartItem.getPrice() * cartItem.getQuantity())
                .sum();
        model.addAttribute("totalPrice", totalPrice);
        // Calculate cart count
        session.setAttribute("cartCount", cartItems.size());
      //  model.addAttribute("cartCount", cartItems.size());
        return "cart/index";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer productId) {
        Product product = productService.get(productId);
        if (product != null) {
            cartService.addToCart(product);
        }
        return "redirect:/cart";
    }
    @PostMapping("/update")
    public String updateCartItem(@RequestParam("productId") Integer productId,
                                 @RequestParam("quantity") int quantity) {
        cartService.updateCartItem(productId, quantity);
        return "redirect:/cart";
    }
    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable("productId") Integer productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @PostMapping("/order")
    public String Order() {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        com.example.WEBBANMOHINH.entity.User findUser = userRepo.getUserByUsername(user.getUsername());
        // Process the purchaseProduct product = productService.get(productId);
        cartService.orderCart(findUser);
        // Redirect to a success page or return a success message
        return "/cart/order.html";
    }
    @PostMapping("/payment")
    public String Payment() {
        // Get the currently logged-in user

        // Redirect to a success page or return a success message
        return "/cart/success";
    }
}