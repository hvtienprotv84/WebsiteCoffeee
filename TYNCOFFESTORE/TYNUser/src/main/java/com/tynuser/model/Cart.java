package com.tynuser.model;

import com.tynentity.Product;

import java.util.Objects;

public class Cart {

    private int quantity;
    private double prices;
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getFormatPrices(double number) {
        return String.format("%,.0f", number);
    }

    public double getTotal() {
        return this.prices * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(product, cart.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
