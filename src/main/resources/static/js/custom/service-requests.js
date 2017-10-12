$( document ).ready(function() {

    $(document).on("click", ".remove-service-request", function () {
        var invoiceId = $(this).data('id');
        $("#deleteInvoiceForm").attr('action', 'request/'+invoiceId+'/delete' );
    });

});