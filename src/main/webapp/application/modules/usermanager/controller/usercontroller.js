define(function (require) {
    "use strict";

    var userViewTemplate = require('Text!../views/usermanager.html'),
        userModelView = require('../model/userviewmodel'),
        userRepo = require('repository/repository'),
        viewResolver = require("ViewResolver"),
        PagedGridView = require("modules/common/model/pagedgridmodel");

    var userController = {

        userPagedGridView: ko.observable(''),


        init: function (path, param) {
            console.log("init USER-CONTROLLER!!");
            userRepo.setPath(path);
            userRepo.findAll().then(function (usrData) {
                var mappedUserList = $.map(usrData, function (item) {
                    var usr = new userModelView(item);
                    return usr;
                });
                userController.userPagedGridView(new PagedGridView(mappedUserList, [ "1", "5", "10", "15" ], "5"));
            });

            viewResolver.renderModelView("people", this, userViewTemplate, "gridStyle");

        },

        submitDoc: function () {
            var data = new FormData($("form")[0]);

            $.ajax({
                url: "docs",
                type: "POST",
                data: data,
                dataType: "json",
                contentType: false,
                processData: false

            }).then(function (data) {
                console.log(data);
            });

            // console.log("Form submitted");

        }

    };

    return userController;

});