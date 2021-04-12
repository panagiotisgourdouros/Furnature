/*-------------------------------------------------------------- only allow super user to see users html--------------------------------------------------------------------------------*/
function isUserSuper() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  for (var i = 0; i < userDetails.roles.length; i++) {
    var name = userDetails.roles[i].roleName;
    if (name == "superuser") {
      return true;
    }
  }
  return false;
}

if (!isUserSuper()) {
  $("#adminUsers").css("display", "none");
} else {
  $("#adminUsers").css("display", "block");
}

/*-------------------------------------------------------------- toggle admin add forms----------------------------------------------------------------------------------------------- */

$(".adminAddProductsbtn").click(function () {
  $("#productbtn").removeClass("editbtn");
  $("#productbtn").addClass("submitAdminProductbtn");
  $(".adminAddProductsFormdiv").toggle();
});

$(".adminAddUsersbtn").click(function () {
  $(".adminAddUsersFormdiv").toggle();
});

/*-------------------------------------------------------------- GET administration lists-----------------------------------------------------------------------------------------------*/

function getAdminOrders() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/orders",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const allOrders = JSON.parse(data);
      localStorage.setItem("allOrders", JSON.stringify(allOrders));
      displayAllOrders(allOrders);
    },

    error: function (e) {
      console.log(e.message);
    },
  });
}
function getAdminUsers() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/superuser/admin",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const allAdmins = JSON.parse(data);
      localStorage.setItem("allAdmins", JSON.stringify(allAdmins));
      adminUsersSelect(allAdmins);
    },

    error: function (e) {
      console.log(e);
    },
  });
}
function getAdminProducts() {
  $.ajax({
    type: "GET",
    headers: {},
    contentType: "application/json",
    url: "http://localhost:8080/api/products",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const adminProducts = JSON.parse(data);
      adminProductsSelect(adminProducts);
      localStorage.setItem("adminProducts", data);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function getAdminCategories() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/category",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const adminCategories = JSON.parse(data);
      if (window.location.href == "http://127.0.0.1:5500/admin-other.html") {
        createCatTable(adminCategories);
      }
      localStorage.setItem("categories", data);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function getAdminCategoriesUpdate() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/category",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const adminCategories = JSON.parse(data);
      createCatTable(adminCategories);
      localStorage.setItem("categories", data);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function getAdminStyles() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/style",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const adminStyles = JSON.parse(data);
      localStorage.setItem("styles", data);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function getAdminStylesUpdate() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/style",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const adminStyles = JSON.parse(data);
      createStyleTable(adminStyles);
      localStorage.setItem("styles", data);
    },

    error: function (e) {
      console.log(e);
    },
  });
}
function getAdminMaterials() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken;
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/material",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const adminMaterials = JSON.parse(data);
      localStorage.setItem("materials", data);
    },

    error: function (e) {
      console.log(e.message);
    },
  });
}

function getAdminMaterialsUpdate() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken;
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "GET",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/material",
    dataType: "json",

    success: function (response) {
      const data = JSON.stringify(response);
      const adminMaterials = JSON.parse(data);
      createMatTable(adminMaterials);
      localStorage.setItem("materials", data);
    },

    error: function (e) {
      console.log(e.message);
    },
  });
}

function getLists() {
  getAdminCategories();
  getAdminStyles();
  getAdminMaterials();
  getAdminOrders();
}

/*-------------------------------------------------------------- create post requests----------------------------------------------------------------------------------------------- */
function createCategory() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  let name = $("#cname").val();

  $.ajax({
    type: "POST",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/category/" + name,
    dataType: "json",
    success: function (response) {
      console.log(response);
      $(".adminCatmessage").html("Category successfully created!");
    },

    error: function (e) {
      console.log(e);
    },
  });
}

$("#addCatbtn").click(function (event) {
  event.preventDefault();
  createCategory();
  setTimeout(function () {
    location.reload();
  }, 2000);
});

function createStyle() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  let name = $("#sname").val();

  $.ajax({
    type: "POST",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/style/" + name,
    dataType: "json",
    success: function (response) {
      console.log(response);
      $(".adminStylemessage").html("Style successfully created!");
    },

    error: function (e) {
      console.log(e);
    },
  });
}

$("#addStylebtn").click(function (event) {
  event.preventDefault();
  createStyle();
  setTimeout(function () {
    location.reload();
  }, 2000);
});

function createMaterial() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  let name = $("#mname").val();

  $.ajax({
    type: "POST",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/material/" + name,
    dataType: "json",
    success: function (response) {
      console.log(response);
      $(".adminMaterialmessage").html("Material successfully created!");
    },

    error: function (e) {
      console.log(e);
    },
  });
}

$("#addMaterialbtn").click(function (event) {
  event.preventDefault();
  createMaterial();
  setTimeout(function () {
    location.reload();
  }, 2000);
});

function createStyle() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  let name = $("#sname").val();

  $.ajax({
    type: "POST",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/style/" + name,
    dataType: "json",
    success: function (response) {
      console.log(response);
      $(".adminStylemessage").html("Style successfully created!");
    },

    error: function (e) {
      console.log(e);
    },
  });
}

$("#addStylebtn").click(function (event) {
  event.preventDefault();
  createStyle();
  setTimeout(function () {
    location.reload();
  }, 2000);
});

function createAdminUser() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;

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
  };

  $.ajax({
    type: "POST",
    headers: { Authorization: jwttoken },
    contentType: "application/json",
    url: "http://localhost:8080/api/superuser/createAdmin",
    data: JSON.stringify(formData),
    dataType: "json",
    success: function (response) {
      $(".adminUsermessage").html("Administrator created successfully!");
      window.setTimeout(function () {
        location.reload();
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

$(".submitAdminUserbtn").click(function (event) {
  event.preventDefault();
  createAdminUser();
});

/*-------------------------------------------------------------- edit admin ajax requests----------------------------------------------------------------------------------------------- */
function editAdminCategory(id, name) {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "PUT",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/category/" + id + "/" + name,
    dataType: "json",

    success: function (response) {
      $(".adminCatmessage").html("Update Successfull");
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function editAdminStyle(id, name) {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "PUT",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/style/" + id + "/" + name,
    dataType: "json",

    success: function (response) {
      $(".adminStylemessage").html("Update Successfull");
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function editAdminMaterial(id, name) {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "PUT",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url: "http://localhost:8080/api/admin/material/" + id + "/" + name,
    dataType: "json",

    success: function (response) {
      $(".adminMaterialmessage").html("Update Successfull");
      setTimeout(function () {
        location.reload();
      }, 1000);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function editAdminOrderStatus(id, name) {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken;
  var jwttoken = "Bearer " + jwtValue;
  $.ajax({
    type: "PUT",
    headers: {
      Authorization: jwttoken,
    },
    contentType: "application/json",
    url:
      "http://localhost:8080/api/admin/orders/updateStatus/" + id + "/" + name,
    dataType: "json",

    success: function (response) {
      console.log("success");
      $(".message").html("Update Successfull!");
    },

    error: function (e) {
      console.log(e);
    },
  });
}

function editAdminUser() {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;

  var formData = {
    id: $("#IDe").val(),
    title: $("#titleE").val(),
    username: $("#usernameE").val(),
    firstName: $("#firstNameE").val(),
    lastName: $("#lastNameE").val(),
    email: $("#emailE").val(),
    phoneNumber: $("#phoneNumberE").val(),
  };

  $.ajax({
    type: "PUT",
    headers: { Authorization: jwttoken },
    contentType: "application/json",
    url: "http://localhost:8080/api/superuser/updateAdmin",
    data: JSON.stringify(formData),
    dataType: "json",
    success: function (responseText) {
      $(".adminUsermessage").html(responseText.message);
      window.setTimeout(function () {
        location.reload();
      }, 2000);
    },

    error: function (e) {
      let errorMessage = JSON.parse(e.responseText).message;
      $(".adminUserError").html(errorMessage);
    },
  });
}

function deleteAdminUser(username) {
  let userDetails = JSON.parse(localStorage.getItem("authResponse"));
  var jwtValue = userDetails.jwtToken; //Etoimo na valeis Bearer
  var jwttoken = "Bearer " + jwtValue;

  $.ajax({
    type: "DELETE",
    headers: { Authorization: jwttoken },
    contentType: "application/json",
    url: "http://localhost:8080/api/superuser/deleteAdmin/" + username,
    dataType: "json",
    success: function (responseText) {
      $(".adminUsermessage").html(responseText.message);
      window.setTimeout(function () {
        location.reload();
      }, 2000);
    },

    error: function (e) {
      console.log(e);
    },
  });
}

/*-------------------------------------------------------------- display product drop down list----------------------------------------------------------------------------------------------- */

function adminProductsSelect(list) {
  var nameList = [];
  for (var i = 0; i < list.length; i++) {
    nameList.push(list[i].name);
  }
  var sortedList = nameList.sort();
  $("#adminProductsSelect").html("");
  let select = $("#select");
  select +=
    "<option class='selectProduct'> Click to Update Product Information</option>";
  for (var i = 0; i < sortedList.length; i++) {
    select +=
      "<option value=" + sortedList[i] + ">" + sortedList[i] + "</option>";
  }
  $("#adminProductsSelect").append(select);
}
$("#adminProductsSelect").click(function () {
  getAdminProducts();
});

$("#adminProductsSelect").change(function () {
  $(".adminProductsTablediv").empty();
  $(".productFromImg").css("display", "none");
  let selectedProduct = $(this).children("option:selected").val();
  populateProductForm(selectedProduct);
});

/*-------------------------------------------------------------- populate product form with selected product data----------------------------------------------------------------------------------------------- */
function populateProductForm(pname) {
  let products = JSON.parse(localStorage.getItem("adminProducts"));
  let form = $("#adminAddProductsForm");
  let materialsList = $(".materialsUl");
  for (var i = 0; i < products.length; i++) {
    if (products[i].name == pname) {
      $("#id").val(products[i].id);
      $("#name").val(products[i].name);
      $("#description").val(products[i].description);
      $("#price").val(products[i].price);
      $("#stock").val(products[i].stock);
      let discount = products[i].discount;
      if (discount === null || discount == "0") {
        $("#discount").val("0");
      }
      $("#sku").val(products[i].sku);
      let category =
        '<option value="' +
        getIdFromCategory(products[i].category) +
        '">' +
        products[i].category +
        "</option>";
      $("#category").prepend(category);
      $("#category").val(getIdFromCategory(products[i].category));
      let style =
        '<option value="' +
        getIdFromStyle(products[i].style) +
        '">' +
        products[i].style +
        "</option>";
      $("#style").prepend(style);
      $("#style").val(getIdFromStyle(products[i].style));
      for (var j = 0; j < products[i].materials.length; j++) {
        let li =
          "<li  class=' list-group-item'>" +
          products[i].materials[j].name +
          "</li>";
        materialsList.append(li);
      }
    }
  }
  $("#productbtn").removeClass("submitAdminProductbtn");
  $("#productbtn").addClass("editbtn");
  $("#productbtn").html("Edit");
  $(".adminInput").attr("readonly", true);
  $(".selectSC").attr("disabled", true);
  $(".materialscheckdiv").css("display", "none");
  $(".materialsList").css("display", "block");
  $(".filledProductsForm").append(form);
}

/*----------------------------------------------populate style dropdown with existing styles----------------------------------------------------------------------------*/
function addSelectStyleOptions() {
  $("#style").empty();
  let styles = JSON.parse(localStorage.getItem("styles"));
  let select = $("#style");
  let emptyOption = '<option value="">Select Style</option>';
  for (let i = 0; i < styles.length; i++) {
    let text = [styles[i].name];
    let value = [styles[i].id];
    let option1 = document.createElement("OPTION");
    let txt = document.createTextNode(text);
    option1.appendChild(txt);
    option1.setAttribute("value", value);
    select.append(option1);
  }
  select.prepend(emptyOption);
}

$("#style").focus(function () {
  addSelectStyleOptions();
});

/*----------------------------------------------populate category dropdown with existing categories---------------------------------------------------------------------------*/

function addSelectCatOptions() {
  $("#category").empty();
  let categories = JSON.parse(localStorage.getItem("categories"));
  let select = $("#category");
  for (let i = 0; i < categories.length; i++) {
    let text = [categories[i].name];
    let value = [categories[i].id];
    let option1 = document.createElement("OPTION");
    let txt = document.createTextNode(text);
    option1.appendChild(txt);
    option1.setAttribute("value", value);
    select.append(option1);
  }
}

$("#category").focus(function () {
  addSelectCatOptions();
});

$(".cancelbtn").click(function () {
  location.reload();
});
/*----------------------------------------------get style and category id--------------------------------------------------------------------------*/

function getIdFromCategory(name) {
  var list = JSON.parse(localStorage.getItem("categories"));
  for (var i = 0; i < list.length; i++) {
    if (list[i].name == name) {
      return list[i].id;
    }
  }
}

function getIdFromStyle(name) {
  var list = JSON.parse(localStorage.getItem("styles"));
  for (var i = 0; i < list.length; i++) {
    if (list[i].name == name) {
      return list[i].id;
    }
  }
}

/*-------------------------------------------------------------- when product editbtn is clicked make inputs editable and in second click send post to edit product----------------------------------------------------------------------------------------------- */
$(document).on("click", ".editbtn", function () {
  var $edit = $(this);
  if (!$edit.data("clicked")) {
    $(".adminInput").attr("readonly", false);
    $(".selectSC").attr("disabled", false);
    $(".materialscheckdiv")
      .find("input[type=checkbox]:checked")
      .removeAttr("checked");
    $(".materialscheckdiv").css("display", "block");
    $(".productFromImg").css("display", "block");
    $(".materialsList").css("display", "block");
    // $(".adminInput").removeClass("no-border");
    $(".editbtn").html("Save");
  } else {
    $("#productbtn").removeClass("editbtn");
    $("#productbtn").addClass("submitAdminProductbtn");
  }
  $edit.data("clicked", true);
});

/*---------------------------------------------------display categories table and edit categories----------------------------------------------------------------------------------------------------------------------------------------------*/

function createCatTable(list) {
  let table = "";
  table += "<thead>";
  table += "<th>Category Id</th>";
  table += " <th>Category Name</th>";
  table += " <th></th>";
  table += "</thead>";
  for (var i = 0; i < list.length; i++) {
    table += "<tbody>";
    table += "<tr>";
    table +=
      '<td><input type="text" class="form-control no-border adminCatInput input" disabled size="3" value="' +
      list[i].id +
      '"</input></td>';
    table +=
      '<td><input type="text" class="form-control no-border adminCatInput input" size="3" readonly value="' +
      list[i].name +
      '"</input></td>';
    table +=
      '<td><button id="editCategory" class="btn btn-default editCatbtn">Edit</button> </td>';
    table += "<tr>";
    table += "</tbody>";
  }
  $(".adminCatTable").append(table);
}

$(document).on("click", "#editCategory", function () {
  var $edit = $(this);
  var parent = $edit.closest("td").parent("tr");
  var name = $edit.closest("td").prev("td").find(".adminCatInput");
  var id = parent.find("td:first").find(".adminCatInput");
  if (!$edit.data("clicked")) {
    name.attr("readonly", false);
    name.removeClass("no-border");
    $edit.html("Save");
  } else {
    editAdminCategory(id.val(), name.val());
    $edit.html("Edit");
    name.attr("readonly", true);
    setTimeout(function () {
      location.reload();
    }, 1000);
  }
  $edit.data("clicked", true);
});
/*--------------------------------------------------display style table and edit style----------------------------------------------------------------------------------------------------------*/
function createStyleTable(list) {
  let table = "";
  table += "<thead>";
  table += "<th>Style Id</th>";
  table += " <th>Style Name</th>";
  table += " <th></th>";
  table += "</thead>";
  for (var i = 0; i < list.length; i++) {
    table += "<tbody>";
    table += "<tr>";
    table +=
      '<td><input type="text" class="form-control no-border adminStyleInput input" disabled size="3" value="' +
      list[i].id +
      '"</input></td>';
    table +=
      '<td><input type="text" class="form-control no-border adminStyleInput input" size="3" readonly value="' +
      list[i].name +
      '"</input></td>';
    table +=
      '<td><button id="editStyle" class="btn btn-default editStylebtn">Edit</button> </td>';
    table += "<tr>";
    table += "</tbody>";
  }
  $(".adminStyleTable").append(table);
}

$(document).on("click", "#editStyle", function () {
  var $edit = $(this);
  var parent = $edit.closest("td").parent("tr");
  var name = $edit.closest("td").prev("td").find(".adminStyleInput");
  var id = parent.find("td:first").find(".adminStyleInput");
  if (!$edit.data("clicked")) {
    name.attr("readonly", false);
    name.removeClass("no-border");
    $edit.html("Save");
  } else {
    editAdminStyle(id.val(), name.val());
    //  $edit.prop("disabled", true);
    $edit.html("Edit");
    name.attr("readonly", true);
    setTimeout(function () {
      location.reload();
    }, 1000);
  }
  $edit.data("clicked", true);
});
/*---------------------------------------------------display materials table and edit material-----------------------------------------------------------------------------------------------------------*/

function createMatTable(list) {
  let table = "";
  table += "<thead>";
  table += "<th>Material Id</th>";
  table += " <th>Material Name</th>";
  table += " <th></th>";
  table += "</thead>";
  for (var i = 0; i < list.length; i++) {
    table += "<tbody>";
    table += "<tr>";
    table +=
      '<td><input type="text" class="form-control no-border adminMaterialInput input" disabled size="3" value="' +
      list[i].id +
      '"</input></td>';
    table +=
      '<td><input type="text" class="form-control no-border adminMaterialInput input" size="3" readonly value="' +
      list[i].name +
      '"</input></td>';
    table +=
      '<td><button id="editMaterial" class="btn btn-default editMaterialbtn">Edit</button> </td>';
    table += "<tr>";
    table += "</tbody>";
  }
  $(".adminMaterialTable").append(table);
}

$(document).on("click", "#editMaterial", function () {
  var $edit = $(this);
  var parent = $edit.closest("td").parent("tr");
  var name = $edit.closest("td").prev("td").find(".adminMaterialInput");
  var id = parent.find("td:first").find(".adminMaterialInput");

  if (!$edit.data("clicked")) {
    name.attr("readonly", false);
    name.removeClass("no-border");
    $edit.html("Save");
  } else {
    editAdminMaterial(parseInt(id.val()), name.val());
    $edit.html("Edit");
    name.attr("readonly", true);
  }
  $edit.data("clicked", true);
});

function createCSMTables() {
  getAdminCategoriesUpdate();
  getAdminStylesUpdate();
  getAdminMaterialsUpdate();
}
/*-------------------------------------------display admin orders (in between specific dates)  and update order status-------------------------------------------------------------------*/

$(".selectDatesbtn").click(function () {
  list = JSON.parse(localStorage.getItem("allOrders"));
  $(".adminOrderTable").empty();
  displayAllOrders(list);
});

function displayAllOrders(list) {
  let from = $("#from").val();
  let to = $("#to").val();

  let table = "";

  for (var i = 0; i < list.length; i++) {
    if (
      list[i].datePlaced.slice(0, 10) >= from &&
      list[i].datePlaced.slice(0, 10) <= to
    ) {
      $(".message").css("display", "none");
      table += "<thead>";
      table += "<th>Order Id</th>";
      table += "<th>Date Placed</th>";
      table += "<th>Total Discount</th>";
      table += "<th>Total Amount</th>";
      table += "<th>Shipping Address</th>";
      table += '<th colspan="4">Status</th>';
      table += "</thead>";
      table += '<tbody class="orderTableBody">';
      table += "<tr>";
      table += "<td>" + list[i].id + "</td>";
      table += "<td>" + list[i].datePlaced.slice(0, 10) + "</td>";
      table += "<td>" + list[i].totalDiscount + "</td>";
      table += "<td>" + list[i].totalAmount + "</td>";
      table +=
        "<td>" +
        list[i].shipAddressDto.houseNumber +
        ", " +
        list[i].shipAddressDto.city +
        ", " +
        list[i].shipAddressDto.postalCode +
        ", " +
        list[i].shipAddressDto.country +
        "</td>";
      table +=
        '<td class="adminStatusInput"><input type="text" class="form-control no-border adminStatusInput input" size="3" readonly value="' +
        list[i].status.name +
        '"</input></td>';
      table +=
        '<td class="selectStatus"><select name="status" class="form-select" id="status" >';
      table += '<option class="statusoption" value="pending">Pending</option>';
      table +=
        '<option class="statusoption" value="processing">Processing</option>';
      table +=
        '<option class="statusoption" value="completed">Completed</option>';
      table +=
        '<option class="statusoption" value="refunded">Refunded</option>';
      table += "</select>";

      table +=
        '<td colspan="3"><button class="btn updateStatusbtn" >Update Status</button></td>';
      table += "</tr>";
      table += '<tr  class="statusesError">';
      table +=
        '<td colspan="8">Choose from: pending, completed, processing, refunded</td>';
      table += "</tr>";
      table += '<tr  class="bold" >';
      table += '<td colspan="9">Products</td>';
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

      table += "</tr>";
      table += '<tr class="adminProductFields">';
      for (let k = 0; k < list[i].ordersDetailsDtoList.length; k++) {
        table +=
          '<td><img class="adminProductImage" src="' +
          list[i].ordersDetailsDtoList[k].product.productImagePaths[0] +
          '" alt=""/></td>';
        table += "<td>" + list[i].ordersDetailsDtoList[k].product.sku + "</td>";
        table +=
          "<td>" + list[i].ordersDetailsDtoList[k].product.name + "</td>";
        table +=
          "<td>" +
          list[i].ordersDetailsDtoList[k].product.description +
          "</td>";
        table +=
          "<td>" + list[i].ordersDetailsDtoList[k].product.category + "</td>";
        table +=
          "<td>" + list[i].ordersDetailsDtoList[k].product.style + "</td>";
        table += "<td>" + list[i].ordersDetailsDtoList[k].quantity + "</td>";
        table +=
          "<td>" + list[i].ordersDetailsDtoList[k].product.price + "</td>";
        table += "</tr>";
      }
      table += "</tbody>";
    }
  }
  $(".adminOrderTable").append(table);
}
/*--------------------------------------------------------update the status of orders-------------------------------------------*/
$(document).on("click", ".updateStatusbtn", function () {
  var $edit = $(this);
  $(".selectStatus").css("display", "block");
  $(".adminStatusInput").css("display", "none");
  var parent = $edit.closest("td").parent("tr");
  var id = parent.find("td:first").html();
  var selected = $("#status").children("option:selected");
  var name = selected.val();
  console.log(name);

  if (!$edit.data("clicked")) {
    $edit.html("Save");
  } else {
    editAdminOrderStatus(id, name);
    $edit.html("Edit");
    setTimeout(function () {
      location.reload();
    }, 1000);
  }
  $edit.data("clicked", true);
});
/*-------------------------------------------populate users select and fill form with selected user information and update / delete----------------------------------------------------------------------*/

function adminUsersSelect(list) {
  $("#adminUsersSelect").html("");
  let select = $("#select");
  select +=
    "<option class='selectUser'> Click to Update User Information</option>";
  for (var i = 0; i < list.length; i++) {
    select +=
      "<option value=" +
      list[i].username +
      ">" +
      list[i].id +
      ". " +
      list[i].firstName +
      " " +
      list[i].lastName +
      "</option>";
  }
  $("#adminUsersSelect").append(select);
}
$("#adminUsersSelect").click(function () {
  getAdminUsers();
});

$("#adminUsersSelect").change(function () {
  $(".adminUsersTablediv").empty();
  let name = $(this).children("option:selected").val();
  console.log(name);
  createUserForm(name);
});

function createUserForm(name) {
  $(".adminUsersFormdiv").html("");
  let adminUsers = JSON.parse(localStorage.getItem("allAdmins"));
  let form = "";
  for (var i = 0; i < adminUsers.length; i++) {
    if (adminUsers[i].username == name) {
      form +=
        '<form id="adminEditUsersForm" role="form" class="row g-3 needs-validation validatorServerSide" novalidate>';
      form += '<div class="col-md-2">';
      form +=
        ' <input type="hidden" class="form-control" id="titleE" name="title" disabled value="' +
        adminUsers[i].title +
        '"</input>';
      form += '<label  for="idE">Id</label>';
      form +=
        ' <input type="text" class="form-control" id="idE" name="id" disabled value="' +
        adminUsers[i].id +
        '"</input>';
      form += "</div>";
      form += '<div class="col-md-5">';
      form += '<label  for="firstNameE">First Name</label>';
      form +=
        '<input type="text" class="form-control adminUserInput" id="firstNameE" name="firstNameE" placeholder="First Name" data-v-min-length="2" readonly required value="' +
        adminUsers[i].firstName +
        '"</input>';
      form += "</div>";
      form += '<div class="col-md-5">';
      form += '<label  for="lastNameE">Last Name</label>';
      form +=
        '<input type="text" class="form-control adminUserInput" id="lastNameE" name="lastNameE" placeholder="Last Name" data-v-min-length="2" readonly required value="' +
        adminUsers[i].lastName +
        '"</input>';
      form += "</div>";
      form += '<div class="col-md-4">';
      form += '<label  for="emailE">Email</label>';
      form +=
        '<input type="text" class="form-control adminUserInput" id="emailE" name="emailE" placeholder="Email Address" data-v-min-length="2"  readonly required value="' +
        adminUsers[i].email +
        '"</input>';
      form += "</div>";
      form += '<div class="col-md-4">';
      form += '<label  for="phoneNumberE">Phone Number</label>';
      form +=
        '<input type="text" class="form-control adminUserInput" id="phoneNumberE" name="phoneNumberE" placeholder="  Phone Number" data-v-min-length="8" readonly required value="' +
        adminUsers[i].phoneNumber +
        '"</input>';
      form += "</div>";
      form += '<div class="col-md-4">';
      form += '<label  for="usernameE">Username</label>';
      form +=
        '<input type="text" class="form-control adminUserInput" id="usernameE" name="usernameE" placeholder="Username" data-v-min-length="8" readonly required value="' +
        adminUsers[i].username +
        '"</input>';
      form += "</div>";
      form += '<div class="col-md-12 updateUserbtndiv">';
      form += '<div class="adminUserError"></div>';
      form +=
        '<button id="updateUserbtn" type="button" class="btn adminUsersbtns" >Update </button>';
      form +=
        '<button id="deleteUserbtn" type="button"  class="btn adminUsersbtns" >Delete </button>';
      form += "</div>";
      form += "</form>";
    }
  }

  $(".adminUsersFormdiv").append(form);
}

$(document).on("click", "#updateUserbtn", function () {
  var $edit = $(this);

  if (!$edit.data("clicked")) {
    $edit.html("Save");
    $(".adminUserInput").attr("readonly", false);
  } else {
    editAdminUser();
    $edit.html("Edit");
  }
  $edit.data("clicked", true);
});

$(document).on("click", "#deleteUserbtn", function () {
  var username = $("#usernameE").val();
  deleteAdminUser(username);
});
/*----------------------------------------------------------------------------check if order status has been written correctly ------------------------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------admin users ------------------------------------------------------------------------------------------*/
