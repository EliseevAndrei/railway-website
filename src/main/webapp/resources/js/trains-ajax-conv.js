$(document).ready(function () {

    let train = {};
    let stationsOptionsHtml = "";

    $('.nBtn, .table .eBtn').click(function (e) {

        e.preventDefault();
        var href = $(this).attr('href');
        var id = $(this).attr('id');

        if (id === 'eBtn') {

            $.getJSON(href, function (response) {

                $(".train-edit-form :input[type='text'], .train-edit-form :input[type='number'], .train-edit-form select").each(function () {

                    $(this).val(response[$(this).attr('id')]);

                });
                /*$(".myForm #arriveTime, .myForm #departureTime").each(function () {
                    let str = response[$(this).attr('id')].replace("@", " ");
                    str = str.slice(0, 16);

                    $(this).val(str);
                });*/
            });
            $('.train-edit-form #exampleModal').modal();
        } else {
            console.log("new");
            $(".train-create-form :input[type='text']").each(function () {
                $(this).val('');
            });
            $('.train-create-form #id').val('-1').hide();

            $('.train-create-form #trainModal').modal();

            /*$.ajaxSetup({
                async: false
            });*/
            $.getJSON('/stations/list', function (response) {
                response.forEach(function (station) {
                    stationsOptionsHtml += `<option value="${station.id}">${station.name}</option>`
                });
                $("#stations-table #startStation").append(stationsOptionsHtml);
                $("#stations-table #endStation").append(stationsOptionsHtml);
            });
            /*$.ajaxSetup({
                async: true
            });*/

        }
    });

    $('.myForm #edit-submit, .myForm #close-btn, .myForm #x-btn').click(function () {
        $('.myForm #errors').empty();
    });


    $('#routeFormModal').on('hide.bs.modal', function () {
    });

    $('.train-edit-form #edit-submit').click(function (event) {

        event.preventDefault();

        let href = $('.train-edit-form #form').attr('action');
        let object = {};

        $(".train-edit-form :input[type='text'], .train-edit-form :input[type='number'], .train-edit-form select").each(function () {
            object[$(this).attr('id')] = $(this).val();
        });


        if (object.id == -1) {
            train = object;

        } else {
            href = href + object.id;

            $.ajax({
                url: href,
                type: 'PUT',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(object),
                success: function (data, textStatus, xhr) {
                    $(location).attr('href', '/trains');
                },
                error: function (xhr, textStatus, errorThrown) {
                    let errorBlock = $('.train-edit-form #errors');
                    console.log(textStatus);
                    console.log(errorThrown);
                    console.log(xhr.responseText);
                    $('.train-edit-form #errors').append(xhr.responseText);
                    let response = JSON.parse(xhr.responseText);
                    response.errors.forEach(function (error) {
                        errorBlock.append("<p>" + error + "</p>");
                    });
                    errorBlock.css("display", "block");
                }
            })
        }

    });

    let routePieceSerialNumber = 1;
    $(".train-create-form #add-station-btn").click(function (e) {
        e.preventDefault();

        routePieceSerialNumber += 1;
        let previousStationDOMElement = $("#stations-table tr:last-child #endStation option:selected");
        $('#stations-table').append('<tr>\n' +
            `<td class="serialNumber">${routePieceSerialNumber}</td>\n` +
            '                                <td class="startStation">\n' +
            '                                    <select id="startStation" required>\n' +
            `                        <option selected value="${previousStationDOMElement.val()}">${previousStationDOMElement.text()}</option>\n` +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                                <td class="endStation">\n' +
            '                                    <select id="endStation" required>\n' +
            `${stationsOptionsHtml}` +
            '                                    </select>\n' +
            '                                </td>\n' +
            '\n' +
            '                                <td class="distance">\n' +
            '                                    <input type="number" id="distance">\n' +
            '                                </td>\n' +
            '\n' +
            '                            </tr>')
    });

    $(".train-create-form #train-create-submit").click(function (e) {
        e.preventDefault();
        let routePieces = [];
        let routePiece = {};

        $('#stations-table tr').each(function () {
            if ($(this).attr('class') !== 'table-head') {
                routePiece = {
                    serialNumber: $(this).children(".serialNumber").text(),
                    startStation: {
                        id: $(this).children('.startStation').children('#startStation').val()
                    },
                    endStation: {
                        id: $(this).children('.endStation').children('#endStation').val()
                    },
                    distance: $(this).children('.distance').children('#distance').val()
                };
                console.log(routePiece);
                routePieces.push(routePiece);
            }
        });
        train.trainRoutePieceList = routePieces;
        train.name = $('#train-card #name').val();
        $.ajax({
            url: '/trains/list',
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(train),
            success: function (data, textStatus, xhr) {
                $(location).attr('href', "/trains");
            },
            error: function (xhr, textStatus, errorThrown) {
                let errorBlock = $('.myForm #errors');
                let response = JSON.parse(xhr.responseText);
                response.errors.forEach(function (error) {
                    errorBlock.append("<p>" + error + "</p>");
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

