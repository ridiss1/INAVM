<%-- 
    Document   : vmProf
    Created on : Dec 13, 2015, 11:04:28 AM
    Author     : ridiss
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
                        <li><a href="accueilProf.jsp" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Accueil</span></a></li>

                        <li><a class="active" href="vmProf.jsp" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtual Machine</span></a></li>
                        
                        <li><a href="${pageContext.request.contextPath}/ListTemplates" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Template</span></a></li>
   
                        <li><a href="connexion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Deconnexion</span></a></li>
                    </ul>
                </nav>
                
            </div>
            
        </div>
        <!-- Main -->
        <div id="main">
            
            <section id="list_vm" class="vm">
                <div class="container">
                    <div class="navbar-header ">
                        <a class="navbar-brand active" href="#" ><h1>Gestion des VMs</h1></a>
                    </div>
                    <br/>
                    <br/>
                    <ul class="nav nav-tabs">
                        <li class="active" ><a href="vmProf.jsp">List VMs</a></li>
                        <li ><a href="${pageContext.request.contextPath}/FormCreatContenaire">Creation</a></li>                  
                    </ul>
                </div>
            </section>
            
            <section id="content">
                
                <div class="container">
                    <a href="#" class="list-group-item active">
                        Liste des machines virtuelles
                    </a>
                    
                    <table class="table table-hover">
                        
                        <thead>
                            <tr>
                                <th>VMid</th>
                                <th>Hostname</th>
                                <th>Address</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>

                    </table>                                                      
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

