


$(document).ready(function () {
  
  $("#nav").load("nav.html", function () {
    //if a user is logged in:
    if (localStorage.getItem("authResponse") != null) {

      $("#body").on("load", showUserDetails());

        if(isUserAdmin()==false){
          $("#login").css("display", "none");
          $("#user").css("display", "block");
          $("#cart-icon").css("display", "block");
          $(".chatli").css("display", "block");
          $("#switchtoadmin").css("display", "none");
          $("#home").attr("href", "dashboard.html");
          getCartTotalQuantity();
        } else{
          $("#login").css("display", "none");
          $("#user").css("display", "block");
          $("#cart-icon").css("display", "block");
          $(".chatli").css("display", "block"); 
          $("#switchtoadmin").css("display", "block");
          $("#home").attr("href", "dashboard.html");
          getCartTotalQuantity();
        }
       
      } else {
        $("#login").css("display", "block");
        $("#user").css("display", "none");
        $("#cart-icon").css("display", "none");
        $("#switchtoadmin").css("display", "none");
        $("#home").attr("href", "index.html");
      }
    

    $("#logout").click(function (event) {
      event.preventDefault();
      localStorage.clear();
      $("#welcome").html("You have sucessfully logged out");
      setTimeout(function () {
        window.location.href = "index.html";
      }, 2000);
    });

    function showUserDetails() {
      let userDetails = JSON.parse(localStorage.getItem("authResponse"));
      $("#myAccount").html(userDetails.firstName + " " + userDetails.lastName);
    }
   
    
    function isUserAdmin(){
      let userDetails = JSON.parse(localStorage.getItem("authResponse"));
      for(var i=0; i<userDetails.roles.length; i++){
        var name = userDetails.roles[i].roleName;
        if(name == "admin"){
          return true;
        }
      }
    
      return false;
    }

    //modal should ONLY close with the close button
    $("#cart").modal({
      backdrop: "static",
      keyboard: false,
    });

    
    $(".navbar-toggler").click(function () {
      $("nav").css("background-color","white");
      })


    if (window.location.href == "http://127.0.0.1:5500/admin-dashboard.html" || 
    window.location.href == "http://127.0.0.1:5500/admin-products.html" ||
    window.location.href == "http://127.0.0.1:5500/admin-other.html" ||
    window.location.href == "http://127.0.0.1:5500/admin-users.html" ||
    window.location.href == "http://127.0.0.1:5500/admin-orders.html"
    ) {
      $("#switchtoadmin").css("display", "none");
      $("#switchtocust").css("display", "block");
      $("#cart-icon").css("display", "none");
      $(".shop").css("display", "none");
      $(".userdropdown").css("display", "none");
    }

    $("#switchtocust").click(function (event) {
      event.preventDefault();
      window.location.href = "dashboard.html";
    });

    $(".orderHistory").click(function (event) {
      event.preventDefault();
      window.location.href = "order-history.html";
    });

  //  if (window.location.href == "http://127.0.0.1:5500/products.html") {
  //    getAllproducts();
  //  }
  

    if (
      window.location.href == "http://127.0.0.1:5500/order-review.html"
    ) {
      createTableOfProducts();
      createOrderDetailsForm();
    }

    $("#cart-icon").click(function (event) {
      event.preventDefault();
      getCartInfo();
      $("#cart").modal("toggle");
      $("#close-modal").click(function () {
        $("#cart").modal("hide");
        $("#display").html("");
        getCartTotalQuantity();
        console.log(getCartTotalQuantity());
      });
    });

  });
 
});
