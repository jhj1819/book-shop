package summer.book_shop.domain;

import java.sql.Date;

public class Order{
    String orderCode;
    private String orderId;
    private String order_name;
    private String bookCode;
    private String phone_number;
    private int totalPrice;
    private Date orderDate;
    private int productCount;
    private int totalProductCount;
    private boolean payment;
    private boolean status;
    private String shipping_address;
    public Order() {};

    public Order(String order_name, String shipping_address, String bookCode, int productCount) {
        this.order_name = order_name;
        this.shipping_address = shipping_address;
        this.bookCode = bookCode;
        this.productCount = productCount;
    }

    public Order(String order_name,String orderCode, String shipping_address, String bookCode, int productCount) {
        this.order_name = order_name;
        this.orderCode = orderCode;
        this.shipping_address = shipping_address;
        this.bookCode = bookCode;
        this.productCount = productCount;
    }
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(int totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public String getPayment() {
        return String.valueOf(payment);
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }
}