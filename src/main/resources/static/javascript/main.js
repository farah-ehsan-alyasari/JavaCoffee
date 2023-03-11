   //apply pagination and table functionalities such as fuzzy search to item table in admin portal
   $(document).ready(function() {
        $("#itemListTable").DataTable();
    });

    //add item to cart by pressing the button add item
/*$(document).ready(function(){
    $("#Add2CartButton").on("click", function(e) {
        addToCart();
    });
});*/

//using jQuery ajax method to call a web service in our rest controller
/*$(document).ready(function() {
  $('#Add2CartButton').click(function(e) {
    e.preventDefault(); // prevent default form submit action

    console.log("HELLLLO");
    // get form data
    var form = $('#addToCartForm');
    var formData = form.serialize();

    // send form data to server
    $.ajax({
      url: form.attr('action'),
      type: form.attr('method'),
      data: formData,
      success: function(response) {
        // handle success response
        console.log(response);
      },
      error: function(xhr, status, error) {
        // handle error response
        console.log(xhr.responseText);
      }
    });
  });
});
*/

 $(document).ready(function() {
  $('#Add2CartButton').click(function(e) {
    e.preventDefault(); // prevent the form from submitting normally
    var itemId = $('#itemId').val(); // get the item ID from the hidden input field
    var qty = $('#qty').val(); // get the selected quantity from the dropdown
    console.log("HIIII");
    $.post('/add-to-cart/' + itemId + '/' + qty, function(data) {
      alert(data); // display the response message in an alert box
    });
  });
});

