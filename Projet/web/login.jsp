<%-- 
    Document   : login
    Created on : 11 déc. 2023, 21:03:09
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>Tiny Dashboard - A Bootstrap Dashboard Template</title>
    <!-- Simple bar CSS -->
    <link rel="stylesheet" href="css/simplebar.css">
    <!-- Fonts CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <!-- Icons CSS -->
    <link rel="stylesheet" href="css/feather.css">
    <link rel="stylesheet" href="css/select2.css">
    <link rel="stylesheet" href="css/dropzone.css">
    <link rel="stylesheet" href="css/uppy.min.css">
    <link rel="stylesheet" href="css/jquery.steps.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">
    <link rel="stylesheet" href="css/quill.snow.css">
    <!-- Date Range Picker CSS -->
    <link rel="stylesheet" href="css/daterangepicker.css">
    <!-- App CSS -->
    <link rel="stylesheet" href="css/app-light.css" id="lightTheme">
    <link rel="stylesheet" href="css/app-dark.css" id="darkTheme" disabled>
  </head>
      <style>
          .col-md-6{
                padding-top: 160px;
                padding-right: 160px;
                margin-left:  500px;
          }
      </style>
  <body>
  <html>
    <main role="main" class="main-content">
            <div class="container-fluid">
              <div class="row justify-content-center">
                <div class="col-12">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="card shadow mb-4">
                    <form action="ServletLogin" method="post">
                        <div class="card-body">
                          <p class="mb-2"><strong>Login</strong></p>
                          <div class="form-group mb-3">
                            <label for="example-date">Email</label>
                            <input class="form-control" id="email" type="email" name="email">
                          </div>
                          <div class="form-group mb-3">
                            <label for="example-month">Mot de passe</label>
                            <input class="form-control" id="password" type="password" name="password">
                          </div>
                          <div class="form-group mb-1">
                              <button class="form-control" type="submit" style="width: 100px" >Valider</button>
                          </div>
                        </div> 
                    </form>
                      </div> <!-- /.card -->
                    </div> <!-- /.col -->
                </div> <!-- .col-12 -->
              </div> <!-- .row -->
            </div> 
            </div> 
          </main>
  </body>
</html>
