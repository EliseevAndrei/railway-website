$(document).ready(function () {


    $('.nBtn, .table .eBtn').click(function (e) {

        e.preventDefault();
        var href = $(this).attr('href');
        var id = $(this).attr('id');


        if (id === 'eBtn') {

            $.getJSON(href, function (response) {

                $(".myForm :input[type='text'], .myForm :input[type='number']").each(function () {
                    $(this).val(response[$(this).attr('id')]);
                });
                let rolesHtml = '';
                response.roles.forEach(function (e) {
                   rolesHtml += `<li id="${e.id}">${e.name}</li>`
                });
                $('#roles').append(rolesHtml);
            });

            $('.myForm #exampleModal').modal();
        } else {
            console.log("new");
            $(".myForm :input[type='text']").each(function () {
                $(this).val('');
            });
            $('.myForm #id').val('-1').hide();

            $('.myForm #exampleModal').modal();
        }
    });

    $('.myForm #edit-submit, .myForm #close-btn, .myForm #x-btn').click(function() {
        $('.myForm #errors').empty();
    });

    $('.myForm #edit-submit').click(function (event) {

        event.preventDefault();

        var href = $('.myForm #form').attr('action');
        var object = {};

        $(".myForm :input[type='text'], .myForm :input[type='number'], .myForm select").each(function () {
            object[$(this).attr('id')] = $(this).val();
        });

        var putMethod = 'PUT';
        var postMethod = 'POST';
        var method;
        if (object.id == -1) {
            method = postMethod;
        } else {
            href = href + "/" +object.id;
            method = putMethod;
        }
        $.ajax({
            url: href,
            type: method,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(object),
            success: function (data, textStatus, xhr) {
                $(location).attr('href', $('.container #reloadBtn').attr('href'));
            },
            error: function (xhr, textStatus, errorThrown) {
                let errorBlock =  $('.myForm #errors');
                let response = JSON.parse(xhr.responseText);
                response.errors.forEach(function(error) {
                    errorBlock.append("<p>"+ error +"</p>");
                    /*console.log(error);*/
                });
                errorBlock.css("display", "block");
            }
        })
    });

    let deleteHref;

    $('.rBtn').click(function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        deleteHref = href;
        $(".myRemove .dBtn").attr('href', href);
        $('.myRemove #exampleModal').modal();
    });

    $('.myRemove #delete-submit').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: deleteHref,
            type: 'DELETE',
            success: function (data, textStatus, xhr) {
                $(location).attr('href', $('.container #reloadBtn').attr('href'));
            },
            error: function (xhr, textStatus, errorThrown) {
                alert("Error with status" + textStatus + errorThrown);
            }
        })
    })

});

