package team.groupproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.entity.Cart;
import team.groupproject.entity.CartDetails;
import team.groupproject.entity.CartDetailsPK;
import team.groupproject.entity.Myuser;
import team.groupproject.entity.Product;
import team.groupproject.errorHandling.CartNotFoundException;
import team.groupproject.errorHandling.ProductNotFoundException;
import team.groupproject.model.MessageResponse;
import team.groupproject.repository.CartDetailsRepo;
import team.groupproject.repository.ProductRepo;
import team.groupproject.security.UserService;
import team.groupproject.service.CartDetailsService;
import team.groupproject.service.CartService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailsService cartDetailsService;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartDetailsRepo cartDetailsRepo;

    @PostMapping("/addProductToCart/{id}")
    public ResponseEntity<?> addProductToCart(@PathVariable(name = "id") int id, Authentication authentication) {

        CartDetails cartDetails = null;
        Product product = productRepo.findById(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product with id " + id + " does not exist.");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());

        Cart cart = user.getCart();

        if (cart == null) {
            cart = cartService.createCart(user);
            cart.setTotalAmount(product.getPrice());
            cartDetailsService.createCartDetails(cart, product);
            return ResponseEntity
                    .ok(new MessageResponse("Cart was created successfully!. Product " + id + " was added."));
        } else {

            if (cartDetailsService.existsByCartDetailsPK(new CartDetailsPK(cart, product))) {
                cartDetails = cartDetailsService.findByCartDetailsPK(new CartDetailsPK(cart, product));
                cartDetails.setQuantity(cartDetails.getQuantity() + 1);
                cartDetailsService.save(cartDetails);
                cart.setTotalAmount(cart.getTotalAmount().add(product.getPrice()));
                cartService.saveCart(cart);
                return ResponseEntity.ok(new MessageResponse(
                        "Quantity of Product " + id + " increased to " + cartDetails.getQuantity()));
            } else {
                cartDetailsService.createCartDetails(cart, product);
                cart.setTotalAmount(cart.getTotalAmount().add(product.getPrice()));
                cartService.saveCart(cart);
                return ResponseEntity.ok(new MessageResponse("Product added to Cart."));
            }
        }
    }

    @DeleteMapping("/removeProductFromCart/{id}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable(name = "id") int id, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());
        Product product = productRepo.findById(id);
        if (product == null) {
            throw new ProductNotFoundException(String.valueOf(id));
        }
        Cart cart = user.getCart();
        if (cart != null) {
            CartDetails cartDetails = cartDetailsService.findByCartDetailsPK(new CartDetailsPK(cart, product));
            if (cartDetails == null) {
                return ResponseEntity.ok(new MessageResponse("Product " + id + " is not in cart"));
            }
            if (cartDetails.getQuantity() > 1) {
                cartDetails.setQuantity(cartDetails.getQuantity() - 1);
                cartDetailsService.save(cartDetails);
                cart.setTotalAmount(cart.getTotalAmount().subtract(product.getPrice()));
                cartService.saveCart(cart);
                return ResponseEntity.ok(new MessageResponse(
                        "Product " + id + " quantity was decreased to " + cartDetails.getQuantity()));
            } else {
                cartDetailsService.deleteProductFromCart(cartDetails);
                cart.setTotalAmount(cart.getTotalAmount().subtract(product.getPrice()));
                cartService.saveCart(cart);
                return ResponseEntity.ok(new MessageResponse("Product with id " + id + " was sucessfully removed"));
            }
        } else {
            throw new CartNotFoundException("");
        }
    }

    @GetMapping("/getCart")
    public ResponseEntity<List<Object>> getCart(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());
        List<Object> cartInfo = cartDetailsRepo.getCartInfo(user.getId());
        return new ResponseEntity<>(cartInfo, HttpStatus.OK);
    }

    @GetMapping("/getCartTotalQuantity")
    public ResponseEntity<?> getCartTotalQuantity(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());
        Cart cart = user.getCart();

        Integer totalQuan = cartDetailsService.getTotalCartQuantity(user.getCart().getId());
        return new ResponseEntity<>(totalQuan, HttpStatus.OK);

    }

}
