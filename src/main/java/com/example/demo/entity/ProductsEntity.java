package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "products", schema = "users20")
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false, insertable = true, updatable = true)
    private long productId;
    @Basic
    @Column(name = "section_id")
    private Integer section_id;
    @Basic
    @Column(name = "Product")
    private String product;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Integer getSectionId() {
        return section_id;
    }

    public void setSectionId(Integer sectionId) {
        this.section_id = sectionId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.example.demo.entity.ProductsEntity that = (com.example.demo.entity.ProductsEntity) o;
        return productId == that.productId && Objects.equals(section_id, that.section_id) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, section_id, product);
    }
}
