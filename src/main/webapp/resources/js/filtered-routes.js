$(document).ready(function () {

    $('#route-table').DataTable({
        "language": {
            "emptyTable": "Поездов на данном пути не найдено"
        }
    });

    $('#route-table .a-radio ').click(function (event) {
        event.preventDefault();

        let selectedTrHtml = $(this).parent();
        let ticket = {
            train: {
                id: selectedTrHtml.siblings('#trainId').text()
            },
            depTime: selectedTrHtml.siblings('#depTime').text().replace("@", " "),
            arrTime: selectedTrHtml.siblings('#arrTime').text().replace("@", " "),
            trainDate: {
                id: selectedTrHtml.siblings("#trainDateId").text()
            },
            depTrainRoutePiece: {
                id: selectedTrHtml.siblings("#startRoutePieceId").text(),
                serialNumber: selectedTrHtml.siblings("#startRoutePieceSerialNumber").text()
            },
            arrTrainRoutePiece:{
                id: selectedTrHtml.siblings("#endRoutePieceId").text(),
                serialNumber: selectedTrHtml.siblings("#endRoutePieceSerialNumber").text()
            }
            /*depStation: {
                id: $('.container #dep_station_id').text()
            },
            arrStation: {
                id: $('.container #arr_station_id').text()
            }*/
        };
        /*let orderDTO = {
            trainDateId: selectedTrHtml.siblings("#trainDateId").text(),
            startRoutePieceId: selectedTrHtml.siblings("#startRoutePieceId").text(),
            endRoutePieceId: selectedTrHtml.siblings("#endRoutePieceId").text(),
            startRoutePieceSerialNumber: selectedTrHtml.siblings("#startRoutePieceSerialNumber").text(),
            endRoutePieceSerialNumber: selectedTrHtml.siblings("#endRoutePieceSerialNumber").text(),
            ticket: ticket
        };*/

        let href = `/trains/list/${ticket.train.id}/carriages/onDate/${ticket.trainDate.id}?depRoutePieceNumber=${ticket.depTrainRoutePiece.id}&arrRoutePieceNumber=${ticket.arrTrainRoutePiece.id}`;
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
                        `<td><input type="radio" \n` +
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


        console.log(ticket);
        $(".myForm #orderModal").modal();

        $(".myForm #order-submit-btn").click(function (e) {
            e.preventDefault();

            ticket.surname = $('.myForm #surname').val();
            ticket.name = $('.myForm #name').val();
            ticket.passportNumber = $('.myForm #passportNumber').val();
            ticket.seatType = $(".myForm input[name='seatType']:checked").val();
            let checkedPlace = $('#carriage-card input[name="place"]:checked');
            ticket.place = {
                id: checkedPlace.parent().siblings('#placeId').text()
            };
            ticket.carriage = {
                id:checkedPlace.parent().siblings('#carriageId').text()
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
                    alert("error");
                    let errorBlock = $('.myForm #errors');
                    let response = JSON.parse(xhr.responseText);
                    response.errors.forEach(function (error) {
                        errorBlock.append("<p>" + error + "</p>");
                    });
                    errorBlock.css("display", "block");
                }
            })

        });
    });


    $(".myForm #orderModal").on('hide.bs.modal', function() {
       $('#carriage-card').children().first().remove();
    });

});