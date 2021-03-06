/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/

package com.wavemaker.sampleapps.wavekart.eshopping.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wavemaker.sampleapps.wavekart.eshopping.models.query.*;

@Service
public class EshoppingQueryExecutorServiceImpl implements EshoppingQueryExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EshoppingQueryExecutorServiceImpl.class);

    @Autowired
    @Qualifier("eshoppingWMQueryExecutor")
    private WMQueryExecutor queryExecutor;

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<TotalItemsPriceCartProdResponse> executeTotalItemsPrice_CartProd(Integer cartId, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("cartId", cartId);

        return queryExecutor.executeNamedQuery("TotalItemsPrice_CartProd", params, TotalItemsPriceCartProdResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable exportTotalItemsPrice_CartProd(ExportType exportType, Integer cartId, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("cartId", cartId);

        return queryExecutor.exportNamedQueryData("TotalItemsPrice_CartProd", params, exportType, TotalItemsPriceCartProdResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<TotalItemsPriceOrdersResponse> executeTotalItemsPrice_Orders(Integer oid, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("Oid", oid);

        return queryExecutor.executeNamedQuery("TotalItemsPrice_Orders", params, TotalItemsPriceOrdersResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable exportTotalItemsPrice_Orders(ExportType exportType, Integer oid, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("Oid", oid);

        return queryExecutor.exportNamedQueryData("TotalItemsPrice_Orders", params, exportType, TotalItemsPriceOrdersResponse.class, pageable);
    }

    @Transactional(value = "eshoppingTransactionManager")
    @Override
    public Integer executeDeleteCartItems_LoggedUser(Integer loggedUserCartId) {
        Map params = new HashMap(1);

        params.put("LoggedUserCartId", loggedUserCartId);

        return queryExecutor.executeNamedQueryForUpdate("DeleteCartItems_LoggedUser", params);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<GetCartIdByUseridResponse> executeGet_CartIdByUserid(Pageable pageable) {
        Map params = new HashMap(0);


        return queryExecutor.executeNamedQuery("Get_CartIdByUserid", params, GetCartIdByUseridResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable exportGet_CartIdByUserid(ExportType exportType, Pageable pageable) {
        Map params = new HashMap(0);


        return queryExecutor.exportNamedQueryData("Get_CartIdByUserid", params, exportType, GetCartIdByUseridResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<MyCartItemsCountResponse> executeMyCart_Items_Count(Pageable pageable) {
        Map params = new HashMap(0);


        return queryExecutor.executeNamedQuery("MyCart_Items_Count", params, MyCartItemsCountResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable exportMyCart_Items_Count(ExportType exportType, Pageable pageable) {
        Map params = new HashMap(0);


        return queryExecutor.exportNamedQueryData("MyCart_Items_Count", params, exportType, MyCartItemsCountResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<QvGetInventoryDetailsResponse> executeQV_getInventoryDetails(List<Integer> productIds, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("productIds", productIds);

        return queryExecutor.executeNamedQuery("QV_getInventoryDetails", params, QvGetInventoryDetailsResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable exportQV_getInventoryDetails(ExportType exportType, List<Integer> productIds, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("productIds", productIds);

        return queryExecutor.exportNamedQueryData("QV_getInventoryDetails", params, exportType, QvGetInventoryDetailsResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<GetCartItemsIdResponse> executeGet_CartItems_id(Integer cid, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("Cid", cid);

        return queryExecutor.executeNamedQuery("Get_CartItems_id", params, GetCartItemsIdResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable exportGet_CartItems_id(ExportType exportType, Integer cid, Pageable pageable) {
        Map params = new HashMap(1);

        params.put("Cid", cid);

        return queryExecutor.exportNamedQueryData("Get_CartItems_id", params, exportType, GetCartItemsIdResponse.class, pageable);
    }

    @Transactional(value = "eshoppingTransactionManager")
    @Override
    public Integer executeQV_UpdateQuantityFromPI(QvUpdateQuantityFromPiRequest qvUpdateQuantityFromPiRequest) {
        Map params = new HashMap(2);

        params.put("qty", qvUpdateQuantityFromPiRequest.getQty());
        params.put("productId", qvUpdateQuantityFromPiRequest.getProductId());

        return queryExecutor.executeNamedQueryForUpdate("QV_UpdateQuantityFromPI", params);
    }

}


