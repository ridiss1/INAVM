<%-- 
    Document   : accueilEtudiant
    Created on : 10 janv. 2016, 09:35:19
    Author     : Ridiss
--%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title>
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
        <link href="css/my_style.css" rel="stylesheet" type="text/css"/>
        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
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
                    <h1 id="title">${sessionScope.sessionUser}</h1>
                    <p>Etudiant</p>
                </div>
                    
                <!-- Nav -->
                <nav id="nav">
                    
                    <ul>
                        <li><a class="active" href="accueil" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Home</span></a></li>
                            
                        <li><a href="${pageContext.request.contextPath}/Vmstudent" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtal Machine</span></a></li>
                            
                        <li><a href="${pageContext.request.contextPath}/DeconnxionServlet" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Logout></li>
                                    </ul>
                                    </nav>
                    
                                    </div>
                
                                    </div>
                                    <!-- Main -->
                                    <div id="main">
                                        <!-- Intro -->
                                        <section id="top" class="one dark cover">
                                            <div class="container" >
                    
                                                <header >
                                                    <!-- Logo -->
                                                    <div align="center">
                                                        <h1><a href="accueil">CLE</a></h1>
                                                        <p > INVAM Vm provider</p>
                                                        <a href="${pageContext.request.contextPath}/Vmstudent" class="button scrolly">Virtuel Machine</a>
                                                    </div>
                                                </header>
                        
                        
                        
                                            </div>
                                        </section>
                                    </div>
            
                                    <!-- Footer -->
                                    <div id="footer">
            
                                        <!-- Copyright -->
                                        <ul class="copyright">
                                            <li>&copy; INAVM. All rights reserved.</li>
                                        </ul>
                
                                    </div>
            
                                    </body>
        
                                    </html>