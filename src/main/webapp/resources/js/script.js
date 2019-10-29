$(document).ready(function(){

    $('.nBtn, .table .eBtn').click(function(e) {

        e.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        console.log(href);

        if(text === 'Edit') {
            $.getJSON(href, function(response) {
                $('.myForm #id').val(response.id);
                $('.myForm #name').val(response.name);
                $('.myForm #countCoupe').val(response.countCoupe);
                $('.myForm #countLying').val(response.countLying);
                $('.myForm #countCommon').val(response.countCommon);
                console.log(response);
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myForm #id').val('-1').hide();
            $('.myForm #name').val('');
            $('.myForm #countCoupe').val('');
            $('.myForm #countLying').val('');
            $('.myForm #countCommon').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    $('.myForm #edit-submit').click(function (event) {
        event.preventDefault();
       var href = $('.myForm #form').attr('action');
       var train = {};
       train.id = $('.myForm #id').val();
       train.name = $('.myForm #name').val();
       train.countCoupe =$('.myForm #countCoupe').val();
       train.countLying = $('.myForm #countLying').val();
       train.countCommon = $('.myForm #countCommon').val();
       var putMethod = 'PUT';
       var postMethod = 'POST';
       var method;
       if (train.id == -1) {
           method = postMethod;
       } else {
           href = href + train.id;
           method = putMethod;
       }
       console.log(href);
       $.ajax({
           url: href,
           type: method,
           dataType: "json",
           contentType: "application/json",
           data: JSON.stringify(train),
           success: function(data, textStatus, xhr) {
               $(location).attr('href', "/trains");
           },
           error: function(xhr, textStatus, errorThrown) {
               alert("Error with status" + textStatus + errorThrown);
           }
       })
    });

    var deleteHref;

    $('.rBtn').click(function(event) {
        event.preventDefault();
        var href=$(this).attr('href');
        deleteHref = href;
        console.log(href);
        $(".myRemove .dBtn").attr('href',href);
        $('.myRemove #exampleModal').modal();
    });

    $('.myRemove #delete-submit').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: deleteHref,
            type: 'DELETE',
            success: function(data, textStatus, xhr) {
                $(location).attr('href', "/trains");
            },
            error: function(xhr, textStatus, errorThrown) {
                alert("Error with status" + textStatus + errorThrown);
            }
        })
    })

});
