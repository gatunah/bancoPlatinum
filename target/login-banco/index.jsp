<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    

</head>
<body>
    <h1>Banco Platinum</h1>
    <h2>Login</h2>

    <form action="auth" method="post">
        <label for="username">Usuario:</label><br>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Contraseña:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" id="loginbtn" value="Login">
    </form>
</body>
</html>
