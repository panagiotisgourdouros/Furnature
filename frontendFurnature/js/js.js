$(document).ready(function () {
  //-----------------------Generic error handling function for fetch -----------------------------------------------------------------------------------------

  let handleErrors = (response) => {
    if (!response.ok) {
      console.log(response);
      throw Error(response.message);
    }

    return response.message;
  };

  //-------------------------------password eye---------------------------------------------------------------------------------------------------

  $(".eye").click(function () {
    let x = document.getElementById("password");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  });

  //----------------------------------------------------------validator----------------------------------------*/

  $(function () {
    let validator = $("form.needs-validation").jbvalidator({
      errorMessage: true,
      successClass: true,
      validFeedBackClass: "valid-feedbak",
      invalidFeedBackClass: "invalid-feedback",
      validClass: "is-valid",
      invalidClass: "is-invalid",
    });

    validator.validator.custom = function (el, event) {
      if (
        $(el).is("[name=password]") &&
        ($(el).val().length < 8 || $(el).val() == $(el).val().toLowerCase())
      ) {
        return "Password should contain at least 8 characters and 1 Uppercase Letter";
      }
    };

    validator.validator.custom = function (el, event) {
      var phoneno = /^\+?([0-9]{2})\)?[-]?([0-9]{4})[-]?([0-9]{6})$/;
      if ($(el).is("[name=phoneNumber]") && !$(el).val().match(phoneno)) {
        return "Include country code - Phone number should be have the format +XX-XXXX-XXXXXX";
      }
    };
  });
});
