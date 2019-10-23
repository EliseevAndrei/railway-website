<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"/>
</head>
<body>
<div class="container my-5">
    <h3>Update Train</h3>
    <div class="card">
        <div class="card-body">
            <div class="col-md-8">
                <form action="#" th:action="@{/trains/edit/{id}(id=${train.id}}" th:object="${train}" method="post">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <lable for="name" class="col-form-label">Название</lable>
                            <input type="text" th:field="*{name}" class="form-control" id="name" placeholder="Название">
                        </div>
                        <div class="form-group col-md-8">
                            <lable for="name" class="col-form-label">Кол-во мест купе</lable>
                            <input type="text" th:field="*{countKupe}" class="form-control" id="name" placeholder="Купе">
                        </div>
                        <div class="form-group col-md-8">
                            <lable for="name" class="col-form-label">Кол-во мест плацкарт</lable>
                            <input type="text" th:field="*{countPlaz}" class="form-control" id="name" placeholder="Плацкарт">
                        </div>
                        <div class="form-group col-md-8">
                            <lable for="name" class="col-form-label">Кол-во мест общих</lable>
                            <input type="text" th:field="*{countGen}" class="form-control" id="name" placeholder="Общее">
                        </div>
                        <div class="col-md-6">
                            <input type="submit" class="btn btn-primary" value="update"/>
                        </div>

                        <div class="form-group col-md-8"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>