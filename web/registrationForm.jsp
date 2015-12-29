<%-- 
    Document   : registrationForm
    Created on : 29 déc. 2015, 17:47:39
    Author     : olga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>INAVM</title>

    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please enter your details</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <label>First Name</label>
                                <input class="form-control" name="first_name" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <label>Last Name</label>
                                <input class="form-control" name="last_name" type="text" >
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input class="form-control" name="email" type="email" >
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input class="form-control" name="password" type="password" >
                            </div>
                            <div class="form-group">
                                <label>Retype Password</label>
                                <input class="form-control" name="re_password" type="password" >
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="user_status" id="st_professor" class="status_check" value="professor" checked="">Professor
                                    </label>
                                </div>
                            </div>
                            <div class="form-group prof_field">
                                <label>Department</label>
                                <select class="form-control" id="">
                                    <option value="STPI">STPI</option>
                                    <option value="GB">GB</option>
                                    <option value="GC">GC</option>
                                    <option value="GEI">GEI</option>
                                    <option value="GMM">GMM</option>
                                    <option value="GM">GM</option>
                                    <option value="GP">GP</option>
                                    <option value="GPE">GPE</option>
                                </select>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="index.html" class="btn btn-lg btn-success btn-block">Save</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-md-offset-2">

        </div>
    </div>
</div>

<!-- jQuery -->
<script src="jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/sb-admin-2.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        whatIsChecked();
        $(".status_check").on("change",function(){
            whatIsChecked();
        });

    });
    function whatIsChecked(){
        $(".prof_field").hide();
        
        if($("#st_professor").is(":checked")){
            $(".prof_field").show();
        }
    }
</script>
</body>

</html>
