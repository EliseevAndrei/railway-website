$(document).ready(function () {

    /*$('#route-table').DataTable({
        "language": {
            "emptyTable": "Поездов на данном пути не найдено"
        }
    });*/

    $('#route-table .a-radio ').click(function (event) {
        event.preventDefault();

        let selectedTrHtml = $(this).parent();
        let depTrainRouteSerialNumber = selectedTrHtml.siblings("#startRoutePieceSerialNumber").text();
        let arrTrainRouteSerialNumber = selectedTrHtml.siblings("#endRoutePieceSerialNumber").text();

        let ticket = {
            train: {
                id: selectedTrHtml.siblings('#trainId').text()
            },
            depTime: selectedTrHtml.siblings('#depTime').text().replace("@", " "),
            arrTime: selectedTrHtml.siblings('#arrTime').text().replace("@", " "),
            trainDateId: selectedTrHtml.siblings("#trainDateId").text(),
            depTrainRoutePieceId: selectedTrHtml.siblings("#startRoutePieceId").text(),
            arrTrainRoutePieceId: selectedTrHtml.siblings("#endRoutePieceId").text()
        };

        let href = `/trains/list/${ticket.train.id}/carriages/onDate/${ticket.trainDateId}?depRoutePieceNumber=${depTrainRouteSerialNumber}&arrRoutePieceNumber=${arrTrainRouteSerialNumber}`;
        console.log(`href ${href}`);
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
                        `<td>${place.type}</td>` +
                        `<td><input type="radio"  \n` +
                        '     name="place"></td>' +
                        '</tr>';
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

            $('#carriage-card').append(htmlText);

        });


        $(".myForm #orderModal").modal();

        let errorsDomElement = $('.myForm #errors');

        $(".myForm #order-submit-btn").click(function (e) {
            e.preventDefault();

            errorsDomElement.empty();

            $.validator.addMethod('passPortNumberVal', function(value, element) {
               return this.optional(element) || /^[a-zA-Z]{2}[0-9]{7}$/.test(value);
            });

            let validator = $('.myForm #form').validate({
                 rules: {
                    name: {
                        required: true
                    },
                    surname: {
                        required: true
                    },
                    passportNumber: {
                        required: true,
                        passPortNumberVal: true
                    }
                },
                messages: {
                     name: "Имя обазательно",
                    surname: "Фамилия обязательна",
                    passportNumber: {
                         required: "Паспорт обязателен",
                        passPortNumberVal: "Неверный формат паспорта"
                    }
                },
                errorPlacement: function(error, element) {
                     $('.myForm #errors').append(error);
                    $('.myForm #errors').append("<br>");
                }
            });


            let checkedRadio = $('.myForm #carriage-card input[name="place"]:checked');
            console.log(checkedRadio);
            console.log(checkedRadio.length);
            if (checkedRadio.length === 0) {
                $('.myForm #errors').append('Место обязательно');
                $('.myForm #errors').append('<br>');
            }

            if (validator.form()) {

                ticket.surname = $('.myForm #surname').val();
                ticket.name = $('.myForm #name').val();
                ticket.passportNumber = $('.myForm #passportNumber').val();
                ticket.seatType = $(".myForm input[name='seatType']:checked").val();
                let checkedPlace = $('#carriage-card input[name="place"]:checked');
                ticket.place = {
                    id: checkedPlace.parent().siblings('#placeId').text()
                };
                ticket.carriage = {
                    id: checkedPlace.parent().siblings('#carriageId').text()
                };
                console.log(ticket);


                $.ajax({
                    url: "/tickets/list/order",
                    type: 'POST',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(ticket),
                    success: function (data, textStatus, xhr) {
                        $(location).attr('href', '/tickets/forUser');
                    },
                    error: function (xhr, textStatus, errorThrown) {

                        let errorBlock = $('.myForm #errors');
                        let response = JSON.parse(xhr.responseText);
                        /*response.errors.forEach(function (error) {
                            errorBlock.append("<p>" + error + "</p>");
                        });*/
                        errorBlock.append(`<p>Что-то пошло не так! Попробуйте обновить страницу!</p>`);
                        errorBlock.css("display", "block");
                    }
                })
            } else {
                $('.myForm #errors').css('display','block');
            }
        });
    });


    $(".myForm #orderModal").on('hide.bs.modal', function() {
       $('#carriage-card').children().first().remove();
    });

});

let getCarriagesForTrainByHref = function(href) {



};