$( document ).ready(function() {

    $('.approve-service').parent().hide();

    $(document).on('click', '.edit-service', function () {
        $(this).parent().parent().prev().removeAttr('readonly');
        $(this).parent().prev().show();
        $(this).parent().hide();
    });

    $(document).on('click', '.approve-service', function () {

        var btnGroup2 = $(this).parent().next();
        var btnGroup1 = $(this).parent();

        var input = $(this).parent().parent().prev();
        var value = $(input).val();
        var serviceId = $(input).data('identifier');
        var contractorId = $('#contractorId').val();
        var csrf = $('#csrf').val();

        $.ajax({
            method: "POST",
            url: contractorId+"/service/"+serviceId+"/update",
            headers: {
                'X-CSRF-TOKEN': csrf
            },
            data: {
                newName: value
            }
        }).done(function() {
            $(input).attr('readonly','readonly');
            $(btnGroup1).hide();
            $(btnGroup2).show();
        });


    });

    $(document).on('click', '.cancel-service', function () {
        var input = $(this).parent().parent().prev();
        input.attr('readonly','readonly');
        input.val(input.data('org'));

        $(this).parent().next().show();
        $(this).parent().hide();
    });

    var template = $('#template').html();

    $(document).on('click', '.add-service', function () {
        var input = $(this).parent().prev();
        var value = $(input).val();
        var contractorId = $('#contractorId').val();
        var csrf = $('#csrf').val();

        $.ajax({
            method: "POST",
            url: contractorId+"/service/add",
            headers: {
                'X-CSRF-TOKEN': csrf
            },
            data: {
                newName: value
            }
        }).done(function(msg) {
            console.log(msg);
            $(input).val('');
            var row = $(template).clone();
            $(row).find('input').data('identifier',msg);
            $(row).find('input').data('org',value);
            $(row).find('input').val(value);
            $('#services').append($(row));
        });
    });
});