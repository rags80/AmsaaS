/* 
 * Document   : JS
 * Author     : Raghavendra I Badiger
 * 
 * Description: 
 *  
 */

define(function (require) {
    /*
     * To avoid accidental global variable creation
     */
    "use strict";

    var urlController = require("Dispatcher");


    var app = {
        context: function () {
            return $("#appContent");
        },
        start: function (path) {

            $(function () {
                console.log("Application starting");
                urlController.init(app.context);
                console.log("Application started");
            });

        }
    };
    return app;
});