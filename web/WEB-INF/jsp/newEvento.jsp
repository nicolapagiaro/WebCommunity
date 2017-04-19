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
            <a href="<c:url value="/homepage"/>" class="brand-logo">Creazione evento</a>
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
            <div class="card">
                <div class="card-content">
                    <form>
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="name" type="text" class="validate" >
                                <label for="name">Nome evento</label>
                            </div>
                            <div class="input-field col s6">
                                <label for="data">Seleziona la data</label>
                                <input id="data" type="date" class="datepicker">
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input  id="via" type="text" class="validate" >
                                <label for="via">Via e numero civico</label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" id="autocomplete-input" class="autocomplete">
                                <label for="autocomplete-input">Provincia</label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12">
                                <select multiple>
                                    <option value="" disabled selected>Scegli il tuo artista</option>
                                    <c:forEach items="${listaArtisti}" var="e">
                                        <option value="${e.id}">${e.nome} ${e.cognome}</option>
                                    </c:forEach>
                                </select>
                                <label>Scegli dagli artisti già presenti nel database</label>
                            </div>
                        </div>
                    </form>

                    <form action="#">
                        <p>
                            <input type="checkbox" id="test5" />
                            <label for="test5">Red</label>
                        </p>
                        <p>
                            <input type="checkbox" id="test6" checked="checked" />
                            <label for="test6">Yellow</label>
                        </p>
                        <p>
                            <input type="checkbox" class="filled-in" id="filled-in-box" checked="checked" />
                            <label for="filled-in-box">Filled in</label>
                        </p>
                        <p>
                            <input type="checkbox" id="indeterminate-checkbox" />
                            <label for="indeterminate-checkbox">Indeterminate Style</label>
                        </p>
                        <p>
                            <input type="checkbox" id="test7" checked="checked" disabled="disabled" />
                            <label for="test7">Green</label>
                        </p>
                        <p>
                            <input type="checkbox" id="test8" disabled="disabled" />
                            <label for="test8">Brown</label>
                        </p>
                    </form><div class="row">
                        <div class="input-field col s12">
                            <form action="#">
                                <p>
                                    <label for="filled-in-box">Vuoi aggiungere degli artisti?</label>
                                    <input type="checkbox" class="filled-in" id="filled-in-box" checked="checked" />
                                </p>
                            </form>
                        </div>
                    </div>
                    <%--<button class="btn waves-effect waves-light" type="submit" name="action">Submit
                        <i class="material-icons right">send</i>
                    </button>
                    --%>


                    <!--
                                            <div class="row">
                    
                                                //da fare for in jstl
                                                <div class="input-field col s6">
                                                    <input id="nomeA" type="text" class="validate">
                                                    <label for="nomeA">Nome</label>
                                                </div>
                                                <div class="input-field col s6">
                                                    <input id="cognome" type="text" class="validate">
                                                    <label for="cognome">Cognome</label>
                                                </div>
                    
                                                <div class="input-field col s6">
                                                    <input id="nomeA" type="text" class="validate">
                                                    <label for="nomeA">Nome</label>
                                                </div>
                                                <div class="input-field col s6">
                                                    <input id="cognome" type="text" class="validate">
                                                    <label for="cognome">Cognome</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col s12">
                                                    This is an inline input field:
                                                    <div class="input-field inline">
                                                        <input id="email" type="email" class="validate">
                                                        <label for="email" data-error="wrong" data-success="right">Email</label>
                                                    </div>
                                                </div>
                                            </div>
                    -->
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