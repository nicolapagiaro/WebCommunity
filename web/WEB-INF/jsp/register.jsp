<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- page title -->
        <title>Registrazione al sito - WebCommunity</title>
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
            <a href="<c:url value="/"/>" class="brand-logo">WebCommunity</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <li><a href="<c:url value="/"/>">Torna al sito</a></li>
            </ul>
            <ul class="side-nav" id="mobile-demo">
                <li><a href="<c:url value="/"/>">Torna al sito</a></li>
            </ul>
        </div>
    </nav>

    <br>
    <div class="container">
        <div class="row">
            <div class="col l6 m8 s12 offset-l3 offset-m2">
                <h5 class="center-align">Registrazione alla Communty</h5>
                <p class="center-align grey-text-costum">
                    Utilizziamo i tuoi dati per fornirti un'esperienza pi&ugrave; 
                    personalizzata possibile
                </p>
                <!-- form di registrazione -->
                <form method="POST" action="<c:url value="/doRegister"/>" class="col s12">
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="nick" id="nick" type="text"/>
                            <label for="nick">Nickname</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="nome" id="nome" type="text"/>
                            <label for="nome">Nome</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="cognome" id="cognome" type="text"/>
                            <label for="cognome">Cognome</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="email" id="email" type="email" class="validate"/>
                            <label for="email" data-error="Immettere una mail valida" data-success="">Email</label>
                        </div>
                    </div>
                    <p class="grey-text-costum">Seleziona le categorie di eventi preferiti</p>
                    <div class="row">
                        <c:forEach items="${listaCategorie}" var="cat">
                            <div class="col l4 m6 s12">
                                <p>
                                    <input value="${cat.id}" name="categorie" type="checkbox" class="filled-in" id="${cat.id}"/>
                                    <label for="${cat.id}">${cat.nome}</label>
                                </p>
                            </div>
                        </c:forEach>
                    </div>
                    <br>
                    <div class="right-align">
                        <button class="btn waves-effect waves-light" type="submit" name="action" id="register">
                            Iscriviti
                        </button> 
                    </div>
                </form>
                <!-- / form di registrazione -->
            </div>
        </div>
    </div>        

    <!-- fast loading of the page -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/materialize.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main_script.js" />"></script>
</body>
</html>
