Application.$controller("PaymentProcessPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */

    }
    $scope.getCurrentYear = function() {
        var yr = new Date().getFullYear();
        return yr;
    }

    function successFn(orderId, i) {
        var dataObj = $scope.Variables.LV_CartItemsData.dataSet.data[i];
        if (i < $scope.Variables.LV_CartItemsData.dataSet.data.length) {
            $scope.Variables.LV_InsertOrderLineItems.setInput({
                "productId": dataObj.productId,
                "quantity": dataObj.productQuantity,
                "pricePerUnit": dataObj.currentPrice,
                "productLineAmount": dataObj.totalPrice,
                "orderId": orderId
            });
            $scope.Variables.LV_InsertOrderLineItems.createRecord({}, function(data) {
                $scope.Variables.SV_UpdateQuantityFromPI.setInput({
                    "qty": data.quantity,
                    "productId": data.productId
                });
                $scope.Variables.SV_UpdateQuantityFromPI.invoke({}, function() {
                    successFn(orderId, ++i);
                });
            });
        } else {
            $scope.Variables.goToPage_Orders.invoke();
        }
    }



    $scope.LV_InsertOrdersonSuccess = function(variable, data) {
        successFn(data.orderId, 0);
    };





    $scope.wizard_paymentDone = function($isolateScope, steps) {
        $scope.Variables.LV_InsertOrders.setInput('orderDate', Date.now());
    };

    $scope.form_field14Keypress = function($event, $isolateScope) {
        var charCode = ($event.which) ? $event.which : $event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            $event.preventDefault();
        }
    };


    $scope.SV_getInventoryDetailsonBeforeUpdate = function(variable, inputData) {
        var pids = _.map($scope.Variables.LV_CartItemsData.dataSet.data, 'productId');
        inputData.productIds = pids.join(',');
    };


    $scope.SV_getInventoryDetailsonSuccess = function(variable, data) {
        var requiredQuantities = _.map($scope.Variables.LV_CartItemsData.dataSet.data, 'productQuantity'),
            quantities = _.map(data.content, 'quantity'),
            validOrder = _.every(quantities, function(item, index) {
                return item >= requiredQuantities[index];
            });

        if (validOrder) {
            $scope.Variables.LV_InsertOrders.setInput('orderDate', Date.now());
            $scope.Variables.LV_InsertOrders.createRecord();
        } else {
            $scope.Variables.Cancel_outOfStock.invoke();
        }
    };


    $scope.radioset5Change = function($event, $isolateScope, newVal, oldVal) {
        $scope.Widgets.select4.datavalue = undefined;
    };

}]);




Application.$controller("useraddressdetailsDialogController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("formDialog_EditaddController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform_EditAddressController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);