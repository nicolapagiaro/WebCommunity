<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- page title -->
        <title>Modifica profilo - WebCommunity</title>
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
            <a href="#!" class="brand-logo">Modifica profilo</a>
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
            <form method="POST" id="form_profilo" action="<c:url value="/homepage/doSetProfilo"/>">
                <div class="card">
                    <div class="card-content"> 
                        <div class="row">
                            <div class="input-field col s6">
                                <input value="${u.getNome()}"  maxlength="30" id="nome" name="nome" type="text">
                                <label class="active" for="nome">Nome</label>
                            </div>
                            <div class="input-field col s6">
                                <input value="${u.getCognome()}" maxlength="30" id="cognome" name="cognome" type="text">
                                <label class="active" for="cognome">Cognome</label>
                            </div>

                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input value="${u.getNickname()}" maxlength="30" id="nickname" name="nickname" type="text">
                                <label class="active" for="nickname">Nickname</label>
                            </div>
                            <div class="input-field col s6">
                                <input value="${u.getEmail()}" maxlength="30" id="email" name="email" type="email" class="validate">
                                <label for="email" data-error="Immettere una mail valida" data-success="">Email</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <p class="grey-text-costum">Le tue categorie preferite</p>
                                <c:forEach items="${catChecked}" var="item">
                                    <p>
                                        <input type="checkbox" id="${item.id}" name="cat" class="filled-in" checked="checked" value="${item.id}"/>
                                        <label for="${item.id}">${item.nome}</label>
                                    </p>
                                </c:forEach>
                                <c:if test="${catChecked.size() == 0}">
                                    <p class="grey-text">Nessuna</p>
                                </c:if>
                            </div>
                            <div class="input-field col s12">
                                <p class="grey-text-costum">Altre categorie disponibili</p>
                                <c:forEach  items="${cat}" var="item">
                                    <p>
                                        <input type="checkbox" id="${item.id}" class="filled-in" name="cat" value="${item.id}"/>
                                        <label for="${item.id}">${item.nome}</label>
                                    </p>
                                </c:forEach>
                                <c:if test="${cat.size() == 0}">
                                    <p class="grey-text">Nessuna</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 right-align">
                                <a class="btn-flat waves-effect waves-light" href="<c:url value="/homepage?ordine=default"/>">
                                    Annulla
                                </a>
                                <button class="btn waves-effect waves-light" type="submit" name="action" id="register">
                                    Salva
                                </button> 
                            </div>
                        </div>
                    </div>
                </div> 
            </form>
        </div>
    </div>


    <!-- fast loading of the page -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/materialize.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main_script.js" />"></script>
</body>
</html>
