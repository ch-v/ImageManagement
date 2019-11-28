<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- link
        href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
        rel="stylesheet" id="bootstrap-css"-->
    <!-- script
        src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script-->
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script
        src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <!-- script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script-->
</head>
<script type="text/javascript">
var msg='<%=session.getAttribute("authorized")%>';
console.log(msg);
if(msg === "false") {
	alert("Wrong Credentials");
<%     session.removeAttribute("authorized");
 %>
}

</script>
<body>
<div class = "container-fluid">
  <form class="form-group" action="Login" method="post">
      <h2>Login </h2>
      <label for="uname"><b>Username : </b></label>
      <input type="text"  class = "form-control" placeholder="Enter Username" name="username" required >
      <label for="psw"><b>Password : </b></label>
      <input type="password" class = "form-control" placeholder="Enter Password" name="password" required>
      <button type="submit" class = "btn btn-primary">Login</button>
  </form>
 </div>
</body>
</html>