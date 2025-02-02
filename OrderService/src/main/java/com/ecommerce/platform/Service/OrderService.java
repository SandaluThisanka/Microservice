package com.ecommerce.platform.Service;

import com.User.UserService.Model.UserEntity;
import com.ecommerce.platform.Model.OrderEntity;
import com.ecommerce.platform.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final WebClient userWebClient;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(WebClient.Builder userWebClient) {
        this.userWebClient=userWebClient.baseUrl("http://localhost:8082/user").build();
    }


    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(int id) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public String SaveOrder (OrderEntity order)
    {

        Integer price=order.getTotal_bill();
        Integer orderID = order.getId();
        Integer userID = order.getUser_id();
        Integer orderQuanitity = order.getQuantity();
        System.out.println(userID);


        UserEntity user = userWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getuser/{id}").build(userID))
                .retrieve()
                .bodyToMono(UserEntity.class)
                .block();

        String email = user.getEmail();
        System.out.println(price);


        orderRepository.save(order);



        sendEmail(email,"Order Placed Confirmation","\"Hey there!\n" +
                "Just wanted to say a huge THANK YOU for your order" +
                "We’re on it – your items will be packed and shipped ASAP. You’ll hear from us again soon with tracking details. Cheers!\"");

        return "Order Successfull ...";
    }

    public String DeleteBooking(int id) {
        if(getOrderById(id) != null)
        {
            orderRepository.deleteById(id);
            return "Order deleted ...";
        }
        else
        {
            return "Order not found !!!";
        }
    }

    private void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
