$( document ).ready(function() {

    //var documentDorm = $('#docUpload');

    $('#docUpload').on('submit', function (event) {
        event.preventDefault();
        var formData = new FormData(this);
        var urlData = $(this).attr('action');
        $.ajax({
            xhr: function(){
                var xhr = new window.XMLHttpRequest();

                xhr.upload.addEventListener('progress', function (e) {
                   if(e.lengthComputable){
                       //console.log('Bytest loaded: '+e.loaded);
                       //console.log('Total size: '+e.total);
                       //console.log('Percentage: '+(e.loaded/e.total));

                       var percentage = Math.round((e.loaded/e.total) * 100);
                       $("#progressBar")
                           .attr('aria-valuenow', percentage)
                           .css('width', percentage+'%')
                           .text(percentage+'%');
                   }
                });

                return xhr;
            },
            type: 'POST',
            url: urlData,
            data: formData,
            processData: false,
            contentType: false,
            success: function(id){
                console.log(id);
                location.reload();
            }
        })
    });

});