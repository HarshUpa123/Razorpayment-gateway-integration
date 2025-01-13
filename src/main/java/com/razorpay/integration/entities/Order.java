package com.razorpay.integration.entities;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	private Product product;
    private int quantity;
    private int totalAmount; // in paise
    private LocalDateTime createdAt;

    public Order(Long id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = product.getPrice() * quantity;
        this.createdAt = LocalDateTime.now();
    }

	@Override
	public String toString() {
		return "Order [id=" + id + ", product=" + product + ", quantity=" + quantity + ", totalAmount=" + totalAmount
				+ ", createdAt=" + createdAt + ", getId()=" + getId() + ", getProduct()=" + getProduct()
				+ ", getQuantity()=" + getQuantity() + ", getTotalAmount()=" + getTotalAmount() + ", getCreatedAt()="
				+ getCreatedAt() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

    // Getters and Setters
}
