$( document ).ready(function() {

    $('.approve-service').parent().hide();

    $(document).on('click', '.edit-service', function () {
        $(this).parent().parent().prev().removeAttr('readonly');
        $(this).parent().prev().show();
        $(this).parent().hide();
    });

    $(document).on('click', '.approve-service', function () {
        $(this).parent().parent().prev().attr('readonly','readonly');
        $(this).parent().next().show();
        $(this).parent().hide();
    });

    $(document).on('click', '.cancel-service', function () {
        var input = $(this).parent().parent().prev();
        input.attr('readonly','readonly');
        input.val(input.data('org'));

        $(this).parent().next().show();
        $(this).parent().hide();
    });
});