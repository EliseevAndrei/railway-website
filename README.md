# Railway website
This is a website for the railway to buy train tickets.
### Project in progress
Now you can use admin features:

- CRUD operations for train. To do this use url `GET localhost:8080/trains` to get the page with trains.
- CRUD operations for stations of train. `GET localhost:8080/trains/list/{trainId}/stations`
- You can get dates of train. `GET locahost:8080/trains/list/1/dates`.(CRUD for dates now does not work)
- CRUD operations for all stations `GET localhost:8080/stations`

User features:

- User can search trains for trip and then buy tickets on these trains (Now does not work: using 'trash data')

## Technologies

- Spring MVC    
- Spring REST
- JPA with Hibernate implementation
- Embedded H2
- jUnit
- Thymeleaf
- Bootstrap 4
- jQuery
- AJAX

To access a web console for H2 get URL `localhost:8082`
* username: sa
* pass:

## Running notes

- If you run a project from idea you should set application context to `/`
- If you deploy a project manually in tomcat server, then you should add app files to ROOT folder
- If you have some troubles with logger encoding in your system, then just replace encoding in log4j.properties from UTF-8 to cp866 for console.
- To access web site write `localhost:8080`.
