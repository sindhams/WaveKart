/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Orders generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ORDERS`")
public class Orders implements Serializable {

    private Integer orderId;
    private int userId;
    private int userAddressId;
    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private Timestamp orderDate;
    private String status;
    private List<OrderLineItems> orderLineItemses;
    private UserDetails userDetails;
    private UserAddressDetails userAddressDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ORDER_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Column(name = "`USER_ID`", nullable = false, scale = 0, precision = 10)
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "`USER_ADDRESS_ID`", nullable = false, scale = 0, precision = 10)
    public int getUserAddressId() {
        return this.userAddressId;
    }

    public void setUserAddressId(int userAddressId) {
        this.userAddressId = userAddressId;
    }

    @Column(name = "`TOTAL_QUANTITY`", nullable = true, scale = 0, precision = 10)
    public Integer getTotalQuantity() {
        return this.totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Column(name = "`TOTAL_PRICE`", nullable = true, scale = 9, precision = 64)
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "`ORDER_DATE`", nullable = false)
    public Timestamp getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "`STATUS`", nullable = false, length = 255)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "orders")
    public List<OrderLineItems> getOrderLineItemses() {
        return this.orderLineItemses;
    }

    public void setOrderLineItemses(List<OrderLineItems> orderLineItemses) {
        this.orderLineItemses = orderLineItemses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`USER_ID`", referencedColumnName = "`USER_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`SYS_FK_10180`"))
    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        if(userDetails != null) {
            this.userId = userDetails.getUserId();
        }

        this.userDetails = userDetails;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`USER_ADDRESS_ID`", referencedColumnName = "`USER_ADDRESSS_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`SYS_FK_10181`"))
    public UserAddressDetails getUserAddressDetails() {
        return this.userAddressDetails;
    }

    public void setUserAddressDetails(UserAddressDetails userAddressDetails) {
        if(userAddressDetails != null) {
            this.userAddressId = userAddressDetails.getUserAddresssId();
        }

        this.userAddressDetails = userAddressDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        final Orders orders = (Orders) o;
        return Objects.equals(getOrderId(), orders.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }
}

