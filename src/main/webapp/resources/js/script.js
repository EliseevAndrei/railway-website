$(document).ready(function(){

    $('.nBtn, .table .eBtn').click(function(e) {

        e.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text === 'Edit') {
            $.getJSON(href, function(response) {
                $('.myForm #id').val(response.id);
                $('.myForm #name').val(response.name);
                $('.myForm #countCoupe').val(response.countCoupe);
                $('.myForm #countLying').val(response.countLying);
                $('.myForm #countCommon').val(response.countCommon);
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myForm #id').val('0').hide();
            $('.myForm #name').val('');
            $('.myForm #countCoupe').val('');
            $('.myForm #countLying').val('');
            $('.myForm #countCommon').val('');
        }
    });

    $('.rBtn').click(function(event) {

        event.preventDefault();

        var href=$(this).attr('href');
        $(".myRemove .dBtn").attr('href',href);
        $('.myRemove #exampleModal').modal();

    });

});
