$(document).ready(function () {

    let timeZone;
    $('.nBtn, .table .eBtn').click(function (e) {

        e.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        console.log(href);

        if (text === 'Edit') {
            $.getJSON(href, function (response) {
                $(".myForm :input[type='text'], .myForm :input[type='number'], .myForm select").each(function () {
                    console.log($(this).attr('id'));
                    $(this).val(response[$(this).attr('id')]);
                });
                $(".myForm #arriveTime, .myForm #departureTime").each(function () {
                    let str = response[$(this).attr('id')].replace("@", " ");
                    str = str.slice(0, 16);
                    console.log(str);
                    $(this).val(str);
                });
            });
            $('.myForm #exampleModal').modal();
        } else {

            $(".myForm :input[type='text']").each(function () {
                console.log($(this).attr('id'));
                $(this).val('');
            });
            $('.myForm #id').val('-1').hide();

            $('.myForm #exampleModal').modal();
        }
    });

    $('.myForm #edit-submit').click(function (event) {

        event.preventDefault();

        var href = $('.myForm #form').attr('action');
        var object = {};

        $(".myForm :input[type='text'], .myForm :input[type='number'], .myForm select").each(function () {
            console.log($(this).val());
            object[$(this).attr('id')] = $(this).val();
        });

        $(".myForm #arriveTime, .myForm #departureTime").each(function () {
            console.log($(this).val());
            let str = $(this).val().replace(/\s+/g, '');
            console.log(str);
            let date = str.substring(0, 10);
            console.log(date);
            let time = str.substring(10, 16);
            console.log(time);
            str = date + "@" + time;
            object[$(this).attr('id')] = str;
        });
        console.log(JSON.stringify(object));
        var putMethod = 'PUT';
        var postMethod = 'POST';
        var method;
        if (object.id == -1) {
            method = postMethod;
        } else {
            href = href + "/" +object.id;
            method = putMethod;
        }
        console.log(href);
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
                alert("Error with status" + textStatus + errorThrown);
            }
        })
    });

    let deleteHref;

    $('.rBtn').click(function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        deleteHref = href;
        console.log(href);
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
