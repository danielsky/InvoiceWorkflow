$( document ).ready(function() {

    //on change function i need to control selected value
    $('#contractor').on('change', function () {
        var selected = $(this).val();
        console.log(selected);
        $('#contractorService').append(new Option('optionName', selected));
        $('#contractorService').selectpicker('refresh');
    });

});