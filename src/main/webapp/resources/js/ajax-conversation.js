$(document).ready(function () {


    $('.nBtn, .table .eBtn').click(function (e) {

        e.preventDefault();
        var href = $(this).attr('href');
        var id  = $(this).attr('id');


        if (id === 'eBtn') {

            $.getJSON(href, function (response) {

                $(".myForm :input[type='text'], .myForm :input[type='number'], .myForm select").each(function () {

                    $(this).val(response[$(this).attr('id')]);


                });
                $(".myForm #arriveTime, .myForm #departureTime").each(function () {
                    let str = response[$(this).attr('id')].replace("@", " ");
                    str = str.slice(0, 16);

                    $(this).val(str);
                });
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

        $(".myForm #arriveTime, .myForm #departureTime").each(function () {
            let str = $(this).val().replace(/\s+/g, '');
            let date = str.substring(0, 10);
            let time = str.substring(10, 16);
            str = date + " " + time;
            object[$(this).attr('id')] = str;
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

    $('.customDataTable').on('click', '.rBtn' ,(function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        deleteHref = href;
        $(".myRemove .dBtn").attr('href', href);

        $.getJSON(href + "/tickets/amount", function (response) {
            let delMes = $(".myRemove #delete-message");
            console.log("aslkdfjalksjdf;lasdjkf");
            delMes.empty();
            if (response > 0) {
                delMes.append(`На данной станции ${response} не завершенных билетов\nВсе билеты будут удалены!!!`);
            }
        });


        $('.myRemove #exampleModal').modal();
    }));

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

