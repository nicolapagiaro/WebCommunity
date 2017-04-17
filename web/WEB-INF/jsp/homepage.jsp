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
                <!-- utente -->
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
                <!-- categorie seguite -->
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
                <!-- nuovo evento -->
                <div class="card">
                    <div class="card-content">
                        <div class="row">
                            <form method="POST" action="<c:url value="/homepage/newEvento"/>">
                                <div class="input-field col s12">
                                    <input name="nomeE" id="nomeE" type="text" class="validate">
                                    <label for="nomeE">Nome nuovo evento</label>
                                </div>
                                <div class="input-field col s12">
                                    <label for="numeroA">Numero di artisti presenti</label>
                                    <br>
                                    <br>
                                    <p class="range-field">
                                        <input type="range" name="numeroA" min="0" max="5" value="0" />
                                    </p>
                                </div>
                                <div class="right-align col l12">
                                    <button class="btn btn-flat" 
                                            id="new_evento" type="submit" name="action">
                                        Crea
                                    </button> 
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- eventi -->
            <div class="col l9">
                <div class="card">
                    <div class="card-content">
                        <div class="row">
                            <div class="col l6">
                                <h5>Eventi</h5>
                            </div>
                            <div class="col l6 right-align">

                                <!-- Dropdown Trigger -->
                                <a class='dropdown-button btn btn-flat' href='#' data-activates='dropdown1'>Ordina per</a>

                                <!-- Dropdown Structure -->
                                <ul id='dropdown1' class='dropdown-content'>
                                    <li><a href="<c:url value="/homepage?ordine=dc"/>">data crescente</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<c:url value="/homepage?ordine=dd"/>">data decrescente</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<c:url value="/homepage?ordine=lc"/>">lettera crescente</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<c:url value="/homepage?ordine=ld"/>">lettera decrescente</a></li>
                                    <!--<li><a href="#!"><i class="material-icons">view_module</i>four</a></li>
                                    <li><a href="#!"><i class="material-icons">cloud</i>five</a></li> -->
                                </ul>

                            </div>
                        </div>
                        <div class="row">
                            <c:forEach items="${listaEventi}" var="e">
                                <div class="col l12">
                                    <span class="card-title">${e.nome}</span>
                                    <p class="">Data: ${e.dataE}</p>
                                    <p class="">${e.via_numero} - ${e.provincia}</p>
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
