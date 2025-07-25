package vn.hoidanit.laptopshop.domain;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // min chỉ dùng cho số còn muốn chữ thì dùng size
    @NotNull(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;

    @NotNull
    @Min(value = 1, message = "Price must be at least 2 characters")
    private double price;

    @NotNull
    @NotEmpty(message = "detailDesc không được để trống")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;

    private String shortDesc;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 2 characters")
    private long quantity;

    private long sold;

    private String factory;

    private String target;

    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getImage() {
        return image;
    }

    public void setProductImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", detailDesc=" + detailDesc
                + ", shortDesc=" + shortDesc + ", quantity=" + quantity + ", sold=" + sold + ", productImage="
                + image + "]";
    }

}
