<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- page title -->
        <title>Nuovo evento - WebCommunity</title>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.min.css-->
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
            <a href="#!" class="brand-logo">Creazione evento</a>
            <ul class="right hide-on-med-and-down">
                <li class="active"><a href="<c:url value="/homepage?ordine=default"/>">Torna alla homepage</a></li>
                <li><a href="<c:url value="/logout"/>">Esci</a></li>
            </ul>
        </div>
    </nav>

    <div class="homepage-bg"></div> <!-- cool background -->
    <br>
    <div class="container">
        <div class="row">
            <div class="card">
                <div class="card-content"> 
                    <form method="POST" id="form_newE" action="<c:url value="/homepage/newEvento/upload"/>">
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="name" type="text" name="name" value="${nomeE}" autocoplete="off">
                                <label for="name">Nome evento</label>
                            </div>
                            <div class="input-field col s6">
                                <label for="data">Seleziona la data</label>
                                <input id="data" name="data" type="date" class="datepicker">
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input  id="via" name="via" type="text" autocoplete="off">
                                <label for="via">Via e numero civico</label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" id="provincia" name="provincia" autocoplete="off" class="autocomplete">
                                <label for="provincia">Provincia</label>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="input-field col s12">
                                <select id="categoria" name="categoria">
                                    <option value="" disabled selected>Scegli la categoria</option>
                                    <c:forEach items="${listaCategorie}" var="c">
                                        <option value="${c.id}">${c.nome}</option>
                                    </c:forEach>
                                </select>
                                <label>Scegli una categoria</label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12">
                                <select id="artistiDB" name="artistiDB" multiple>
                                    <option value=""  disabled selected>Scegli gli artisti</option>
                                    <c:forEach items="${listaArtisti}" var="e">
                                        <option value="${e.id}">${e.nome} ${e.cognome}</option>
                                    </c:forEach>
                                </select>
                                <label>Scegli dagli artisti già presenti nel database</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <p>                                   
                                    <input type="checkbox" class="filled-in" id="nuoviArtisti" name="nuoviArtisti" align="right"/>
                                    <label for="nuoviArtisti" align="left">Vuoi inserire nuovi artisti?</label>
                                </p>
                            </div>
                            <div class="input-field col s6 hide" id="numArtisti" name="numArtisti">
                                <p class="range-field">
                                    <input type="range"name="nA" id="nA" min="0" max="50" />
                                </p>
                                <br>
                                <button class="btn waves-effect waves-light right" type="submit" name="action" id="nuoviA">Inserisci gli artisti
                                    <i class="material-icons right">send</i>
                                </button>

                            </div>
                            <button class="btn waves-effect waves-light right" type="submit" name="action" id="caricaE">Carica l'evento
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </form>
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
