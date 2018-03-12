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

import com.wavemaker.sampleapps.wavekart.eshopping.Orders;
import com.wavemaker.sampleapps.wavekart.eshopping.UserAddressDetails;
import com.wavemaker.sampleapps.wavekart.eshopping.service.UserAddressDetailsService;


/**
 * Controller object for domain model class UserAddressDetails.
 * @see UserAddressDetails
 */
@RestController("eshopping.UserAddressDetailsController")
@Api(value = "UserAddressDetailsController", description = "Exposes APIs to work with UserAddressDetails resource.")
@RequestMapping("/eshopping/UserAddressDetails")
public class UserAddressDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressDetailsController.class);

    @Autowired
	@Qualifier("eshopping.UserAddressDetailsService")
	private UserAddressDetailsService userAddressDetailsService;

	@ApiOperation(value = "Creates a new UserAddressDetails instance.")
@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
public UserAddressDetails createUserAddressDetails(@RequestBody UserAddressDetails userAddressDetails) {
		LOGGER.debug("Create UserAddressDetails with information: {}" , userAddressDetails);

		userAddressDetails = userAddressDetailsService.create(userAddressDetails);
		LOGGER.debug("Created UserAddressDetails with information: {}" , userAddressDetails);

	    return userAddressDetails;
	}

    @ApiOperation(value = "Returns the UserAddressDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserAddressDetails getUserAddressDetails(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting UserAddressDetails with id: {}" , id);

        UserAddressDetails foundUserAddressDetails = userAddressDetailsService.getById(id);
        LOGGER.debug("UserAddressDetails details with id: {}" , foundUserAddressDetails);

        return foundUserAddressDetails;
    }

    @ApiOperation(value = "Updates the UserAddressDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserAddressDetails editUserAddressDetails(@PathVariable("id") Integer id, @RequestBody UserAddressDetails userAddressDetails) throws EntityNotFoundException {
        LOGGER.debug("Editing UserAddressDetails with id: {}" , userAddressDetails.getUserAddresssId());

        userAddressDetails.setUserAddresssId(id);
        userAddressDetails = userAddressDetailsService.update(userAddressDetails);
        LOGGER.debug("UserAddressDetails details with id: {}" , userAddressDetails);

        return userAddressDetails;
    }

    @ApiOperation(value = "Deletes the UserAddressDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteUserAddressDetails(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting UserAddressDetails with id: {}" , id);

        UserAddressDetails deletedUserAddressDetails = userAddressDetailsService.delete(id);

        return deletedUserAddressDetails != null;
    }

    /**
     * @deprecated Use {@link #findUserAddressDetails(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of UserAddressDetails instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserAddressDetails> searchUserAddressDetailsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering UserAddressDetails list");
        return userAddressDetailsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of UserAddressDetails instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserAddressDetails> findUserAddressDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering UserAddressDetails list");
        return userAddressDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of UserAddressDetails instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserAddressDetails> filterUserAddressDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering UserAddressDetails list");
        return userAddressDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportUserAddressDetails(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return userAddressDetailsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of UserAddressDetails instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countUserAddressDetails( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting UserAddressDetails");
		return userAddressDetailsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getUserAddressDetailsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return userAddressDetailsService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/orderses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the orderses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Orders> findAssociatedOrderses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated orderses");
        return userAddressDetailsService.findAssociatedOrderses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserAddressDetailsService instance
	 */
	protected void setUserAddressDetailsService(UserAddressDetailsService service) {
		this.userAddressDetailsService = service;
	}

}
