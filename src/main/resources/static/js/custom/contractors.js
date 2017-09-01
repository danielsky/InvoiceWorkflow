$( document ).ready(function() {
    $(document).on("click", ".remove-contractor", function () {
        var contractorId = $(this).data('id');
        $("#deleteContractorForm").attr('action', 'contractor/'+contractorId+'/delete' );
    });
});