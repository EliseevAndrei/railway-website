let getLocale = function() {

    let i18nRussian = '//cdn.datatables.net/plug-ins/1.10.20/i18n/Russian.json';
    let i18nEnglish = '//cdn.datatables.net/plug-ins/1.10.20/i18n/English.json';

    let localeDOM = $('#locale').text();
    let locale;
    if (localeDOM === undefined) {
        locale = i18nRussian;
    } else if (localeDOM === 'ru_RU'){
        locale = i18nRussian;
    } else if (localeDOM === 'en_US') {
        locale = i18nEnglish;
    }
    console.log(locale);
    console.log(localeDOM);

    return locale;
};



$(document).ready(function () {


    $('.customDataTable').DataTable({
        "language": {
            "url": getLocale()
        }
    });

});