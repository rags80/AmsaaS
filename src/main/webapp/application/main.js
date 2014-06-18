//requirejs configurations
require.config({
    /*
     * AMD load by name configuration
     */
    
	paths: {
        /*
         * Application mvc framework
         */
        Application: '../lib/raghs-core/application',
        Dispatcher: '../lib/raghs-core/dispatcher',
        ViewResolver: '../lib/raghs-core/viewresolver',

        /*
         * Route map & style map of application
         */
        RouteMap: './config/routemap',
        ViewStyleMap: './config/viewstylemap',

        /* 
         * Libraries required!
         */
        Calendar:'../lib/fullcalendar/fullcalendar.min',
        Jquery:'../lib/jquery/jquery-1.8.3.min',
        Knockout:'../lib/knockout/knockout-latest',
        Moment: '../lib/moment/moment.min',
        Router: '../lib/crossroads/crossroads',
        Signals: '../lib/crossroads/signals',
        Text: '../lib/amd/text'
       
    }
});

define(function (require) {
    "use strict";
    var application = require("Application");
    // Start application 
    application.start("#home");
});