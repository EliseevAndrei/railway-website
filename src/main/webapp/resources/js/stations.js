$(document).ready(function () {

    $(".arriveTime").each(function() {
       $(this).mask("9999-99-99 99:99", {placeholder: "гггг-мм-дд чч:мм"});
    });
    $(".departureTime").each(function() {
        $(this).mask("9999-99-99 99:99", {placeholder: "гггг-мм-дд чч:мм"});
    });

});