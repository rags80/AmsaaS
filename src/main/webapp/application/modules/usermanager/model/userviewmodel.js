define(function (require) {
    var userModelView = function (data) {
        this.dataModel = {
            persnFlatNum: ko.observable(data.persnAddress.flatNumber),
            persnFirstName: ko.observable(data.persnFirstName),
            persnLastName: ko.observable(data.persnLastName),
            persnPhoneNum: ko.observable(data.persnDetail.landlineNumber),
            persnMobileNum: ko.observable(data.persnDetail.mobileNumber),
            persnEmailId: ko.observable(data.persnDetail.emailId),
        };

        this.viewModel = {
            isIdEditable: ko.observable(false),
            isEditable: ko.observable(false),
            isSelected: ko.observable(false)
        };
    };
    return userModelView;
});
