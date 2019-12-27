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
                console.log(stationsOptionsHtml);
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
            '                                    <select id="startStation" class="form-control mb-2 mr-sm-2" required>\n' +
            `                        <option selected value="${previousStationDOMElement.val()}">${previousStationDOMElement.text()}</option>\n` +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                                <td class="endStation">\n' +
            '                                    <select id="endStation" class="form-control mb-2 mr-sm-2" required>\n' +
            `${stationsOptionsHtml}` +
            '                                    </select>\n' +
            '                                </td>\n' +
            '\n' +
            '                                <td class="distance">\n' +
            '                                    <input type="number" required min="0" class="form-control mb-2 mr-sm-2" id="distance" name="distance">\n' +
            '                                </td>\n' +
            '\n' +
            '                            </tr>')
    });


    $(".train-create-form #add-carriage-btn").click(e => {

        e.preventDefault();
        $('.train-create-form #carriage-table').append(
            '<tr>\n' +
            '                                                    <td class="carriageType">\n' +
            '                                                        <select style="width: 90px" class="form-control mb-2 mr-sm-2">\n' +
            '                                                            <option value="Купе">Купе</option>\n' +
            '                                                            <option value="Плацкарт">Плацкарт</option>\n' +
            '                                                            <option value="Общий">Общий</option>\n' +
            '                                                        </select>\n' +
            '                                                    </td>\n' +
            '                                                    <td class="carriageAmount">\n' +
            '                                                        <input type="number" required min="0" style="width: 70px" class="form-control mb-2 mr-sm-2" name="carriageAmount">\n' +
            '                                                    </td>\n' +
            '                                                    <td>\n' +
            '                                                        <ul class="placeType">\n' +
            '                                                            <li>\n' +
            '                                                                <select style="width: 100px" class="form-control mb-2 mr-sm-2">\n' +
            '                                                                    <option value="Верхнее">Верхнее</option>\n' +
            '                                                                    <option value="Нижнее">Нижнее</option>\n' +
            '                                                                    <option value="Сидячее">Сидячее</option>\n' +
            '                                                                    <option value="Купе">Купе</option>\n' +
            '                                                                </select>\n' +
            '                                                            </li>\n' +
            '                                                        </ul>\n' +
            '                                                    </td>\n' +
            '                                                    <td>\n' +
            '                                                        <ul class="amountLeftBorder">\n' +
            '                                                            <li>\n' +
            '                                                                <input type="number" required min="0" style="width: 70px" class="form-control mb-2 mr-sm-2" name="amountLeftBorder">\n' +
            '                                                            </li>\n' +
            '                                                        </ul>\n' +
            '                                                    </td>\n' +
            '                                                    <td>\n' +
            '                                                        <ul class="amountRightBorder">\n' +
            '                                                            <li>\n' +
            '                                                                <input type="number" required min="0" style="width: 70px" class="form-control mb-2 mr-sm-2" name="amountRightBorder">\n' +
            '                                                            </li>\n' +
            '                                                        </ul>\n' +
            '                                                    </td>\n' +
            '                                                    <td>\n' +
            '                                                        <ul class="placesParity">\n' +
            '                                                            <li>\n' +
            '                                                                <select class="form-control mb-2 mr-sm-2">\n' +
            '                                                                    <option value="Все">Все</option>\n' +
            '                                                                    <option value="Четные">Четные</option>\n' +
            '                                                                    <option value="Нечетные">Нечетные</option>\n' +
            '                                                                </select>\n' +
            '                                                            </li>\n' +
            '                                                        </ul>\n' +
            '                                                    </td>\n' +
            '                                                    <td>\n' +
            '                                                        <a href="#" name="add-place" class="fas fa-plus"></a>\n' +
            '                                                    </td>\n' +
            '                                                </tr>'
        );

    });

    $('.train-create-form #carriage-table').on('click', 'a[name="add-place"]',function (e) {
        e.preventDefault();

        let parent = $(this).parent().parent();

        parent.find('.placeType').append(
            '                                                           <li>\n' +
            '                                                                <select style="width: 100px" class="form-control mb-2 mr-sm-2">\n' +
            '                                                                    <option value="Верхнее">Верхнее</option>\n' +
            '                                                                    <option value="Нижнее">Нижнее</option>\n' +
            '                                                                    <option value="Сидячее">Сидячее</option>\n' +
            '                                                                    <option value="Купе">Купе</option>\n' +
            '                                                                </select>\n' +
            '                                                            </li>'
        );

        parent.find('.amountLeftBorder').append(
            '                                                            <li>\n' +
            '                                                                <input type="number" name="amountLeftBorder" required min="0" style="width: 70px" class="form-control mb-2 mr-sm-2">\n' +
            '                                                            </li>'
        );

        parent.find('.amountRightBorder').append(
            '                                                           <li>\n' +
            '                                                                <input type="number" name="amountRightBorder" required min="0" style="width: 70px" class="form-control mb-2 mr-sm-2">\n' +
            '                                                            </li>'
        );

        parent.find('.placesParity').append(
            '                                                           <li>\n' +
            '                                                                <select class="form-control mb-2 mr-sm-2">\n' +
            '                                                                    <option value="Все">Все</option>\n' +
            '                                                                    <option value="Четные">Четные</option>\n' +
            '                                                                    <option value="Нечетные">Нечетные</option>\n' +
            '                                                                </select>\n' +
            '                                                            </li>'
        );

    });


    let errorsDomElement = $('.train-create-form #errors');

    $(".train-create-form #train-create-submit").click(function (e) {
        e.preventDefault();

        errorsDomElement.empty();

        let validator = $('#train-create-form').validate({
            rules: {
                name: {
                    required: true
                },
                distance: {
                    required: true,
                    min: 0
                },
                carriageAmount: {
                    required: true
                },
                amountLeftBorder: {
                    required: true,
                    min: 0
                },
                amountRightBorder: {
                    required: true,
                    min: 0
                }
            },
            messages: {
                name: "Название поезда обязательно!",
                distance: "Дистанция между станциями обязательна!",
                carriageAmount: "Количество поездов обязательно!",
                amountLeftBorder: "Левая граница промежутка мест обязательна!",
                amountRightBorder: "Правая граница промежутка мест обязательна!"
            },
            errorPlacement: function(error, element) {
                $('.train-create-form #errors').append(error);
                $('.train-create-form #errors').append("<br>");
            }
        });

        if (validator.form()) {
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
            train.carriages = createCarriagesWithPlaces();

            console.log(train);

            $.ajax({
                url: '/trains/list/full',
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
        } else {
            $('.train-create-form #errors').css('display', 'block');
        }

    });

    let deleteHref;

    $('.rBtn').click(function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        deleteHref = href;
        $(".myRemove .dBtn").attr('href', href);

        $.getJSON(href + "/tickets/amount", function (response) {
            let delMes = $("#delete-message");
            delMes.empty();
            if (response > 0) {
                delMes.append(`<p>На данный поезд куплено ${response} не завершенных билетов</p><p>Все билеты будут удалены!!!</p>`);
            }
        });

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

    $('.getCarriagesBtn').click(function(e) {
        e.preventDefault();
        let href =  $(this).attr('href');
        getAllCarriages(href);
    })
});

let createCarriagesWithPlaces = function() {

    let carriage, place;
    let carriages = [], places;
    let carriageIndex = 0;
    let carriageAmount = 0;
    let carriageType = '';
    let parent;
    let placesType = [], leftBorderAmount = [], rightBorderAmount = [], placesParity = [];
    $('.carriageType').each(function() {

        placesType = [];
        leftBorderAmount = [];
        rightBorderAmount = [];
        placesParity = [];

        carriageType = $(this).find('option:selected').val();
        carriageAmount = $(this).siblings('.carriageAmount').children('input').val();

        console.log(`carriageAmount = ${carriageAmount}`);

        parent = $(this).parent();

        console.log(parent);

        parent.find('.placeType li').each(function() {
            placesType.push($(this).find('option:selected').val());
        });

        parent.find('.amountLeftBorder li input').each(function() {
            leftBorderAmount.push($(this).val());
        });

        parent.find('.amountRightBorder li input').each(function() {
            rightBorderAmount.push($(this).val());
        });

        parent.find('.placesParity li').each(function() {
            placesParity.push($(this).find('option:selected').val());
        });

        console.log(`placesType = ${placesType}\nleftBorderAmount=${leftBorderAmount}\nrightBorder=${rightBorderAmount}\nplacesParity=${placesParity}`);

        let leftBorder;
        let rightBorder;
        let offset = 0;
        console.log(places);
        places = [];
        console.log(places);
        for (let i = 0; i < placesType.length; i++) {
            leftBorder = leftBorderAmount[i];
            rightBorder = rightBorderAmount[i];
            if (placesParity[i] === "Все") {
                offset = 1;
            } else {
                if (placesParity[i] === "Четные") {
                    if (leftBorder[i] % 2 !== 0) {
                        leftBorder++;
                    }
                } else {
                    if (leftBorder[i] % 2 === 0) {
                        leftBorder++;
                    }
                }
                offset = 2;
            }

            for (let j = Number(leftBorder); j <= rightBorder; j += offset) {
                place = {
                    type: placesType[i],
                    number: j
                };
                places.push(place);
            }
        }
        console.log(places);

        for (let i = 0; i < carriageAmount; i++) {

            carriage = {
                number: ++carriageIndex,
                type: carriageType,
                places: places
            };
            carriages.push(carriage);
        }


    });
    console.log(carriages);
    return carriages;
};

let getAllCarriages = function (href) {
    console.log(href);
    $.getJSON(href, function (response) {
        let carriages = response;
        let collapseName = '', trWithPlacesInfo;
        let htmlText = '<div class="accordion md-accordion" id="accordionEx1" role="tablist" aria-multiselectable="true">\n';
        carriages.forEach((e, index) => {
            collapseName = `Номер - ${e.number}, Тип - ${e.type}, Колв-во мест - ${e.places.length}`;
            trWithPlacesInfo = '';
            e.places.forEach((place,placeIndex) => {
                trWithPlacesInfo += '<tr>' +
                    `<td hidden id ="placeId">${place.id}</td>` +
                    `<td hidden id ="carriageId">${e.id}</td>` +
                    `<td>${place.number}</td>` +
                    `<td>${place.type}</td>`;
            });


            htmlText += '<!-- Accordion card -->\n' +
                '                                            <div class="card">\n' +
                '                                                <!-- Card header -->\n' +
                `<div class="card-header" role="tab" id="heading${index}">\n` +
                `<a class="collapsed" data-toggle="collapse" data-parent="#accordionEx1" href="#collapse${index}" aria-expanded="false" aria-controls="collapse${index}">\n` +
                '                                                        <h5 class="mb-0">\n' +
                `${collapseName}` +
                '<i style="float: right;" class="fas fa-angle-down rotate-icon"></i>\n' +
                '                                                        </h5>\n' +
                '                                                    </a>\n' +
                '                                                </div>\n' +
                '                                                <!-- Card body -->\n' +
                `<div id="collapse${index}" class="collapse" role="tabpanel" aria-labelledby="heading${index}" data-parent="#accordionEx1">` +
                '                                                    <div class="card-body">\n' +
                '                                                        <table id="places-table">\n' +
                '                                                            <tr>\n' +
                '                                                                <th>Номер</th>\n' +
                '                                                                <th>Тип</th>\n' +
                '                                                                <th></th>\n' +
                '                                                            </tr>\n' +
                `${trWithPlacesInfo}` +
                '                                                        </table>\n' +
                '                                                    </div>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                            <!-- Accordion card -->';
        });
        htmlText += '</div>';

        $('.train-carriages #carriage-card').empty();
        $('.train-carriages #carriage-card').append(htmlText);

        $('#train-carriages').modal();
    });
};

