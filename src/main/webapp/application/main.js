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
         *
         * Route map & style map of application
         *
         */
        RouteMap: './config/routemap',
        ViewStyleMap: './config/viewstylemap',

        /*
         * Libraries required!
         */
        Text: '../lib/amd/text',
        Moment: '../lib/moment/moment.min',
        Router: '../lib/crossroads/crossroads',
        signals: '../lib/crossroads/signals'

    }
});

define(function (require) {
    "use strict";
    var application = require("Application");

    // Start application once DOM gets loaded
    /* $(function() {
     application.start("#home");
     }); */

    application.start("#home");
});