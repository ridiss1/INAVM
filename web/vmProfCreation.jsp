<%-- 
    Document   : vmProf
    Created on : Dec 13 2015, 11:04:28 AM
    Author     : ridiss
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Virtual Machine</title>
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
                        <li><a href="accueilProf.jsp" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Home</span></a></li>

                        <li><a class="active" href="${pageContext.request.contextPath}/InfoVms" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtual Machine</span></a></li>
                        
                        <li><a href="${pageContext.request.contextPath}/ListTemplates" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Template</span></a></li>

                        <li><a href="connexion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Log out</span></a></li>
                    </ul>
                </nav>

            </div>

        </div>
        <!-- Main -->
        <div id="main">
                        
            <section id="list_vm" class="vm">
                <div class="container">
                    <div class="navbar-header ">
                        <a class="navbar-brand active" href="#" ><h1>Management of VMs</h1></a>
                    </div>
                    <br/>
                    <br/>
                    <ul class="nav nav-tabs">
                        <li ><a href="${pageContext.request.contextPath}/InfoVms">List VMs</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/FormCreatContenaire">Creation</a></li>                  
                    </ul>
                </div>
            </section>
            
            <section id="creation_vm" >
                <div class="container">
                    <header> <h1>Container's creation(VM)</h1></header>
                   
                    <form method="post" action="FormCreatContenaire" class="form-horizontal" role="form" >
                        <div class="form-group">
                           
                            <label for="ram" class="col-sm-2 control-label">Ram</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="ram" id="ram">
                                    <option >256</option>
                                    <option >512</option>
                                    <option >1000</option>
                                </select>
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="cpus" class="col-sm-2 control-label">Cpu</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="cpus" id="cpus">
                                    <option >1</option>
                                    <option >2</option>
                                    <option >3</option>
                                </select>
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="disk" class="col-sm-2 control-label">Disk</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="disk" id="disk">
                                    <option >5</option>
                                    <option >10</option>
                                    <option >15</option>
                                </select>
                            </div>  

                        </div>
                        
                        <p style="color:red">${result}</p>
                        
                        <div class="form-group">
                            <label for="template" class="col-sm-2 control-label">Template * (choose a template)</label>
                            <div class="form-inline">
                            <div class="col-sm-6">
                                <select class="form-control" name="templatedefault" id="template">
                                    <option value="null">Default Templates</option>
                                    <c:forEach items="${tempDefault}" var="item">
                                        <option>${item} </option>
                                    </c:forEach>
                               
                                </select>
                                
                                <select class="form-control" name="template" id="template">
                                    <option value="null">My templates</option>
                                    <c:forEach items="${tempCustom}" var="item">
                                        <option>${item} </option>
                                    </c:forEach>
                               
                                </select>
                            </div>  
                            </div>

                        </div>
                        
                        <div class="form-group">
                            <label for="groupe" class="col-sm-2 control-label">Group</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="groupe" id="groupe">
                                
                                    <option>1 </option> 
                                    <option>2 </option> 
                                    <option>4 </option> 
                                    <option>5 </option> 
                                
                                </select>     
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="hostname" class="col-sm-2 control-label">Hostname</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="hostname" name="hostname"
                                       placeholder="Entrez le nom de la machine">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="passwordDefault" class="col-sm-2 control-label">Password</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="passwordDefault" name="passwordDefault"
                                       placeholder="Entrez le mot de passe">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default" name="actionAdd" value="CreerContainer">Validate</button>
                            </div>
                        </div>

                    </form>

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

