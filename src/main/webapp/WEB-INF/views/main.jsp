<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<form class="form-inline" method="get" action="/trains/available/onRoute">
    <div class="form-group">
        <label for="dep_station" class="mr-sm-2">Departure station:</label>
        <input type="text" class="form-control mb-2 mr-sm-2" id="dep_station" name="dep_station"/>
    </div>
    <div class="form-group">
        <label for="arr_station" class="mr-sm-2">Arrive station</label>
        <input type="text" class="form-control mb-2 mr-sm-2" id="arr_station" name="arr_station"/>
    </div>
    <div class="form-group">
        <label for="dep_date" class="mr-sm-2">Date</label>
        <input type="date" class="form-control mb-2 mr-sm-2" id="dep_date" name="dep_date"/>
    </div>
    <button type="submit" class="btn btn-primary mb-2">Submit</button>
</form>

<%--jQuery library --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%--Popper JS--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<%-- Latest compiled JavaScript --%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
