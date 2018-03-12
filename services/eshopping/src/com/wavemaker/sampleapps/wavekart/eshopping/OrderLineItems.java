/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderLineItems generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ORDER_LINE_ITEMS`")
@IdClass(OrderLineItemsId.class)
public class OrderLineItems implements Serializable {

    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal productLineAmount;
    private ProductDetails productDetails;
    private Orders orders;

    @Id
    @Column(name = "`ORDER_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "`PRODUCT_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "`QUANTITY`", nullable = true, scale = 0, precision = 10)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "`PRICE_PER_UNIT`", nullable = true, scale = 9, precision = 64)
    public BigDecimal getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Column(name = "`PRODUCT_LINE_AMOUNT`", nullable = true, scale = 9, precision = 64)
    public BigDecimal getProductLineAmount() {
        return this.productLineAmount;
    }

    public void setProductLineAmount(BigDecimal productLineAmount) {
        this.productLineAmount = productLineAmount;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PRODUCT_ID`", referencedColumnName = "`PRODUCT_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`SYS_FK_10195`"))
    public ProductDetails getProductDetails() {
        return this.productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        if(productDetails != null) {
            this.productId = productDetails.getProductId();
        }

        this.productDetails = productDetails;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ORDER_ID`", referencedColumnName = "`ORDER_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`SYS_FK_10196`"))
    public Orders getOrders() {
        return this.orders;
    }

    public void setOrders(Orders orders) {
        if(orders != null) {
            this.orderId = orders.getOrderId();
        }

        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLineItems)) return false;
        final OrderLineItems orderLineItems = (OrderLineItems) o;
        return Objects.equals(getOrderId(), orderLineItems.getOrderId()) &&
                Objects.equals(getProductId(), orderLineItems.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(),
                getProductId());
    }
}
