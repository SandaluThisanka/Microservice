package com.ecommerce.platform.Controller;

import com.ecommerce.platform.Model.OrderEntity;
import com.ecommerce.platform.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getorders")
    public List<OrderEntity> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getorder/{id}")
    public OrderEntity getOrder(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/neworder")
    public ResponseEntity<String> addOrder(@RequestBody OrderEntity order){
        String message= orderService.SaveOrder(order);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/deleteorder/{id}")
    public String deleteOrder(@PathVariable int id){
        return orderService.DeleteBooking(id);
    }

}
