<%-- 
    Document   : index
    Created on : 8 déc. 2015, 14:59:35
    Author     : Ridiss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header> <h1>Creation d'un container</h1></header>
                   
                    <form method="post" action="vmProf" class="form-horizontal" role="form" >
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
                            <label for="template" class="col-sm-2 control-label">Template</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="template" id="template">
                                   
                                    <option >debian-7.0-standard_7.0-2_i386.tar.gz </option> 
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
                                <button type="submit" class="btn btn-default" name="actionAdd" value="CreerContainer">Valider</button>
                            </div>
                        </div>

                    </form>
    </body>
</html>
