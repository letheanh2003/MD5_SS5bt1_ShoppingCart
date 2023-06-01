package rikkei.academy.model;

import javax.persistence.*;

@Entity
@Table(name = "oder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameRec;

    private String phone;

    private String address;

    private String nameProduct;

    private String imageProduct;

    private double total;

    private double priceOld;

    private int quantityBuy;

    public Order() {
    }

    public Order(Long id, String nameRec, String phone, String address, String nameProduct,String imageProduct, double total, double priceOld, int quantityBuy) {
        this.id = id;
        this.nameRec = nameRec;
        this.phone = phone;
        this.address = address;
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
        this.total = total;
        this.priceOld = priceOld;
        this.quantityBuy = quantityBuy;
    }

    public Order(String nameRec, String phone, String address, String nameProduct, double total, double priceOld, int quantityBuy) {
        this.nameRec = nameRec;
        this.phone = phone;
        this.address = address;
        this.nameProduct = nameProduct;
        this.total = total;
        this.priceOld = priceOld;
        this.quantityBuy = quantityBuy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRec() {
        return nameRec;
    }

    public void setNameRec(String nameRec) {
        this.nameRec = nameRec;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public double getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(double priceOld) {
        this.priceOld = priceOld;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }
}
