<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- page title -->
        <title>Homepage - WebCommunity</title>
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
    <nav class="white">
        <!-- navbar structure -->
        <div class="nav-wrapper white container">
            <a href="#!" class="brand-logo">Homepage</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <li><a href="<c:url value="/logout"/>">Esci</a></li>
            </ul>
        </div>
    </nav>

    <div class="homepage-bg"></div> <!-- cool background -->
    <br>
    <br>
    <div class="container">
        <div class="row">
            <div class="col l3">
                <div class="card">
                    <div class="card-content">
                        <div class="center-align">
                            <img src="<c:url value="/resources/images/userimage.png"/>" 
                                 width="100px" alt="user_image" class="circle responsive-img">
                            <br>
                            <h6><b>${utente.nome} ${utente.cognome}</b></h6>
                            <p>${utente.nickname}</p>
                            <p>${utente.email}</p>

                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-content">
                        <span class="card-title">Le tue categorie preferite</span>
                        <div class="section no-padding"></div>
                        <div class="divider"></div>
                        <div class="section"></div>
                        <c:forEach items="${listaCategorie}" var="c">
                            <a href='<c:url value="/homepage/categoria?c=${c.id}"/>'>
                                <div class="chip">${c.nome}</div>
                            </a>   
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col l9">
                <div class="card">
                    <div class="card-content">
                        <div class="row">
                            <div class="col l6">
                                <h5>Eventi</h5>
                            </div>
                            <div class="col l6 left-align">
                                <p>Ordina per nome</p>
                            </div>
                        </div>
                        <div class="row">
                            <c:forEach items="${listaEventi}" var="e">
                                <div class="col l12">
                                    <span class="card-title">${e.nome}</span>
                                    <p class="">Il: ${e.dataE}</p>
                                    <p class="">Indirizzo: ${e.via_numero} - ${e.provincia}</p>
                                    ${e.categoria.nome}
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>        

        <!-- fast loading of the page -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/materialize.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/main_script.js" />"></script>
    </body>
</html>