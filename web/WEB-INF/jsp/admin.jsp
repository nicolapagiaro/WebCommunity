<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- page title -->
        <title>Pannello di controllo - WebCommunity</title>
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
    <nav class="white nav-extended">
        <!-- navbar structure -->
        <div class="nav-wrapper white container">
            <a href="#!" class="brand-logo">Amministrazione</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <li><a href="<c:url value="/admin/logout"/>">Torna al sito</a></li>
            </ul>
        </div>
        <div class="nav-content container">
            <ul class="tabs tabs-transparent">
                <li class="tab">
                    <a class="<c:if test="${section == 0}">active</c:if>" href="#users">
                            Utenti
                        </a>
                    </li>
                    <li class="tab">
                        <a class="<c:if test="${section == 1}">active</c:if>" href="#artists">
                            Artisti
                        </a>
                    </li>
                    <li class="tab">
                        <a class="<c:if test="${section == 2}">active</c:if>" href="#events">
                            Eventi
                        </a>
                    </li>
                    <li class="tab">
                        <a class="<c:if test="${section == 3}">active</c:if>" href="#cats">
                            Categorie
                        </a>
                    </li>
                    <li class="tab">
                        <a class="<c:if test="${section == 4}">active</c:if>" href="#dash">
                            Dashboard
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- div per il content delle tab -->

        <!-- utenti -->
        <div id="users" class="container">
            <br>
            <div class="card">
                <div class="card-content">
                    <h5>Lista degli utenti della webcommunity</h5>
                    <br>
                    <table class="highlight">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Cognome</th>
                                <th>Nickname</th>
                                <th>Email</th>
                                <th>Categorie seguite</th>
                                <th>Azioni</th>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${users}" var="u">
                            <tr>
                                <td>${u.nome}</td>
                                <td>${u.cognome}</td>
                                <td>${u.nickname}</td>
                                <td>${u.email}</td>
                                <td>
                                    <c:set var="count" value="0" scope="page" />
                                    <c:forEach items="${u.categorie}" var="c">
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                        ${c.nome}<c:if test="${u.categorie.size() != count}">, </c:if>
                                    </c:forEach>
                                    <c:if test="${u.categorie.size() == 0}">-</c:if>
                                    </td>
                                    <td><a href="<c:url value="/admin/deleteUtente?id=${u.id}"/>"><i class="material-icons">delete</i></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br>
                <p class="red-text darken-1">Attenzione!</p>
                <p>
                    Eliminando un utente verranno eliminati tutti i dati su di lui, i suoi 
                    commenti e le sue categorie preferite.
                </p>
            </div>
        </div>
    </div>

    <!-- eventi -->
    <div id="events" class="container">
        <br>
        <div class="card">
            <div class="card-content">
                <h5>Lista degli eventi nella webcommunity</h5>
                <br>
                <table class="highlight">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Data</th>
                            <th>Luogo</th>
                            <th>Provincia</th>
                            <th>Categoria</th>
                            <th>Artisti coinvolti</th>
                            <th>Azioni</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${events}" var="e">
                            <tr>
                                <td>${e.nome}</td>
                                <td>${e.dataE}</td>
                                <td>${e.via_numero}</td>
                                <td>${e.provincia}</td>
                                <td>${e.categoria.nome}</td>
                                <td>
                                    <c:set var="count" value="0" scope="page" />
                                    <c:forEach items="${e.artisti}" var="a">
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                        ${a.nome} ${a.cognome}<c:if test="${e.artisti.size() != count}">, </c:if>
                                    </c:forEach>
                                    <c:if test="${e.artisti.size() == 0}">-</c:if>
                                    </td>
                                    <td><a href="<c:url value="/admin/deleteEvento?id=${e.id}"/>"><i class="material-icons">delete</i></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br>
                <p class="red-text darken-1">Attenzione!</p>
                <p>
                    Eliminando un evento verranno eliminate tutte le informazioni su di esso
                    e tutte le recensioni degli utenti su di esso.
                </p>
            </div>
        </div>
    </div>

    <!-- artisti -->
    <div id="artists" class="container">
        <br>
        <div class="card">
            <div class="card-content">
                <div class="row">
                    <div class="col l12">
                        <h5>Lista degli artisti nella webcommunity</h5>
                        <br>
                    </div>
                    <div class="col l8">
                        <table class="highlight">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Cognome</th>
                                    <th>Eventi</th>
                                    <th>Azioni</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${artisti}" var="a">
                                    <tr>
                                        <td>${a.nome}</td>
                                        <td>${a.cognome}</td>
                                        <td>
                                            <c:set var="count" value="0" scope="page" />
                                            <c:forEach items="${a.eventi}" var="e">
                                                <c:set var="count" value="${count + 1}" scope="page"/>
                                                ${e.nome}<c:if test="${a.eventi.size() != count}">, </c:if>
                                            </c:forEach>
                                            <c:if test="${a.eventi.size() == 0}">-</c:if>
                                            </td>
                                            <td><a href="<c:url value="/admin/deleteArtista?id=${a.id}"/>"><i class="material-icons">delete</i></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col l4">
                        <br>
                        <p class="red-text darken-1">Attenzione!</p>
                        <p>
                            Eliminando un artista verranno eliminate tutte le informazioni su di esso.
                        </p>
                    </div>
                    <div class="col l12">
                        <br>
                        <h5>Aggiungi un nuovo artista</h5>
                        <form action="<c:url value="/admin/addArtista"/>" method="POST">
                            <div class="row">
                                <div class="input-field col s4">
                                    <input id="nomeA" name="nomeA" type="text" maxlength="30">
                                    <label for="nomeA">Nome</label>
                                </div>
                                <div class="input-field col s4">
                                    <input id="cognomeA" name="cognomeA" type="text" maxlength="30">
                                    <label for="cognomeA">Cognome</label>
                                </div>
                                <div class="col s4">
                                    <button type="submit" id="btn_aggiungi_artista" class="btn waves-effect waves-light">
                                        Aggiungi
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <!-- categorie -->
        <div id="cats" class="container">
            <br>
            <div class="card">
                <div class="card-content">
                    <div class="row">
                        <div class="col l12">
                            <h5>Lista delle categorie presenti</h5>
                            <br>
                        </div>
                        <div class="col l8">
                            <table class="highlight">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nome</th>
                                        <th>Azioni</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${cats}" var="c">
                                        <tr>
                                            <td>${c.id}</td>
                                            <td>${c.nome}</td>
                                            <td><a href="<c:url value="/admin/deleteCategoria?id=${c.id}"/>">
                                                    <i class="material-icons">delete</i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="col l4">
                            <p class="red-text darken-1">Attenzione!</p>
                            <p>
                                Eliminando la categoria verranno eliminati tutti gli eventi
                                che hanno come categoria la categoria che si vuole eliminare.
                            </p>
                        </div>
                        <div class="col l12">
                            <br>
                            <h5>Aggiungi una nuova categoria</h5>
                            <form action="<c:url value="/admin/addCategoria"/>" method="POST">
                                <div class="row">
                                    <div class="input-field col s4">
                                        <input id="nome_categoria" name="nomeC" type="text" maxlength="30">
                                        <label for="nomeC">Nome</label>
                                    </div>
                                    <div class="col s8">
                                        <button type="submit" id="btn_aggiungi_categoria" class="btn waves-effect waves-light">
                                            Aggiungi
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- dashboard -->
        <div id="dash" class="container">
            <br>
            <div class="card">
                <div class="card-content">
                    <h5>Invio di mail agli utenti</h5>
                    <br>
                    <form action="<c:url value="/admin/sendMail"/>" method="POST">
                        <div class="row">                    
                            <div class="input-field col s6">
                                <select name="to">
                                    <option name="to" value="" disabled selected>Scegli</option>
                                    <c:forEach items="${users}" var="us" >
                                        <option value="${us.email}">${us.nome} ${us.cognome} - ${us.email}</option>
                                    </c:forEach>
                                </select>
                                <label>Destinatario</label>
                            </div>
                            <div class="col s6">
                                <p>Messaggi preimpostati:</p>
                                <p>
                                    <input name="msg" type="radio" id="msg_default" />
                                    <label class="radio_text" id="label_msg_default" for="msg_default">Nessuno</label>
                                </p>
                                <p>
                                    <input name="msg" type="radio" id="msg1" />
                                    <label class="radio_text" id="label_msg1" for="msg1">
                                        Nuovo evento disponibile delle tue categorie preferite, affrettati a partecipare ed a valutarlo!
                                    </label>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <textarea name="mail" id="textarea1_mail" class="materialize-textarea" maxlength="500"></textarea>
                                <label for="textarea_mail">Scrivi la mail...</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s6 right-align">
                                <button class="btn waves-effect waves-light" type="submit" name="action" id="invia_mail">
                                    Invia
                                </button> 
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>



        <!-- fast loading of the page -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/materialize.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/main_script.js" />"></script>
    </body>
</html>
