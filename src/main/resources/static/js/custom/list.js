$( document ).ready(function() {
    $(document).on("click", ".remove-invoice", function () {
        var invoiceId = $(this).data('id');
        $("#deleteInvoiceForm").attr('action', 'invoice/'+invoiceId+'/delete' );
    });
});