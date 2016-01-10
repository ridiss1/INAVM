<%-- 
    Document   : vmStu
    Created on : 10 janv. 2016, 10:32:12
    Author     : Ridiss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Virtuel Machine</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.scrolly.min.js"></script>
        <script src="js/jquery.scrollzer.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <script src="js/Chart.js"></script>
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
                    <p>Student</p>
                </div>

                <!-- Nav -->
                <nav id="nav">

                    <ul>
                        <li><a href="accueilEtudiant.jsp" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Accueil</span></a></li>

                        <li><a class="active" href="${pageContext.request.contextPath}/Vmstudent" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtuel Machine</span></a></li>

                        <li><a href="${pageContext.request.contextPath}/DeconnxionServlet" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Deconnexion</span></a></li>
                    </ul>
                </nav>

            </div>

        </div>
        <!-- Main -->
        <div id="main">

            <section id="listvm" class="my_list_vm">
                <div class="container">
                    <a href="#" class="list-group-item active">
                        Vitual machine
                    </a>
                    <c:forEach items="${sessionScope.ListContainer}" var="container">
                        <a href="#${container.vmid}" class="list-group-item">${container.hostname}</a>
                    </c:forEach>


                </div>
            </section>
            <c:forEach items="${sessionScope.ListContainer}" var="container">

                <section id="content${container.vmid}" class="backvm">

                    <div  class="container" id ="${container.vmid}">
                        <nav class="navbar navbar-default" role="navigation">
                            <div class="navbar-header">
                                <a class="btn btn-primary" role="button">${container.hostname}  : ${container.status}</a>                           </div>

                            <div id="alignright" class="navbar-right">

                                <a class="btn btn-success" href="${pageContext.request.contextPath}/Vmstudent?start=${container.vmid}" role="button"><span class="icon fa-play">&nbsp;&nbsp;Start</span></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <a class="btn btn-danger" href="${pageContext.request.contextPath}/Vmstudent?stop=${container.vmid}" role="button"><span class="icon fa-power-off">&nbsp;&nbsp;Stop</span></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <a class="btn btn-info " target="_blank" href="${container.console}" role="button"><span class="icon fa-desktop">&nbsp;&nbsp;Console</span></a>

                            </div>

                        </nav>
                    </div>



                    <div  class="container">

                        <table class="table table-bordered">

                            <thead>
                                <tr>
                                    <th>Hostname</th>
                                    <th>${container.hostname}</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Vmid</td>
                                    <td>${container.vmid}</td>
                                </tr>
                                <tr>
                                    <td>Address</td>
                                    <td>${container.ip_address}</td>
                                </tr>




                            </tbody>
                        </table>

                    </div>

                </section>

                

            </c:forEach>



        </div>




        <!-- Footer -->
        <div id="footer">

            <!-- Copyright -->
            <ul class="copyright">
                <li>&copy; Insa Toulouse. All rights reserved.</li>
            </ul>

        </div>
        <script type="text/javascript" src="js/graphe.js"></script>

    </body>

</html>
