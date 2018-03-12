/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wavemaker.sampleapps.wavekart.eshopping.CartItems;
import com.wavemaker.sampleapps.wavekart.eshopping.OrderLineItems;
import com.wavemaker.sampleapps.wavekart.eshopping.ProductDetails;


/**
 * ServiceImpl object for domain model class ProductDetails.
 *
 * @see ProductDetails
 */
@Service("eshopping.ProductDetailsService")
@Validated
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    @Lazy
    @Autowired
	@Qualifier("eshopping.ProductInventoryService")
	private ProductInventoryService productInventoryService;

    @Lazy
    @Autowired
	@Qualifier("eshopping.OrderLineItemsService")
	private OrderLineItemsService orderLineItemsService;

    @Lazy
    @Autowired
	@Qualifier("eshopping.CartItemsService")
	private CartItemsService cartItemsService;

    @Autowired
    @Qualifier("eshopping.ProductDetailsDao")
    private WMGenericDao<ProductDetails, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<ProductDetails, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "eshoppingTransactionManager")
    @Override
	public ProductDetails create(ProductDetails productDetails) {
        LOGGER.debug("Creating a new ProductDetails with information: {}", productDetails);
        return this.wmGenericDao.create(productDetails);
    }

	@Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
	public ProductDetails getById(Integer productdetailsId) throws EntityNotFoundException {
        LOGGER.debug("Finding ProductDetails by id: {}", productdetailsId);
        ProductDetails productDetails = this.wmGenericDao.findById(productdetailsId);
        if (productDetails == null){
            LOGGER.debug("No ProductDetails found with id: {}", productdetailsId);
            throw new EntityNotFoundException(String.valueOf(productdetailsId));
        }
        return productDetails;
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
	public ProductDetails findById(Integer productdetailsId) {
        LOGGER.debug("Finding ProductDetails by id: {}", productdetailsId);
        return this.wmGenericDao.findById(productdetailsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "eshoppingTransactionManager")
	@Override
	public ProductDetails update(ProductDetails productDetails) throws EntityNotFoundException {
        LOGGER.debug("Updating ProductDetails with information: {}", productDetails);

        if(productDetails.getProductInventory() != null) {
            productDetails.getProductInventory().setProductDetails(productDetails);
        }
        if(productDetails.getCartItemses() != null) {
            for(CartItems _cartItems : productDetails.getCartItemses()) {
                _cartItems.setProductDetails(productDetails);
            }
        }
        if(productDetails.getOrderLineItemses() != null) {
            for(OrderLineItems _orderLineItems : productDetails.getOrderLineItemses()) {
                _orderLineItems.setProductDetails(productDetails);
            }
        }

        this.wmGenericDao.update(productDetails);

        Integer productdetailsId = productDetails.getProductId();

        return this.wmGenericDao.findById(productdetailsId);
    }

    @Transactional(value = "eshoppingTransactionManager")
	@Override
	public ProductDetails delete(Integer productdetailsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting ProductDetails with id: {}", productdetailsId);
        ProductDetails deleted = this.wmGenericDao.findById(productdetailsId);
        if (deleted == null) {
            LOGGER.debug("No ProductDetails found with id: {}", productdetailsId);
            throw new EntityNotFoundException(String.valueOf(productdetailsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
	public Page<ProductDetails> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all ProductDetails");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<ProductDetails> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all ProductDetails");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service eshopping for table ProductDetails to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<CartItems> findAssociatedCartItemses(Integer productId, Pageable pageable) {
        LOGGER.debug("Fetching all associated cartItemses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("productDetails.productId = '" + productId + "'");

        return cartItemsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<OrderLineItems> findAssociatedOrderLineItemses(Integer productId, Pageable pageable) {
        LOGGER.debug("Fetching all associated orderLineItemses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("productDetails.productId = '" + productId + "'");

        return orderLineItemsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProductInventoryService instance
	 */
	protected void setProductInventoryService(ProductInventoryService service) {
        this.productInventoryService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service OrderLineItemsService instance
	 */
	protected void setOrderLineItemsService(OrderLineItemsService service) {
        this.orderLineItemsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CartItemsService instance
	 */
	protected void setCartItemsService(CartItemsService service) {
        this.cartItemsService = service;
    }

}

