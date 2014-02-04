/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description: This script file contains view related functions & templates.
 *  
 */

define(function(require) {
    "use strict";
    return {
	initPage : function() {
	    $("#mainPageTabs").tabs();

	    ko.bindingHandlers.datepicker = {
		init : function(element, valueAccessor, allBindingsAccessor) {

		    /*
		     * Initialize datepicker with some optional options
		     */

		    $(element).datepicker({
			dateFormat : 'd-M-yy'
		    });

		    /*
		     * Handle the field changing
		     */
		    ko.utils.registerEventHandler(element, "change", function() {
			var observable = valueAccessor();
			$(element).datepicker({
			    dateFormat : 'd-M-yy'
			});
			observable($(element).datepicker("getDate"));
		    });

		},

		update : function(element, valueAccessor) {
		    var value = ko.utils.unwrapObservable(valueAccessor()), current = $(element).datepicker("getDate");

		    if (value - current !== 0) {
			$(element).datepicker("setDate", value);
		    }
		}

	    };

	}
    };

});
