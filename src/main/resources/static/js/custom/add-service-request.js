$( document ).ready(function() {

    //on change function i need to control selected value
    $('#contractor').on('change', function () {
        var contractorId = $(this).val();

        $('#contractorService').empty();
        $('#contractorService').selectpicker('refresh');

        $.ajax({
            method: "GET",
            url: contractorId+"/services"
        }).done(function(result) {
            $.each( result, function( key, value ) {
                $('#contractorService').append(new Option(value.name, value.id));
            });
            $('#contractorService').selectpicker('refresh');
        });
    });

});