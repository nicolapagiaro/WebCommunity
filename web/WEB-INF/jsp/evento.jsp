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
            <a href="#!" class="brand-logo">Evento</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <li class="active"><a href="<c:url value="/homepage?ordine=default"/>">Torna alla homepage</a></li>
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
                        <p class="grey-text text-darken-2">${evento.categoria.nome}</p>
                        <span class="card-title">${evento.nome}</span>
                        <p><i class="material-icons">today</i> ${evento.dataE}</p>
                        <p><i class="material-icons">location_on</i> ${evento.via_numero} - ${evento.provincia}</p>
                        <span><i class="material-icons">people</i></span>
                        <c:forEach items="${evento.artisti}" var="a">
                            <div class="chip">${a.nome} ${a.cognome}</div>
                        </c:forEach>
                        <c:if test="${evento.artisti.size() == 0}">
                            Nessun artista presente
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col l6 s12">
                <div class="card">
                    <div class="card-content">
                        <span class="card-title">Voti e commenti</span>
                        <c:forEach items="${voti_commenti}" var="v">
                            <div class="row">
                                <div class="col l11">
                                    <p><b>${v.utente.nome} ${v.utente.cognome}</b></p>
                                    <p>${v.commento}</p>
                                    <p>Voto: ${v.voto}</p>
                                </div>
                                <c:if test="${idUtente == v.utente.id}">
                                    <div class="col l1 left-align">
                                        <a href="#confirm_delete"><i class="material-icons">delete</i></a>
                                    </div>
                                    <!-- Modal Structure -->
                                    <div id="confirm_delete" class="modal">
                                        <div class="modal-content">
                                            <h4>Conferma</h4>
                                            <p>Confermi l'eliminazione della tua recensione su questo evento?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="#!" id="confirm_delete_no" class="modal-action modal-close btn teal lighten-2">No</a>
                                            <a href="#!" id="confirm_delete_si" class="modal-action modal-close btn-flat">Si</a>
                                        </div>
                                    </div>
                                    <!-- Modal Structure -->
                                </c:if>
                            </div>
                        </c:forEach>
                        <c:if test="${voti_commenti.size() == 0}">
                            <div class="row">
                                <div class="col l12">
                                    <p class="grey-text text-darken-2">Ancora nessun commento</p>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${!commentato}">
                            <div class="row">
                                <div class="col l12">
                                    <div class="section no-padding"></div>
                                    <div class="divider"></div>
                                    <div class="section no-padding"></div>
                                </div>
                                <div class="col l2">
                                    <br>
                                    <img width="42px" class="responsive-img circle circle-min" alt="userimage" src="<c:url value="/resources/images/userimage.png"/>"/>
                                </div>
                                <form class="col l10 pull-l1" action="<c:url value="/homepage/evento/commenta"/>" method="POST">
                                    <div class="row">
                                        <div class="input-field col l12">
                                            <textarea name="commento" id="commento" class="materialize-textarea" maxlength="255"></textarea>
                                            <label for="commento">Commento..</label>
                                        </div>
                                        <div class="input-field col l4">
                                            <p id="voto">Voto</p>
                                            <p class="range-field">
                                                <input type="range" name="voto" id="range" min="0" max="5" value="0"/>
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
