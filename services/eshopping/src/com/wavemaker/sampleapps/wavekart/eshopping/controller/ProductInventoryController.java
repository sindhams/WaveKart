/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.wavemaker.sampleapps.wavekart.eshopping.ProductInventory;
import com.wavemaker.sampleapps.wavekart.eshopping.service.ProductInventoryService;


/**
 * Controller object for domain model class ProductInventory.
 * @see ProductInventory
 */
@RestController("eshopping.ProductInventoryController")
@Api(value = "ProductInventoryController", description = "Exposes APIs to work with ProductInventory resource.")
@RequestMapping("/eshopping/ProductInventory")
public class ProductInventoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInventoryController.class);

    @Autowired
	@Qualifier("eshopping.ProductInventoryService")
	private ProductInventoryService productInventoryService;

	@ApiOperation(value = "Creates a new ProductInventory instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProductInventory createProductInventory(@RequestBody ProductInventory productInventory) {
		LOGGER.debug("Create ProductInventory with information: {}" , productInventory);

		productInventory = productInventoryService.create(productInventory);
		LOGGER.debug("Created ProductInventory with information: {}" , productInventory);

	    return productInventory;
	}

    @ApiOperation(value = "Returns the ProductInventory instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProductInventory getProductInventory(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting ProductInventory with id: {}" , id);

        ProductInventory foundProductInventory = productInventoryService.getById(id);
        LOGGER.debug("ProductInventory details with id: {}" , foundProductInventory);

        return foundProductInventory;
    }

    @ApiOperation(value = "Updates the ProductInventory instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProductInventory editProductInventory(@PathVariable("id") Integer id, @RequestBody ProductInventory productInventory) throws EntityNotFoundException {
        LOGGER.debug("Editing ProductInventory with id: {}" , productInventory.getProductId());

        productInventory.setProductId(id);
        productInventory = productInventoryService.update(productInventory);
        LOGGER.debug("ProductInventory details with id: {}" , productInventory);

        return productInventory;
    }

    @ApiOperation(value = "Deletes the ProductInventory instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteProductInventory(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting ProductInventory with id: {}" , id);

        ProductInventory deletedProductInventory = productInventoryService.delete(id);

        return deletedProductInventory != null;
    }

    /**
     * @deprecated Use {@link #findProductInventories(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of ProductInventory instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProductInventory> searchProductInventoriesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering ProductInventories list");
        return productInventoryService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of ProductInventory instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProductInventory> findProductInventories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering ProductInventories list");
        return productInventoryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of ProductInventory instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProductInventory> filterProductInventories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering ProductInventories list");
        return productInventoryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportProductInventories(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return productInventoryService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of ProductInventory instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countProductInventories( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting ProductInventories");
		return productInventoryService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getProductInventoryAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return productInventoryService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProductInventoryService instance
	 */
	protected void setProductInventoryService(ProductInventoryService service) {
		this.productInventoryService = service;
	}

}

