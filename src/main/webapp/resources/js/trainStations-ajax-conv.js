$(document).ready(function () {


    $('.nBtn, .table .eBtn').click(function (e) {

        e.preventDefault();
        var href = $(this).attr('href');
        var id = $(this).attr('id');

        if (id === 'eBtn') {
            console.log("edit");
            $.getJSON(href, function (response) {
                $(".myForm #idTrain").val(response.train.id);
                $(".myForm #stationSerialNumber").val(response.stationSerialNumber);
                $(".myForm #station").append(`<option id="addedOption" selected value="${response.station.id}">${response.station.name}</option>`)
            });
            $('.myForm #exampleModal').modal();
        } else {
            console.log("new");
            $(".myForm :input[type='text']").each(function () {
                $(this).val('');
            });
            $('.myForm #stationSerialNumber').prop("disabled", false);
            $('.myForm #id').val('-1').hide();

            $('.myForm #exampleModal').modal();
        }
    });


    $('.myForm #edit-submit').click(function (event) {

        event.preventDefault();

        var href = $('.myForm #form').attr('action');
        let object = {
            id: $(".myForm #id").val(),
            train: {
                id: $(".myForm #idTrain").val()
            },
            station: {
                id: $(".myForm #station").val(),
            },
            stationSerialNumber: $(".myForm #stationSerialNumber").val()
        };


        var putMethod = 'PUT';
        var postMethod = 'POST';
        var method;
        if ($(".myForm #id").val() == -1) {
            method = postMethod;
        } else {
            href = href + "/" + $(".myForm #id").val();
            console.log(href);
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
        });
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
    });

    $('.myForm #exampleModal').on('hidden.bs.modal', function() {
        $('.myForm #errors').empty();
        console.log("remove");
        $('.myForm #addedOption').remove();
    });

});

