$(document).ready(function () {
  //-------------------------------------------------------------GET ADDRESSES AND DISPLAY IN ADDRESS BOOK-----------------------------------------------------------------------------
  $("#addressbody").on("load", getAddresses());

  function getAddresses() {
    let userDetails = JSON.parse(localStorage.getItem("authResponse"));
    var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
    var jwttoken = "Bearer " + jwtValue;
    $.ajax({
      type: "GET",
      headers: {
        Authorization: jwttoken,
      },
      contentType: "application/json",
      url: "http://localhost:8080/api/users/addresses",
      dataType: "json",

      success: function (response) {
        const data = JSON.stringify(response);
        const addresses = JSON.parse(data);
        console.log(addresses);

        if (addresses.message) {
          $("#message").html(
            "No addresses registered yet. Please register a shipping Address in your Adress Book"
          );
          $("#addaddressbutton").css("display", "block");
        } else {
          localStorage.setItem("addresses", JSON.stringify(addresses));
          displayAdresses(addresses);
        }
      },

      error: function (e) {
        console.log(e);
      },
    });
  }

  function displayAdresses(addresses) {
    for (var i = 0; i < addresses.length; i++) {
      console.log(addresses[i].id);
      var count = i + 1;
      var $div = $("<div></div>")
        .addClass("col-lg-3")
        .addClass("col-md-6")
        .attr("id", "addrressformdiv");
      var $form = $("<form></form>");
      $form
        .append("<h3></h3>")
        .html("Address " + count)
        .addClass("font-weight-light text-center row g-1");

      $form.append(
        $("<input/>").val(addresses[i].streetName).addClass("form-control")
      );
      $form.append(
        $("<input/>").val(addresses[i].houseNumber).addClass("form-control")
      );
      $form.append(
        $("<input/>").val(addresses[i].city).addClass("form-control")
      );
      $form.append(
        $("<input/>").val(addresses[i].postalCode).addClass("form-control")
      );
      $form.append(
        $("<input/>").val(addresses[i].country).addClass("form-control")
      );
      if (addresses[i].shipping == 1) {
        $form.append(
          $("<input/>").val("Shipping Address").addClass("form-control")
        );
      } else {
        $form.append(
          $("<input/>").val("Billing Address").addClass("form-control")
        );
      }
      $form.append(
        $("<button></button>")
          .html("Delete Address")
          .attr("class", "deleteaddress")
          .attr("id", addresses[i].id)
          // .attr("click", deleteAddress(addresses[i].id))
          .addClass("btn btn-dark  btn-sm addressbutton")
      );
      var $gap = $("<br/>");
      $form.appendTo($div);
      $gap.appendTo($div);
      $div.appendTo("#addresses");
      $("#addaddressbutton").css("display", "block");
    }
  }

  //---------------------------Add Address---------------------------------------------------------------------------

  $("#addnewaddress").click(function (event) {
    event.preventDefault();
    addAddress();
  });

  function addAddress() {
    let validatorServerSide = $("form.validatorServerSide").jbvalidator({
      errorMessage: true,
      successClass: false,
    });

    var userDetails = JSON.parse(localStorage.getItem("authResponse"));
    var jwtValue = userDetails.jwtToken;
    var jwttoken = "Bearer " + jwtValue;

    var formData = {
      streetName: $("#streetName").val(),
      houseNumber: $("#houseNumber").val(),
      postalCode: $("#postalCode").val(),
      city: $("#city").val(),
      country: $("#country").val(),
      shipping: $("#shipping").is(":checked"),
      billing: $("#billing").is(":checked"),
      myuser_id: userDetails.id,
    };

    $.ajax({
      type: "POST",
      headers: { Authorization: jwttoken },
      contentType: "application/json",
      url: "http://localhost:8080/api/users/createaddress",
      data: JSON.stringify(formData),
      dataType: "json",
      success: function (response) {
        $("#result").html(response.message);
        setTimeout(function () {
          window.location.href = "addresses.html";
        }, 2000);
      },

      error: function (e) {
        let errorDetails = JSON.parse(e.responseText).details;
        let errorFirstWord = errorDetails.split(" ")[0].toLowerCase();
        let namefirstword = $("input[name^='" + errorFirstWord + "']");
        validatorServerSide.errorTrigger(namefirstword, errorDetails);
      },
    });
  }

  //------------------------------------------delete address--------------------------------------------------------------

  $(document).on("click", "button.deleteaddress", function (e) {
    e.preventDefault();
    deleteAddress(this.id);
    // $(this).parent().parent().remove();
  });

  function deleteAddress(id) {
    var userDetails = JSON.parse(localStorage.getItem("authResponse"));
    var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
    var jwttoken = "Bearer " + jwtValue;

    $.ajax({
      type: "DELETE",
      headers: { Authorization: jwttoken },
      contentType: "application/json",
      url: "http://localhost:8080/api/users/deleteaddress/" + id,
      dataType: "json",
      success: function (response) {
        $("#message").html(response.message);
        $("button.deleteaddress").parent().parent().remove();
      },

      error: function (e) {
        $("#deleteAddressmsg").html(
          "You cannot delete this shipping address as it has been associated with one of your orders."
        );
        window.setTimeout(function () {
          location.reload();
        }, 2000);
      },
    });
  }
});

/*------------------------------------------------------------------------select with address options--------------------------------------------------------------------------------- */

function createAddressesOptions() {
  $("#selectAdd").empty();
  let addresses = JSON.parse(localStorage.getItem("addresses"));
  console.log(addresses);
  let select = $("#selectAdd");
  for (let i = 0; i < addresses.length; i++) {
    if (addresses[i].shipping == true) {
      let text = [
        addresses[i].streetName,
        addresses[i].houseNumber,
        addresses[i].city,
        addresses[i].postalCode,
        addresses[i].country,
      ];
      let value = [addresses[i].id];
      let option1 = document.createElement("OPTION");
      let txt = document.createTextNode(text);
      option1.appendChild(txt);
      option1.setAttribute("value", value);
      select.append(option1);
    }
  }
}

$("#selectAdd").focus(function () {
  createAddressesOptions();
});

$("#selectAdd").change(function () {
  console.log($(this).children("option:selected").val());
});
