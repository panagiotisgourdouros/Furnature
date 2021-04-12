$(document).ready(function () {
  // --------------------Register----------------------------------------------------------------------------------------------------------

  $("#registerform").submit(function (event) {
    event.preventDefault();
    register();
  });

  function register() {
    let validatorServerSide = $("form.validatorServerSide").jbvalidator({
      errorMessage: true,
      successClass: false,
    });

    var formData = {
      firstName: $("#fname").val(),
      lastName: $("#lname").val(),
      email: $("#email").val(),
      phoneNumber: $("#tel").val(),
      title: $("#title").val(),
      username: $("#username").val(),
      password: $("#password").val(),
      paypalAcc: null,
    };

    $.ajax({
      type: "POST",
      headers: {},
      contentType: "application/json",
      url: "http://localhost:8080/api/register",
      data: JSON.stringify(formData),
      dataType: "json",
      success: function (responseText) {
        $("#result").html(responseText.message);
        if (responseText.message == "User registered successfully!") {
          window.setTimeout(function () {
            window.location.href = "login-form.html";
          }, 2000);
        }
      },

      error: function (e) {
        let errorDetails = JSON.parse(e.responseText).details;

        let errorFirstWord = errorDetails.split(" ")[0].toLowerCase();
        if (errorFirstWord == "first" || errorFirstWord == "last") {
          validatorServerSide.errorTrigger(
            $("[name=" + errorFirstWord + "Name]"),
            errorDetails
          );
        } else if (errorFirstWord == "phone") {
          validatorServerSide.errorTrigger(
            $("[name=" + errorFirstWord + "Number]"),
            errorDetails
          );
        } else {
          validatorServerSide.errorTrigger(
            $("[name=" + errorFirstWord + "]"),
            errorDetails
          );
        }
      },
    });
  }
  //------------------------USER LOGIN -------------------------------------------------------------------------------------------------------------------------

  $("#loginform").submit(function (event) {
    event.preventDefault();
    logIn();
  });

  function logIn() {
    var formData = {
      username: $("#username").val(),
      password: $("#password").val(),
    };
    $.ajax({
      type: "POST",
      headers: {},
      contentType: "application/json",
      url: "http://localhost:8080/api/authenticate",
      data: JSON.stringify(formData),
      dataType: "json",
      success: function (response) {
            if (response.jwtToken != null) {
          $("#result").html("Login sucessfull. Redirecting to Dashboard...");
          console.log(response.jwtToken);
          localStorage.setItem("authResponse", JSON.stringify(response));
          setTimeout(function () {
            window.location.href = "dashboard.html";
          }, 2000);
        }
      },

      error: function (e) {
        let errorDetails = JSON.parse(e.responseText).message;
        $("#incorectUsernamePass").html(errorDetails);
        $("#loginform").trigger("reset");
      
      },
    });
  }



});
