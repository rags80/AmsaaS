/*
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * Description:
 *
 */

define(function(require) {
    
    	"use strict";
	//var urlToControllerMap = require("UrlMap");
	var router = require("Router");
	var routeMap = require("RouteMap");

	return {
		init : function(applnContext) {
			var self = this;
			self.applicationContext = applnContext;
			self.dispatcherContext = this;
			router.greedy = true;
			console.log("Application Context:"+self.applicationContext);
			console.log("Route map:");
			$.each(routeMap, function(i) {
				//console.log("Route:{" + "path:"+routeMap[i].path+ "   controller:"+routeMap[i].controller.toString()+"}");
				console.log("Route:"+ko.toJSON(routeMap[i]));
			    var routeObj=router.addRoute(routeMap[i].path, function(path,param) {
					console.log("Params:" + param);
					console.log("Path:" +path);
					routeMap[i].controller.init(path,param);
				});
			});
			
			
			self.applicationContext().delegate('a','click onhashchange', function() {
				var url = $(this).attr('href');
				//self.dispatcherContext.loadModule(url);
				console.log("URL:" + url);
				router.resetState();
				router.parse(url,[url.substring(1)]);
			});

		},

		forward : function(url) {
		    //this.loadModule('#' + url);
		    router.parse(url,[url.substring(1)]);
		},

	/*	loadModule : function(url) {
			var moduleController = urlToControllerMap[url];
			if (moduleController) {
				moduleController.init(this);
			}
		} */
	};
}); 