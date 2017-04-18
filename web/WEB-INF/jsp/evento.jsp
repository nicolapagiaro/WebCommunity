<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- page title -->
        <title>Evento - WebCommunity</title>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link href="<c:url value="/resources/css/materialize.min.css" />" rel="stylesheet" media="screen,projection">
        <!--Import main_css.css-->
        <link href="<c:url value="/resources/css/main_css.css" />" rel="stylesheet" media="screen,projection">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
    </head>
    <body>
    <nav class="white">
        <!-- navbar structure -->
        <div class="nav-wrapper white container">
            <a href="<c:url value="/homepage?ordine=default"/>" class="brand-logo">Homepage</a>
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
            <div class="col l6 s12">
                <div class="card">
                    <div class="card-content">
                        <span class="card-title">${evento.nome}</span>
                        <p>Data: ${evento.dataE}</p>
                        <p>${evento.via_numero} - ${evento.provincia}</p>
                        <p>${evento.categoria.nome}</p>
                        <p>Voto medio: </p>
                    </div>
                </div>
            </div>
            <div class="col l6 s12">
                <div class="card">
                    <div class="card-content">
                        <span class="card-title">Voti e commenti</span>
                        <c:forEach items="${voti_commenti}" var="v">
                            <div class="row">
                                <div class="col l12">
                                    <p><b>${v.utente.nome} ${v.utente.cognome}</b> - ${v.voto}</p>
                                    <p>${v.commento}</p>
                                </div>
                            </div>
                        </c:forEach>
                        <c:if test="${!commentato}">
                            <div class="row">
                                <div class="col l12">
                                    <div class="section no-padding"></div>
                                    <div class="divider"></div>
                                    <div class="section no-padding"></div>
                                </div>
                                <div class="col l2">
                                    <br>
                                    <img width="42px" class="responsive-img circle" alt="userimage" src="<c:url value="/resources/images/userimage.png"/>"/>
                                </div>
                                <form class="col l10 pull-l1">
                                    <div class="row">
                                        <div class="input-field col l12">
                                            <textarea id="textarea1" class="materialize-textarea" maxlength="255" data-length="255"></textarea>
                                            <label for="textarea1">Commento..</label>
                                        </div>
                                        <div class="input-field col l4">
                                            <p id="voto">Voto</p>
                                            <p class="range-field">
                                                <input type="range" id="range" min="0" max="5" value="0"/>
                                            </p>
                                        </div>
                                        <div class="input-field right-align col l8">
                                            <button type="submit" class="btn-flat">
                                                <i class="material-icons right">send</i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:if>
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
