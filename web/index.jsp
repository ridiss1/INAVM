
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/styles_login.css" rel="stylesheet" type="text/css"/>
<!--        <link href="css/my_style.css" rel="stylesheet" type="text/css"/>-->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery-min.js" type="text/javascript"></script>
        <script src="js/jquery.js" type="text/javascript"></script>
        <title>Page de connexion </title>
    </head>
    <body >
        <!--login modal-->
        <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h1 class="text-center">Login</h1>
                    </div>
                    <div class="modal-body">
                        <form class="form col-md-12 center-block" action="accueil" method="post">
                            <div class="form-group">
                                
                                <input type="text" name="login" class="form-control input-lg" placeholder="Login">
                                <span class="erreur">${Message['login']}</span>
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control input-lg" placeholder="Password">
                                <span class="erreur">${Message['password']}</span>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary btn-lg btn-block">Sign In</button>
                                <span class="pull-right"><p>Don't have an account? <a href="registrationForm.jsp">Register</a></p></span><span><a href="#">Need help?</a></span>
<!--                                <p>Don't have an account, <a href="registrationForm.jsp">Sign Up</a></p>-->
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="col-md-12">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                        </div>	
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
