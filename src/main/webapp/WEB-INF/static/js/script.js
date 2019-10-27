$(document).ready(function(){

    console.log("started");

    $('.nBtn, .table .eBtn').click(function(e){
        e.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text == 'Edit') {
            $.getJSON(href, function(response){
                $('.myForm #id').val(response.id).hide();
                $('.myForm #nama').val(response.nama);
                $('.myForm #jurusan').val(response.jurusan);
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myForm #id').val('0').hide();

            $('.myForm #nama').val('');
            $('.myForm #jurusan').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    $('.rBtn').click(function(event) {
        event.preventDefault();
        var href=$(this).attr('href');
        $(".myRemove .dBtn").attr('href',href);
        $('.myRemove #exampleModal').modal();

    });

});