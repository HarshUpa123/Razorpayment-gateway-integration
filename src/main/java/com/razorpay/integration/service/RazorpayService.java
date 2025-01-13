package com.razorpay.integration.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.integration.entities.Order;

@Service
public class RazorpayService {
	private RazorpayClient razorpayClient;

	public RazorpayService() throws RazorpayException {
		// Initialize Razorpay client with your API keys
		this.razorpayClient = new RazorpayClient("rzp_test_0pSfVMeaRYVb3v", "m9g3VRW25Tugog77ZKDXeW4J");
	}

	RazorpayClient client = new RazorpayClient("rzp_test_0pSfVMeaRYVb3v", "m9g3VRW25Tugog77ZKDXeW4J");

	// Create an order in Razorpay
	public com.razorpay.Order createOrder(Order order) throws RazorpayException {
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", order.getTotalAmount()); // Amount in paise
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", "order_rcptid_" + order.getId());

		com.razorpay.Order razorpayOrder = razorpayClient.orders.create(orderRequest);
		
		try {
			
			com.razorpay.Order razorpayOrder1 = razorpayClient.orders.create(orderRequest);
			
			 return razorpayOrder1;

		}catch (Exception e) {
e.printStackTrace();		}
	
	return null;
	}
	// Capture payment (simulation in your case)
	public String capturePayment(String paymentId, int amount) throws RazorpayException {
		try {
			Payment payment = razorpayClient.payments.fetch(paymentId);
			if ("captured".equals(payment.get("status"))) {
				return "Payment already captured";
			}

			JSONObject captureRequest = new JSONObject();
			captureRequest.put("amount", amount);
//            payment.capture(captureRequest); // Capture payment
			client.payments.capture(paymentId, captureRequest);

			return "Payment captured successfully!";
		} catch (RazorpayException e) {
			throw new RazorpayException("Error capturing payment: " + e.getMessage(), e);
		}
	}
	
	public String getPaymentLink() {
        try {

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", 900); // in paise
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("description", "Order payment");
            PaymentLink paymentLink = client.paymentLink.create(paymentLinkRequest);


            return paymentLink.get("short_url").toString();
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }
}