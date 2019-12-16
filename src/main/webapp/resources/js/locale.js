$(document).ready(function () {

    $('header #lang-dropdown a').click(function (e) {

        e.preventDefault();

        let locale = $(this).attr('name');
        let arr = $(this).attr('href').split(' ');
        let href = arr[0];
        let queryString = arr[1];
        let index;

        if (queryString === 'null') {
            href = `${href}?lang=${locale}`;
        } else if ((index = queryString.indexOf('lang=')) === -1) {
            href = `${href}?${queryString}&lang=${locale}`;
        } else {
            queryString = queryString.slice(0, index);
            href = `${href}?${queryString}lang=${locale}`;
        }

        $(location).attr('href', href);

    });
});