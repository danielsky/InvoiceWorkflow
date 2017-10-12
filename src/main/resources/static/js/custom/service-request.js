$( document ).ready(function() {

    $('#docUpload').on('submit', function (event) {
        event.preventDefault();
        var formData = new FormData($('#docUpload'));
        var urlData = $('#docUpload').attr('action');
        $.ajax({
            type: 'POST',
            url: urlData,
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentData: false,
            success: function(){
                alert('File uploaded');
            }
        })
    });

});