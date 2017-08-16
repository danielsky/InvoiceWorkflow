$( document ).ready(function() {
    // $('#selector').selectpicker('refresh');
    //
    // $('#selector').on('changed.bs.select', function (e) {
    //     console.log(e);
    // });

    $('#selector').selectpicker();

    $('#selector.selectpicker').on('change', function(e){
        console.log(e);
    });
});