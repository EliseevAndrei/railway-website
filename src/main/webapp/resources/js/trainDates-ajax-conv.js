$(document).ready(function () {

    let errorsDOMElement = $('#errors');

    $('#form #submit-btn').click(function (e) {
        e.preventDefault();

        errorsDOMElement.empty();

        $.validator.addMethod("dataTime", function (value, element) {
            return this.optional(element) || /^\d\d\d\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) (00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9])$/.test(value);
        }, "Формат даты неверн!");

        let validator = $("#form").validate({
            rules: {
                arriveTime: {
                    required: true,
                    dataTime: true
                },
                departureTime: {
                    required: true,
                    dataTime: true
                }
            }
        });

        let prev;
        let date1, date2;
        let errors = '';
        $('#form input[type=text]').each(function () {
            if ($(this).attr('name') === 'departureTime') {
                prev = $(this).parent().parent().prev().find('input[name="arriveTime"]').val();
            } else {
                prev = $(this).parent().parent().find('input[name="departureTime"]').val();
            }

            console.log($(this).val());
            console.log(prev);
            if (prev !== undefined) {
                date1 = prev.replace(' ', 'T');
                date2 = $(this).val().replace(' ', 'T');
                if (new Date(date1) >= new Date(date2)) {
                    errors += `<span>Дата ${prev} не может быть больше или равна ${$(this).val()}</span><br>`
                }
            }
        });
        if (errors.length !== 0) {
            errorsDOMElement.append(errors);
            errorsDOMElement.css("display", "block");
        } else {

            if (validator.form()) {
                let objects = [];
                let object = {};
                let trainStationId;
                let departureTime;
                let arriveTime;

                $('.object').each(function () {

                    trainStationId = $(this).children(".trainStationId").first().text();
                    departureTime = $(this).children(".td-departureTime").first().children(".departureTime").first().val();
                    console.log(departureTime);
                    arriveTime = $(this).children(".td-arriveTime").first().children(".arriveTime").first().val();
                    console.log(arriveTime);
                    object = {
                        trainStationId: trainStationId,
                        arriveTime: arriveTime,
                        departureTime: departureTime
                    };
                    console.log(object);
                    objects.push(object);
                });

                console.log(objects);
                let href = $("#form").attr('action');

                $.ajax({
                    url: href,
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(objects),
                    success: function (data, textStatus, xhr) {
                        $(location).attr('href', $('#form .reload').attr('href'));
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        let errorBlock = $('#form #errors');
                        let response = JSON.parse(xhr.responseText);
                        response.errors.forEach(function (error) {
                            errorBlock.append("<p>" + error + "</p>");
                            /*console.log(error);*/
                        });
                        errorBlock.css("display", "block");
                    }
                })
            }
        }
    });

});