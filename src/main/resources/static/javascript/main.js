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

