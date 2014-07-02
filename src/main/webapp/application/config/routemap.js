/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description: Route map contains page# links to controller mapping. 
 *  
 */

define(function(require) {
	"use strict";
	return [
			{
				path : '#home',
				controller : require("./modules/homemanager/controller/homecontroller")
			},
			{
				path : '#people',
				controller : require("./modules/usermanager/controller/usercontroller")
			},
			{
				path : '#services',
				controller : require("./modules/servicemanager/servicecatalogue/servicecontroller"),
			},
			{
				path : '#serviceplans/',
				controller : require("./modules/servicemanager/serviceplancatalogue/controller/serviceplancontroller")
			},
			{
				path : '#serviceplans/{srvcPlanName}/servicerates/',
				controller : require("./modules/servicemanager/serviceplancatalogue/controller/serviceratecontroller")
			}];
});