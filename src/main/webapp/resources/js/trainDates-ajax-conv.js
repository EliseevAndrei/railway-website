
$(document).ready(function() {

    $('#form #submit-btn').click(function(e) {
        e.preventDefault();

        let objects = [];
        let object = {};
        let trainStationId;
        let departureTime;
        let arriveTime;

        $('.object').each(function() {

            trainStationId = $(this).children(".trainStationId").first().text();
            departureTime = $(this).children(".td-arriveTime").first().children(".arriveTime").first().val();
            console.log(departureTime);
            arriveTime = $(this).children(".td-departureTime").first().children(".departureTime").first().val();
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
        alert("dakfj");
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
                let errorBlock =  $('#form #errors');
                let response = JSON.parse(xhr.responseText);
                response.errors.forEach(function(error) {
                    errorBlock.append("<p>"+ error +"</p>");
                    /*console.log(error);*/
                });
                errorBlock.css("display", "block");
            }
        })
    });

});