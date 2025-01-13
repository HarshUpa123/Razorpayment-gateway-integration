package com.razorpay.integration.controller;

import org.springframework.web.bind.annotation.*;

import com.razorpay.RazorpayException;
import com.razorpay.integration.entities.Order;
import com.razorpay.integration.entities.Product;
import com.razorpay.integration.service.RazorpayService;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

@RestController
public class PaymentController {

    private final RazorpayService razorpayService;

    public PaymentController() throws RazorpayException {
        this.razorpayService = new RazorpayService();
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam String productId, @RequestParam int quantity) {
        try {
            // Convert the productId to Long
            Long productLongId = Long.parseLong(productId);
            String paymentLink = razorpayService.getPaymentLink();
// Converts the productId to Long

            // Create demo product
            Product product = new Product(productLongId, "Demo Product", 50000);  // Price in paise (INR 500)
            Order order = new Order(1L, product, quantity); // Demo order creation
            
            // Create order in Razorpay
            com.razorpay.Order razorpayOrder = razorpayService.createOrder(order);
      
            return razorpayOrder.toString();

        }catch (Exception e) {
e.printStackTrace();		}
		return null;
    }

    @PostMapping("/capture-payment")
    public ResponseEntity<?> capturePayment(@RequestParam String paymentId, @RequestParam int amount) {
        try {
            String response = razorpayService.capturePayment(paymentId, amount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error capturing payment: " + e.getMessage());
        }
    }
}
