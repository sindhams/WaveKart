/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
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

import com.wavemaker.sampleapps.wavekart.eshopping.CartDetails;
import com.wavemaker.sampleapps.wavekart.eshopping.CartItems;


/**
 * ServiceImpl object for domain model class CartDetails.
 *
 * @see CartDetails
 */
@Service("eshopping.CartDetailsService")
@Validated
public class CartDetailsServiceImpl implements CartDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartDetailsServiceImpl.class);

    @Lazy
    @Autowired
	@Qualifier("eshopping.CartItemsService")
	private CartItemsService cartItemsService;

    @Autowired
    @Qualifier("eshopping.CartDetailsDao")
    private WMGenericDao<CartDetails, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<CartDetails, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "eshoppingTransactionManager")
    @Override
	public CartDetails create(CartDetails cartDetails) {
        LOGGER.debug("Creating a new CartDetails with information: {}", cartDetails);
        return this.wmGenericDao.create(cartDetails);
    }

	@Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
	public CartDetails getById(Integer cartdetailsId) throws EntityNotFoundException {
        LOGGER.debug("Finding CartDetails by id: {}", cartdetailsId);
        CartDetails cartDetails = this.wmGenericDao.findById(cartdetailsId);
        if (cartDetails == null){
            LOGGER.debug("No CartDetails found with id: {}", cartdetailsId);
            throw new EntityNotFoundException(String.valueOf(cartdetailsId));
        }
        return cartDetails;
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
	public CartDetails findById(Integer cartdetailsId) {
        LOGGER.debug("Finding CartDetails by id: {}", cartdetailsId);
        return this.wmGenericDao.findById(cartdetailsId);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public CartDetails getByUserId(int userId) {
        Map<String, Object> userIdMap = new HashMap<>();
        userIdMap.put("userId", userId);

        LOGGER.debug("Finding CartDetails by unique keys: {}", userIdMap);
        CartDetails cartDetails = this.wmGenericDao.findByUniqueKey(userIdMap);

        if (cartDetails == null){
            LOGGER.debug("No CartDetails found with given unique key values: {}", userIdMap);
            throw new EntityNotFoundException(String.valueOf(userIdMap));
        }

        return cartDetails;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "eshoppingTransactionManager")
	@Override
	public CartDetails update(CartDetails cartDetails) throws EntityNotFoundException {
        LOGGER.debug("Updating CartDetails with information: {}", cartDetails);

        if(cartDetails.getCartItemses() != null) {
            for(CartItems _cartItems : cartDetails.getCartItemses()) {
                _cartItems.setCartDetails(cartDetails);
            }
        }

        this.wmGenericDao.update(cartDetails);

        Integer cartdetailsId = cartDetails.getCartId();

        return this.wmGenericDao.findById(cartdetailsId);
    }

    @Transactional(value = "eshoppingTransactionManager")
	@Override
	public CartDetails delete(Integer cartdetailsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting CartDetails with id: {}", cartdetailsId);
        CartDetails deleted = this.wmGenericDao.findById(cartdetailsId);
        if (deleted == null) {
            LOGGER.debug("No CartDetails found with id: {}", cartdetailsId);
            throw new EntityNotFoundException(String.valueOf(cartdetailsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "eshoppingTransactionManager")
	@Override
	public Page<CartDetails> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all CartDetails");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<CartDetails> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all CartDetails");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service eshopping for table CartDetails to {} format", exportType);
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
    public Page<CartItems> findAssociatedCartItemses(Integer cartId, Pageable pageable) {
        LOGGER.debug("Fetching all associated cartItemses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("cartDetails.cartId = '" + cartId + "'");

        return cartItemsService.findAll(queryBuilder.toString(), pageable);
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

