<html>
    <head>
        <title>Title</title>
        <link href="/css/styles.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="post" action="/jpa/users">
            <label for="firstName">Name</label>
            <input type="text" id="firstName" name="firstName">

            <label for="lastName">LastName</label>
            <input type="text" id="lastName" name="lastName">

            <input type="submit" value="add"/>
        </form>


        <div class="form-style-2">
            <div class="form-style-2-heading">
                Already registered!
            </div>
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                    <th>cars</th>
                </tr>
                <tr th:each="user : ${usersFromServer}">
                    <td th:text="${user.firstName}"/>
                    <td th:text="${user.lastName}"/>
                    <td th:text="${user.age}"/>
                    <td th:each="car : ${user.cars}"
                        th:text="${car.model}"/>
                </tr>
            </table>
        </div>
    </body>
</html>