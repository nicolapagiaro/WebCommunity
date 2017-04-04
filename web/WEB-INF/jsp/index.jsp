<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- page title -->
        <title>WebCommunity</title>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link href="<c:url value="/resources/css/materialize.min.css" />" rel="stylesheet" media="screen,projection">
        <!--Import main_css.css-->
        <link href="<c:url value="/resources/css/main_css.css" />" rel="stylesheet" media="screen,projection">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
    </head>
    <body>
    <nav class="teal darken-1">
        <!-- Dropdown Structure -->
        <ul id="dropdown1" class="dropdown-content">
            <li><a href="#!">Accedi</a></li>
            <li><a href="<c:url value="/registrazione"/>">Registrati</a></li>
        </ul>
        <!-- navbar structure -->
        <div class="nav-wrapper teal darken-1 container">
            <a href="#!" class="brand-logo">WebCommunity</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <li><a href="#!">Categorie</a></li>
                <!-- Dropdown Trigger -->
                <li>
                    <a class="dropdown-button" href="#!" data-activates="dropdown1">
                        Community <i class="material-icons right">arrow_drop_down</i>
                    </a>
                </li>
            </ul>
            <ul class="side-nav" id="mobile-demo">
                <li><a href="#!">Categorie</a></li>
                <li><a href="#!">Accedi</a></li>
                <li><a href="<c:url value="/registrazione"/>">Registrati</a></li>
            </ul>
        </div>
    </nav>
    
    <p>Prove: ${listEventi}</p>

    <!-- footer -->
    <footer class="page-footer teal darken-1">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="white-text">WebCommunity</h5>
                    <p class="grey-text text-lighten-4">
                        Sviluppo di un progetto di gruppo
                    </p>
                </div>
                <div class="col l4 offset-l2 s12">
                    <h5 class="white-text">Autori</h5>
                    <ul>
                        <li class="grey-text text-lighten-3">Link 1</li>
                        <li class="grey-text text-lighten-3">Link 2</li>
                        <li class="grey-text text-lighten-3">Link 3</li>
                        <li class="grey-text text-lighten-3">Link 4</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <div class="container">
                © 2017 Classe 5IB
            </div>
        </div>
    </footer>
    
    <!-- fast loading of the page -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/materialize.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main_script.js" />"></script>
</body>
</html>
