/*-------------------------------------------------------get orders of user to display at order history-----------------------------------------------------------*/
function getUserOrders() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/users/orders",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const orders = JSON.parse(data);
      displayUserOrders(orders);
      localStorage.setItem("userorders", JSON.stringify(orders));
      if (orders.length == 0 || orders===null) {
        $(".message").html("There are no orders to show.");
      }
    },

    error: function (e) {
      console.log(e.message);
    },
  });
}

function displayUserOrders(list) {
  let table = "";
  for (var i = 0; i < list.length; i++) {
    table += "<thead>";
    table += "<th>Order Id</th>";
    table += "<th>Date Placed</th>";
    table += "<th>Total Discount</th>";
    table += "<th>Total Amount</th>";
    table += "<th>Shipping Address</th>";
    table += '<th colspan="3">Status</th>';
    table += "</thead>";
    table += '<tbody class="orderTableBody">';
    table += "<tr>";
    table += "<td>" + list[i].id + "</td>";
    table += "<td>" + list[i].datePlaced.slice(0, 10) + "</td>";
    table += "<td>" + list[i].totalDiscount + "</td>";
    table += "<td>" + list[i].totalAmount + "</td>";
    table += '<td>'+list[i].shipAddressDto.houseNumber +
      ", " +
      list[i].shipAddressDto.city +
      ", " +
      list[i].shipAddressDto.postalCode +
      ", " +
      list[i].shipAddressDto.country +
      "</td>";
    table += "<td>" + list[i].status.name + "</td>";
    table += '<td colspan="3"></td>';
    table += "</tr>";
    table += '<tr  class="statusesError">';
    table +=
      '<td colspan="8">Choose from: pending, completed, processing, refunded</td>';
    table += "</tr>";
    table += '<tr  class="bold" >';
    table += '<td colspan="8">Products</td>';
    table += "</tr>";
    table += '<tr class="adminProductFields">';
    table += "<td>Photo</td>";
    table += "<td>Sku</td>";
    table += "<td>Name</td>";
    table += "<td>Description</td>";
    table += "<td>Category</td>";
    table += "<td>Style</td>";
    table += "<td>Quantity</td>";
    table += "<td>Price</td>";
    table += "<td></td>";
    table += "</tr>";
    table += '<tr class="adminProductFields">';
    for (let k = 0; k < list[i].ordersDetailsDtoList.length; k++) {
      table +=
        '<td><img class="adminProductImage" src="' +
        list[i].ordersDetailsDtoList[k].product.productImagePaths[0] +
        '" alt=""/></td>';
      table += "<td>" + list[i].ordersDetailsDtoList[k].product.sku + "</td>";
      table += "<td>" + list[i].ordersDetailsDtoList[k].product.name + "</td>";
      table +=
        "<td>" + list[i].ordersDetailsDtoList[k].product.description + "</td>";
      table +=
        "<td>" + list[i].ordersDetailsDtoList[k].product.category + "</td>";
      table += "<td>" + list[i].ordersDetailsDtoList[k].product.style + "</td>";
      table += "<td>" + list[i].ordersDetailsDtoList[k].quantity + "</td>";
      table += "<td>" + list[i].ordersDetailsDtoList[k].product.price + "</td>";
      table += "</tr>";
    }
    table += "</tbody>";
  }
  $(".adminOrderTable").append(table);
}

//----------------------------get cart information------------------------------------------------

function getCartInfo() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/users/getCart",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const cartInfo = JSON.parse(data);

      if (cartInfo.length == 0) {
        $("#display").html("Your Cart is empty");
        $("#checkout").css("display", "none");
      } else {
        localStorage.setItem("cartInfo", JSON.stringify(cartInfo));
        displayCart(cartInfo);
      }
    },

    error: function (e) {
      console.log(e);
    },
  });
}

//----------------------------add product------------------------------------------------

function addProductToCart(id) {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;

  $.ajax({
    type: "POST",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/users/addProductToCart/" + id,
    dataType: "json",

    success: function (response) {
      console.log(response.message);
    },

    error: function (e) {
      console.log(e);
    },
  });
}
//----------------------------remove product------------------------------------------------
function removeProductFromCart(id) {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken;
  var jwttoken = "Bearer " + jwtValue;

  $.ajax({
    type: "DELETE",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/users/removeProductFromCart/" + id,
    dataType: "json",

    success: function (response) {
      console.log(response.message);
    },

    error: function (e) {
      console.log(e);
    },
  });
}
//----------------------------display cart info in modal dynamically-----------------------------------------------
function displayCart(cartInfo) {
  if ($("#display").html != "") {
    for (var i = 0; i < cartInfo.length; i++) {
      let initialQuantity = cartInfo[i][6];
      let productprice = cartInfo[i][5];
      var productdiv = $("<div></div>").addClass("product");
      var itemdiv = $("<div></div>").addClass("row item");

      var imgdiv = $("<div></div>").addClass("col-4 imagediv");
      var img = $("<img></img>").addClass("image").attr("src", cartInfo[i][2]);
      img.appendTo(imgdiv);

      var quantity = $("<div></div>").addClass("col-4 quantity");
      var quant = $("<p></p>").attr("id", "quant").html("Quantity");
      var input = $("<input/>")
        .attr("class", "quantityinput")
        .attr("type", "number")
        .val(cartInfo[i][6])
        .attr("id", cartInfo[i][9])
        .change(function () {
          if ($(this).val() > initialQuantity) {
            addProductToCart($(this).attr("id"));
            updateLinearPrice(this, productprice);
          } else {
            removeProductFromCart($(this).attr("id"));
            updateLinearPrice(this, productprice);
          }
        });
      quant.appendTo(quantity);
      input.appendTo(quantity);

      var pricediv = $("<div></div>").addClass("col-4 pricediv");
      var price = $("<div></div>")
        .addClass("price")
        .html(productprice * initialQuantity);
      var pricetitle = $("<div></div>")
        .attr("id", "pricetitle")
        .html("Price (&#128;)");
      pricetitle.appendTo(pricediv);
      price.appendTo(pricediv);
      imgdiv.appendTo(itemdiv);
      quantity.appendTo(itemdiv);
      pricediv.appendTo(itemdiv);

      var description = $("<div></div>").addClass("col-12 description");
      var sku = $("<span></span>").html("Code: " + cartInfo[i][1]);
      var name = $("<span></span>").html(cartInfo[i][3]);
      var descr = $("<span></span>").html(cartInfo[i][4]);
      sku.appendTo(description);
      name.appendTo(description);
      descr.appendTo(description);

      itemdiv.appendTo(productdiv);
      description.appendTo(productdiv);
      productdiv.appendTo("#display");
    }
    var totaldiv = $("<div></div>").addClass("total");
    var totaltitle = $("<div></div>").html("Total (&#128;)");
    var total = $("<div></div>").addClass("totalprice").html(cartInfo[0][8]);
    totaltitle.appendTo(totaldiv);
    total.appendTo(totaldiv);
    totaldiv.appendTo("#display");
  }
}

/*----------------------------------------------------- Update quantity-------------------------------------------------------------------------------- */
function updateLinearPrice(quantityInput, price) {
  var fadeTime = 300;
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  //var price = parseFloat(productRow.children('.price').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;
  if (linePrice == 0) {
    productRow.parent().fadeOut(fadeTime);
  }

  /* Update line price display and recalc cart totals */
  productRow.find(".price").each(function () {
    $(this).fadeOut(fadeTime, function () {
      $(this).text(linePrice.toFixed(2));
      recalculateCart(quantityInput);
      $(this).fadeIn(fadeTime);
    });
  });
}

/*-------------------------------------------------------------- Recalculate cart----------------------------------------------------------------------------------------------- */
function recalculateCart() {
  var fadeTime = 300;
  var total = 0;

  /* Sum up row totals */
  $(".item").each(function () {
    if (!isNaN($(this).find(".price").html())) {
      total += parseFloat($(this).find(".price").html());
    }
  });

  /* Update totals display */
  $(".totalprice").fadeOut(fadeTime, function () {
    $(".totalprice").html(total.toFixed(2));
    if (total == 0) {
      $("#checkout").fadeOut(fadeTime);
      $("#display").html("Your cart is empty");
    } else {
      $("#checkout").fadeIn(fadeTime);
    }
    $(".totalprice").fadeIn(fadeTime);
  });
}

/*-------------------------------------------------------------- show products ----------------------------------------------------------------------------------------------- */
var ul = $(".list li");
ul.click(function (event) {
  var fadeTime = 300;
  let id = event.target.id;
  var filter = $(this).parent().attr("id");
  console.log(filter);
  event.preventDefault();
  $(".products").empty();
  $(".products").fadeIn(fadeTime);
  getproductsBy(id, filter);
});

/*-------------------------------------------------------------- get products by---------------------------------------------------------------------------------------------- */

function getproductsBy(id, filter) {
  $.ajax({
    type: "GET",
    headers: {},
    contentType: "application/json",
    url: "http://localhost:8080/api/products/" + filter + "/" + id,
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const products = JSON.parse(data);
      displayProducts(products);

      if (products.message == "No Products to show") {
        $("#display").html(products.message);
      }
    },

    error: function (e) {
      console.log(e);
    },
  });
}

/*-------------------------------------------------------------- get all products---------------------------------------------------------------------------------------------- */

function getAllproducts() {
  $.ajax({
    type: "GET",
    headers: {},
    contentType: "application/json",
    url: "http://localhost:8080/api/products",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const allProducts = JSON.parse(data);
      localStorage.setItem("products", data);
      displayProducts(allProducts);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

/*-------------------------------------------------------------- display products----------------------------------------------------------------------------------------------- */

function displayProducts(list) {
  var display = "";
  display += '<div class="row">';
  for (var i = 0; i < list.length; i++) {
    display += '<div class="outside-div col-lg-4 col-md-12"">';
    display += '<div class="product-item row">';
    display += '<div class="product-image col-6">';
    display +=
      '<img class="productImage" src="..' +
      list[i].productImagePaths[0] +
      '" alt=""/>';
    display += "</div>";
    display += '<div class="product-details col-6">';
    display += '<div class="product-name">' + list[i].name + "</div>";
    display += '<div class="product-sku">Code:' + list[i].sku + "</div>";
    display +=
      '<div class="product-description">' + list[i].description + "</div>";
    display +=
      '<div class="product-price">' +
      parseFloat(list[i].price) +
      "&#128;</div>";
    if (list[i].stock == 0) {
      display += '<div class="soldout"> Sold Out! </div>';
    }
    display += '<div class="addtocart">';
    display +=
      ' <button class="bth btn-submit addtocartbtn" id="' +
      list[i].id +
      '"/>Buy it!</button>';
    display += " </div>";
    display += " </div>";
    display += " </div>";
    display += " </div>";
  }
  display += " </div>";

  $(".products").append(display);
}

$(document).on("click", ".addtocartbtn", function (event) {
  let id = event.target.id;
  if (localStorage.getItem("authResponse") != null) {
    addProductToCart(id);
    let oldvalue = parseInt($("#cart-quantity").html());
    $("#cart-quantity").html(parseInt(oldvalue) + 1);
    console.log($("#cart-quantity").html());
  } else {
    $(".message").html("Please login in order to shop..");
    window.setTimeout(function () {
      window.location.href = "login-form.html";
    }, 2000);
  }
});

/*-------------------------------------------------------------- getTotal cart quantity---------------------------------------------------------------------------------------------- */
function getCartTotalQuantity() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/users/getCartTotalQuantity",

    dataType: "json",

    success: function (response) {
      let quantity = parseInt(response);
      console.log(quantity);
      $("#cart-quantity").html(quantity);
    },

    error: function (e) {
      console.log(e);
    },
  });
}
/*-------------------------------------------------------------------------on cart checkout send user to review his order and select adress---------------------------------------------------------------- */
$(document).on("click", "#checkout", function (event) {
  event.preventDefault();
  window.location.href = "order-review.html";
});

function getcheckoutPayPal() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;

  var userId = userDetails.id;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/payments/paypal/" + userId,
    dataType: "json",

    success: function (response) {
      console.log(response.message);
    },

    error: function (e) {
      console.log(e);
    },
  });
}
/*------------------------------------------------------------------------products table in review order page---------------------------------------------------------------------------------- */
function createTableOfProducts() {
  $("#productsTableBody").empty();
  let cartInfo = JSON.parse(localStorage.getItem("cartInfo"));
  console.log(cartInfo);
  let table = $("#productsTableBody");
  for (var i = 0; i < cartInfo.length; i++) {
    table += "<tr>";
    table += "<td>" + cartInfo[i][1] + "</td>";
    table += "<td>" + cartInfo[i][3] + "</td>";
    table += "<td>" + cartInfo[i][4] + "</td>";
    table += "<td>" + cartInfo[i][6] + "</td>";
    table += "<td>" + cartInfo[i][5] + "</td>";
    table += "<tr>";
  }

  $("#productsTable").append(table);
}

/*------------------------------------------------------------------------order detaiis form in review order page---------------------------------------------------------------------------------- */

function createOrderDetailsForm() {
  $(".orderreviewform").empty();
  let cartInfo = JSON.parse(localStorage.getItem("cartInfo"));
  let price = parseInt(cartInfo[0][8]);
  let form = "";
  form += '<label for="price" class="form-label small">Total Price</label>';
  form +=
    '<input type=text name="price" class="form-control rounded-right" id="price" value="' +
    price +
    '"</>';
  form += '<label for="currency" class="form-label small">Currency</label>';
  form +=
    '<input type=text name="currency" class="form-control rounded-right" id="currency" value="EUR"</>';
  form += '<label for="method" class="form-label small">Method</label>';
  form +=
    '<input type=text name="method" class="form-control rounded-right" id="method" value="PayPal"</>';
  form +=
    '<input type=hidden name="intent" class="form-control rounded-right" id="intent" value="sale"</>';
  form +=
    '<input type=hidden name="description" class="form-control rounded-right" id="description" value="FRevive"</>';

  $(".orderreviewform").append(form);
}

function createOrderReviewPage() {
  createTableOfProducts();
  createOrderDetailsForm();
}

$(".checkoutbtn").click(function (event) {
  event.preventDefault();
  $("#redirectmsg").html("Please wait to get derirected to PayPal...");
  $(".dot-flashing").css("display", "block");
  pay();
});

function pay() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;

  var formData = {
    price: $("#price").val(),
    currency: $("#currency").val(),
    method: $("#method").val(),
    intent: $("#intent").val(),
    description: $("#description").val(),
  };

  $.ajax({
    type: "POST",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/paypal/pay",
    data: JSON.stringify(formData),
    dataType: "json",
    success: function (response) {
      console.log(response.message);
      window.location.href = response.message;
    },

    error: function (e) {
      console.log(e);
    },
  });
}
/*--------------------------------------------------------------send cart id when pay pal payment is successfull-------------------------------------------*/
function payPalConfirm() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "POST",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/users/orders",
    dataType: "json",
    success: function (response) {
      console.log(response.message);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function redirectDashboard() {
  window.setTimeout(function () {
    window.location.href = "dashboard.html";
  }, 2000);
}
/*--------------------------------------------------------------combine and run on success page loading-------------------------------------------*/

function paymentSuccess() {
  payPalConfirm();
  redirectDashboard();
}
