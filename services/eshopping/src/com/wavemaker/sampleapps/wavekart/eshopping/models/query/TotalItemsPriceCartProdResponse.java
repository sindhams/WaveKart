/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class TotalItemsPriceCartProdResponse implements Serializable {


    @JsonProperty("PRICE")
    @ColumnAlias("PRICE")
    private Double price;

    @JsonProperty("QTY")
    @ColumnAlias("QTY")
    private BigInteger qty;

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BigInteger getQty() {
        return this.qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TotalItemsPriceCartProdResponse)) return false;
        final TotalItemsPriceCartProdResponse totalItemsPriceCartProdResponse = (TotalItemsPriceCartProdResponse) o;
        return Objects.equals(getPrice(), totalItemsPriceCartProdResponse.getPrice()) &&
                Objects.equals(getQty(), totalItemsPriceCartProdResponse.getQty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(),
                getQty());
    }
}
