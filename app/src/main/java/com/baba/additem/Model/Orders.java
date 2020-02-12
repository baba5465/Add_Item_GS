package com.baba.additem.Model;

public class Orders {
    String itemName, itemPrice, orderDate, orderTime, paymentStatus, quantity, totalAmount, userAdd, userEmail, userId, userName, userNumber;

    public Orders(String itemName, String itemPrice, String orderDate, String orderTime, String paymentStatus, String quantity, String totalAmount, String userAdd, String userEmail, String userId, String userName, String userNumber) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.paymentStatus = paymentStatus;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.userAdd = userAdd;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userName = userName;
        this.userNumber = userNumber;
    }

    public Orders() {
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getUserAdd() {
        return userAdd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber;
    }
}
