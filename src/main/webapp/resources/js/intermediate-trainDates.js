$(document).ready(function() {

    $('.customDataTable').on('click', '.inter-show-btn', function (e) {
        e.preventDefault();

        let href = $(this).attr('href');

        $.getJSON(href, function (response) {

            let allTrForTableHtml = '';

            response.forEach(e => {
                allTrForTableHtml +=
                    '<tr>\n' +
                    `<td>${e.trainRoutePiece.startStation.name}</td>\n` +
                    `<td>${e.departureTime.replace("@", " ").slice(0, 16)}</td>\n` +
                    `<td>${e.trainRoutePiece.endStation.name}</td>\n` +
                    `<td>${e.arriveTime.replace("@", " ").slice(0, 16)}</td>\n` +
                    '</tr>';
            });

            let tableBodyDOM = $('.intermediate-dates .intermediate-table tbody');
            tableBodyDOM.empty();
            tableBodyDOM.append(allTrForTableHtml);

            $('#inter-dates').modal();
        });

    });

    let deleteHref;

    $('.customDataTable').on('click', '.del-btn', function(e) {

        e.preventDefault();
        let href = $(this).attr('href');
        deleteHref = href;
        $.getJSON(href + '/tickets/amount', function (response) {
            let delMes = $(".myRemove #delete-message");

            delMes.empty();
            if (response > 0) {
                delMes.append(`<p>На данной дате ${response} не завершенных билетов</p><p>Все билеты будут удалены!!!</p>`);
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
    })

});