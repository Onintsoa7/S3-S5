<%-- 
    Document   : header
    Created on : 11 déc. 2023, 20:50:07
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
        <title>1767</title>
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
        <link rel="stylesheet" href="css/dataTables.bootstrap4.css">
        <!-- Date Range Picker CSS -->
        <link rel="stylesheet" href="css/daterangepicker.css">
        <!-- App CSS -->
        <link rel="stylesheet" href="css/app-light.css" id="lightTheme">
        <link rel="stylesheet" href="css/app-dark.css" id="darkTheme" disabled>
    </head>
    <body class="vertical  light  ">
        <div class="wrapper">
            <nav class="topnav navbar navbar-light">
                <button type="button" class="navbar-toggler text-muted mt-2 p-0 mr-3 collapseSidebar">
                    <i class="fe fe-menu navbar-toggler-icon"></i>
                </button>
                <form class="form-inline mr-auto text-muted">
                    <input class="form-control mr-sm-2 bg-transparent border-0 pl-4 text-muted" type="search" placeholder="Tapez ..." aria-label="Search">
                </form>
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link text-muted my-2" href="#" id="modeSwitcher" data-mode="light">
                            <i class="fe fe-sun fe-16"></i>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted pr-0" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="avatar avatar-sm mt-2">
                                <img src="./assets/avatars/me.jpg" alt="..." class="avatar-img rounded-circle">
                            </span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="ServletLogin?out=1">Deconnexion</a>
                        </div>
                    </li>
                </ul>
            </nav>
            <aside class="sidebar-left border-right bg-white shadow" id="leftSidebar" data-simplebar>
                <a href="#" class="btn collapseSidebar toggle-btn d-lg-none text-muted ml-2 mt-3" data-toggle="toggle">
                    <i class="fe fe-x"><span class="sr-only"></span></i>
                </a>
                <nav class="vertnav navbar navbar-light">
                    <!-- nav bar -->
                    <div class="w-100 mb-4 d-flex">
                        <a class="nav-link" href="FormulaireTarification.jsp">
                            <i class="fe fe-award fe-16" style="color: #03224C; font-size: 25px; "></i>
                        </a>
                    </div>
                    <p class="text-muted nav-heading mt-4 mb-1">
                        <span>Fonctionnalités</span>
                    </p>
                    <ul class="navbar-nav flex-fill w-100 mb-2">
                        <li class="nav-item dropdown">
                            <a href="#dashboard" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                                <i class="fe fe-navigation fe-16"></i>
                                <span class="ml-3 item-text">NAVIGATION</span><span class="sr-only">(current)</span>
                            </a>
                            <ul class="collapse list-unstyled pl-4 w-100" id="dashboard">
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="formCategorie.jsp"><span class="ml-1 item-text">Categorie</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletMateriel"><span class="ml-1 item-text">Materiel</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletStyle"><span class="ml-1 item-text">Style</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletFormStyleMateriel"><span class="ml-1 item-text">Materiel et style</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletListeStyle"><span class="ml-1 item-text">Liste materiel et style</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletTaille"><span class="ml-1 item-text">Taille</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletProfil"><span class="ml-1 item-text">Profil</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="InsertionFournisseur.jsp"><span class="ml-1 item-text">Fournisseur</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletEmployer"><span class="ml-1 item-text">Personnel</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletPoste"><span class="ml-1 item-text">Poste</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletGenre"><span class="ml-1 item-text">Genre</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletRemise"><span class="ml-1 item-text">Remise</span></a>
                                </li>
                            </ul>
                        </li>   
                        <li class="nav-item w-100">
                            <a class="nav-link" href="FormulaireTarification.jsp">
                                <i class="fe fe-dollar-sign fe-16"></i>
                                <span class="ml-3 item-text">Tarifs</span>
                            </a>
                        </li>  
                        <li class="nav-item w-100">
                            <a class="nav-link" href="ServletFabrication">
                                <i class="fe fe-box"></i>
                                <span class="ml-3 item-text">Meuble</span>
                            </a>
                        </li>
                        <li class="nav-item w-100">
                            <a class="nav-link" href="Report.jsp">
                                <i class="fe fe-box"></i>
                                <span class="ml-3 item-text">Report</span>
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="#client" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                                <i class="fe fe-activity fe-16"></i>
                                <span class="ml-3 item-text">CLIENT & ACTIVITES</span><span class="sr-only">(current)</span>
                            </a>
                            <ul class="collapse list-unstyled pl-4 w-100" id="client">
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletClient"><span class="ml-1 item-text">Client</span></a>
                                </li>
                            </ul>
                            <ul class="collapse list-unstyled pl-4 w-100" id="client">
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletFacturation"><span class="ml-1 item-text">Facturation</span></a>
                                </li>
                            </ul>
                            <ul class="collapse list-unstyled pl-4 w-100" id="client">
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="ServletListeFacture"><span class="ml-1 item-text">Facture</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </aside>
