$(document).ready(function () {

    let rolesSelectDOM = '<li><select class="form-control col-md-6 selectRole" style="float: left;">';

    $.getJSON('roles/list', function (response) {

        response.forEach(e => {
            rolesSelectDOM += `<option value=${e.id}>${e.name}</option>`
        });
        rolesSelectDOM += '</select><a class="delete-role-btn ml-3" style="color: red;"><i class="fas fa-times"></i></a></li>';
    });

    $('.nBtn, .table .eBtn').click(function (e) {

        e.preventDefault();
        let href = $(this).attr('href');
        let id = $(this).attr('id');


        if (id === 'eBtn') {

            $.getJSON(href, function (response) {

                $(".myForm :input[type='text'], .myForm :input[type='number']").each(function () {
                    $(this).val(response[$(this).attr('id')]);
                });
                let rolesHtml = '';
                response.roles.forEach(function (e) {
                   rolesHtml += `<li><span id="${e.id}">${e.name}</span>      <a class="delete-role-btn ml-3" style="color: red;"><i class="fas fa-times"></i></a></li>`
                });
                $('#roles').append(rolesHtml);
            });

            $('.myForm #exampleModal').modal();
        } else {
            console.log("new");
            $(".myForm :input[type='text']").each(function () {
                $(this).val('');
            });
            $('.myForm #id').val('-1').hide();

            $('.myForm #exampleModal').modal();
        }
    });

    $('.myForm #edit-submit, .myForm #close-btn, .myForm #x-btn').click(function() {
        $('.myForm #errors').empty();
    });

    let errorsDomElement = $('.myForm #errors');

    $('.myForm #edit-submit').click(function (event) {

        event.preventDefault();

        errorsDomElement.empty();

        let validator = $('#form').validate({
            rules: {
                name: {
                    required: true
                },
                surname: {
                    required: true,
                },
                email: {
                    required: true,
                    email: true
                },
                login: {
                    required: true
                },
                pass: {
                    required: true
                }
            },
            messages: {
                name: "Имя обязательно!",
                surname: "Фамилия обязательна!",
                email: "Количество поездов обязательно!",
                login: "Логин обязателен!",
                pass: "Пароль обязателен!"
            },
            errorPlacement: function(error, element) {
                $('#form #errors').append(error);
                $('#form #errors').append("<br>");
            }
        });

        if (validator.form()) {

            let href = $('.myForm #form').attr('action');
            let object = {};

            $(".myForm :input[type='text'], .myForm :input[type='number']").each(function () {
                object[$(this).attr('id')] = $(this).val();
            });

            let roles = [];
            let role;
            $('.myForm #roles li').each(function () {

                $(this).children('span').each(function () {
                    role = {
                        id: $(this).attr('id'),
                        name: $(this).text()
                    };
                    roles.push(role);
                });

                $(this).find('select option:selected').each(function () {
                    role = {
                        id: $(this).val(),
                        name: $(this).text()
                    };
                    roles.push(role);
                    console.log(role);
                })
            });

            object.roles = roles;
            console.log(object);

            let putMethod = 'PUT';
            let postMethod = 'POST';
            let method;
            if (object.id == -1) {
                method = postMethod;
            } else {
                href = href + "/" + object.id;
                method = putMethod;
            }
            $.ajax({
                url: href,
                type: method,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(object),
                success: function (data, textStatus, xhr) {
                    $(location).attr('href', $('.container #reloadBtn').attr('href'));
                },
                error: function (xhr, textStatus, errorThrown) {
                    let errorBlock = $('.myForm #errors');
                    let response = JSON.parse(xhr.responseText);
                    response.errors.forEach(function (error) {
                        errorBlock.append("<p>" + error + "</p>");
                        /*console.log(error);*/
                    });
                    errorBlock.css("display", "block");
                }
            });
        } else {
            $('#form #errors').css('display', 'block');
        }
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
               /* alert("Error with status" + textStatus + errorThrown);*/
            }
        })
    });

    $('.myForm #roles').on('click', ".delete-role-btn", function(e) {
        e.preventDefault();
        $(this).parent().remove();
    });

    $('.myForm .add-role-btn').click(function(e) {
        e.preventDefault();

        $('.myForm #roles').append(rolesSelectDOM);
    });

});

