$(document).ready(function () {
  //-------------------------------------Update User-----------------------------------------------------------------------------------------------------------

  $("#profileform").submit(function (event) {
    event.preventDefault();
    update();
  });

  function update() {
    let validatorServerSide = $("form.validatorServerSide").jbvalidator({
      errorMessage: true,
      successClass: false,
    });

    var formData = {
      firstName: $("#firstName").val(),
      lastName: $("#lastName").val(),
      email: $("#email").val(),
      phoneNumber: $("#phoneNumber").val(),
      title: $("#title").val(),
      username: $("#username").val(),
      password: $("#password").val(),
      paypalAcc: $("#paypalAcc").val(),
    };

    let userDetails = JSON.parse(localStorage.getItem("authResponse"));
    var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
    var jwttoken = "Bearer " + jwtValue;

    $.ajax({
      type: "PUT",
      headers: {
        Authorization: jwttoken,
      },
      contentType: "application/json",
      url: "http://localhost:8080/api/users/update",
      data: JSON.stringify(formData),
      dataType: "json",
      success: function (responseText) {
        $("#result").html(responseText.message);
        if (
          responseText.message ==
          "Your Personal Information was successfully updated"
        ) {
          window.setTimeout(function () {
            window.location.href = "dashboard.html";
          }, 2000);
        }
      },

      error: function (e) {
        console.log(e);
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

  // ----------------------USER PROFILE IN DASHBOARD--------------------------------------------------------------------------------------------

  $("#profile").click(function (event) {
    event.preventDefault();
    setTimeout(function () {
      window.location.href = "profile.html";
    }, 1000);
  });

  // ----------------------forgot password SEND EMAIL--------------------------------------------------------------------------------------------

  $("#forgotPassForm").submit(function (event) {
    event.preventDefault();
    forgotPass();
  });


  function forgotPass() {
    var formData = {
      email: $("#email").val(),
    };
    fetch("http://localhost:8080/reset/forgot_password", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
        return Promise.reject(response);
      })
      .then((result) => {
        console.log(result);
        $("#responseForgotPass").html(result.message);
      })
      .catch((error) => {
        console.log("Something went wrong.", error);
        $("#responseForgotPass").html("Invalid Password");
      });
  }

  //------------------------------------------UPDATE PASSWORD-------------------------------------------------

  $("#resetPassform").submit(function (event) {
    event.preventDefault();
    resetPass();
  });

  function resetPass() {
    let validatorServerSide = $("form.validatorServerSide").jbvalidator({
      errorMessage: true,
      successClass: false,
    });

    var myParam = location.search.split("reset_password_token=")[1];
    console.log(myParam);
    var formData = {
      resetPasswordToken: myParam,
      password: $("#password").val(),
    };

    $.ajax({
      type: "POST",
      headers: {},
      contentType: "application/json",
      url: "http://localhost:8080/reset/reset_password_by_token",
      data: JSON.stringify(formData),
      dataType: "json",
      success: function (responseText) {
        $("#result").html(responseText.message);
        if (responseText.message == "Your password has been changed") {
          window.setTimeout(function () {
            window.location.href = "login-form.html";
          }, 2000);
        }
      },

      error: function (e) {
        console.log(e);
      },
    });
  }
});
