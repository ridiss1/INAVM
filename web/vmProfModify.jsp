


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
                    <h1 id="title">${sessionScope.sessionUser}</h1>
                    <p>Professor</p>
                </div>

                <!-- Nav -->
                <nav id="nav">

                    <ul>
                        <li><a href="accueilProf.jsp" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Home</span></a></li>

                        <li><a class="active" href="${pageContext.request.contextPath}/InfoVms" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtual Machine</span></a></li>
                        
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
                    <header> <h1>Modification du container ${InfoContainer.hostname}</h1></header>
                    
                    <form method="post" action="${pageContext.request.contextPath}/InfoVms" class="form-horizontal" role="form">
                            
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
                        
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default" name="actionUpdate" value="UpdateContainer">Valider</button>
                            </div>
                        </div>
                            
                        <input type="hidden" name="VMid" value="${InfoContainer.vmid}">                      
                       
                    </form>
                        
                </div>
                                
                <div class="container four dark" id="status">
                        <c:if test="${InfoUpdate && RequestUpdate}">
                            <div class="container alert alert-success">Succes modify.</div>
                            
                        </c:if>                                  

                        <c:if test="${ (not InfoUpdate) && RequestUpdate}">
                            <div class="container alert alert-danger">Problème avec la mis à jour .</div>
                        </c:if>     
                    
                </div>        
            </section>

            
            <section id="content">
     
                <div class="container">    
                    <a href="#" class="list-group-item active">
                        Information Vm
                    </a>
                    <table class="table table-hover">
                           
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Hostname</th>
                                    <th>Address</th>
                                    <th>Ram</th>
                                    <th>Cpu</th>
                                    <th>Disk</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                        <tr>
                                            <td>${InfoContainer.vmid}</td>
                                            <td>${InfoContainer.hostname}</td>
                                            <td>${InfoContainer.ip_address}</td>
                                            <td>${InfoContainer.memory}</td>
                                            <td>${InfoContainer.cpus}</td>
                                            <td>${InfoContainer.disk}</td>
                                            <td>${InfoContainer.status}</td>
                                        </tr>
                            </tbody>
                        
                    </table>
                                        
                    <form method="post" action="VmProfUpdateServlet">
                        
                        <input type="hidden" name="VMid" value="${InfoContainer.vmid}">
                        <center> <input type="submit" name="actionDelete" value="SupprimerContainer"></center>                    
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

