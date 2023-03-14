//apply pagination and table functionalities such as fuzzy search to item table in admin portal
   $(document).ready(function() {
        $("#itemListTable").DataTable();
    });


//add item to shopping cart. Uses ajax to call rest web controller
 $(document).ready(function() {
  $('#Add2CartButton').click(function(e) {
    e.preventDefault(); // prevent the form from submitting normally
    var itemId = $('#itemId').val(); // get the item ID from the hidden input field
    var qty = $('#qty').val(); // get the selected quantity from the dropdown

        $.post('/add-to-cart/' + itemId + '/' + qty, function(data) {
          //console.log(data); // display the response message in the console
            alert(data);
        });

  });
});

//update subtotal and total in shopping cart
$(document).ready(function(){
   updateTotal();
});

function updateSubtotal(input) {
    // Get the quantity and price of the item
    var quantity = parseInt(input.value);
    var price = parseFloat(input.parentElement.previousElementSibling.textContent);

    // Calculate the subtotal for the item
    var subtotal = quantity * price;

    // Update the item subtotal in the table
    var itemSubtotal = input.parentElement.nextElementSibling;
    itemSubtotal.textContent = subtotal;

    // Update the total in the order details section
    updateTotal();
}


function updateTotal(){
    total = 0.0;

    $(".itemSubtotal").each(function(index, element){
        total = total + parseFloat(element.innerHTML);

    });

    $("#totalAmount").text("$" + total);
}


//preserve the updated item in shopping cart to database
 $(document).ready(function() {
     $('table').on('change', 'input[type="number"]', function() {
         var itemId = $(this).closest('tr').data('itemid');
         var qty = $(this).val();
         $.ajax({
             url: '/update-cart/' + itemId + '/' + qty,
             type: 'POST',
             success: function(result) {
                 alert(result);
             },
             error: function(xhr, status, error) {
                 alert(xhr.responseText);
             }
         });
     });
 });