<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"/>
</head>
<body>
    <div class="container my-2">
        <div class="card">
            <div class="card-body">
                <div th:switch="${trains}" class="container my-5">
                    <p class="my-5">
                        <a action="/trains/adding-form" class="btn brn-primary">
                            <i class="fas fa-user-plus ml-2">Add Train</i>
                        </a>
                    </p>
                    <div class="col-md-10">
                        <h2 th:case="null">Поездов нет!</h2>
                        <div th:case="*">
                            <table class="table table-striped table-responsive-md">
                                <thead>
                                    <tr>
                                        <th>Название</th>
                                        <th>Кол-во мест купе</th>
                                        <th>Кол-во мест плацкарт</th>
                                        <th>Кол-во мест общих</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr th:each="train : ${trains}">
                                        <td th:text="*{name}"></td>
                                        <td th:text="*{countKupe}"></td>
                                        <td th:text="*{countPlaz}"></td>
                                        <td th:text="*{countGen}"></td>
                                        <td>
                                            <a th:href="@{/trains/editing-form/{id}(id=${train.id})}" class="btn btn-primary">
                                                <i class="fas fa-user-edit ml-2"></i>
                                            </a>
                                        </td>
                                        <td>
                                            <a th:href="@{/trains/delete-form/{id}(id=${train.id})}" class="btn btn-primary">
                                                <i class="fas fa-user-times ml-2"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>