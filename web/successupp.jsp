<%-- 
    Document   : successupp
    Created on : 9 janv. 2016, 16:43:48
    Author     : Ridiss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <title>Suppression VM</title>
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.scrolly.min.js"></script>
        <script src="js/jquery.scrollzer.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
        <link href="css/my_style.css" rel="stylesheet" type="text/css"/>

        </noscript>
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
        <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
    </head>
    <body>
        <!-- Header -->
        <div id="header" class="skel-layers-fixed">

            <div class="top">

                <!-- Logo -->
                <div id="logo">
                    <span class="image avatar48"><img src="images/avatar.jpg" alt="" /></span>
                    <h1 id="title">${sessionScope.sessionUser} </h1>
                    <p>Professor</p>
                </div>

                <!-- Nav -->
                <nav id="nav">

                    <ul>
                        <li><a href="accueilProf.jsp" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Home</span></a></li>

                        <li><a class="active" href="${pageContext.request.contextPath}/InfoVms" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtual Machine</span></a></li>
                        
                        <li><a href="${pageContext.request.contextPath}/ListTemplates" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Template</span></a></li>

                        <li><a href="${pageContext.request.contextPath}/DeconnxionServlet" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Log out</span></a></li>
                    </ul>
                </nav>

            </div>

        </div>
        
        <!-- Main -->
        <div id="main">
            
            <div align="center">
                <p style="color:green"> <i class="fa fa-check"></i> Your VM has been deleted succesfully !!</p>
            </div>
                       
            <div align="center">
              <a href="${pageContext.request.contextPath}/InfoVms"><button type="button" class="btn btn-primary btn-lg">Manage the VM</button></a>              
            </div>
            
        </div>
        <hr>
        <!-- Footer -->
        <div id="footer">

            <!-- Copyright -->
            <ul class="copyright">
                <li>&copy; INAVM. All rights reserved.</li>
            </ul>

        </div>
    </body>
</html>

