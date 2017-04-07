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
    <nav class="white" role="navigation">
        <!-- navbar structure -->
        <div class="nav-wrapper white container">
            <a href="#!" class="brand-logo">WebCommunity</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="<c:url value="/registrazione"/>">Registrati</a></li>
            </ul>
        </div>
    </nav>

    <!-- page content -->
    <div class="parallax-container">
        <div class="section no-pad-bot">
            <div class="container">
                <div class="row">
                    <div class="col l9">
                        <br><br>
                        <h4 class="header left-align white-text">Join the community</h4>
                        <div class="row left-align">
                            <h5 class="header col s12 white-text">Condividi eventi, vota e commenta</h5>
                        </div>
                        <br><br>
                    </div>
                    <br><br>
                    <form method="POST" action="<c:url value="/doLogin"/>" class="col l3 form-fg teal text-lighten-1">
                        <h5 class="center white-text">Accedi</h5>
                        <div class="row white">
                            <br>
                            <div class="input-field col s12">
                                <input name="nick" id="nickname" type="text">
                                <label for="nickname">Nickname</label>
                            </div>
                            <div class="input-field col s12">
                                <input name="email" id="email" type="email" class="validate">
                                <label for="email" data-error="Immettere una mail valida" 
                                       data-success="">Email</label>
                                <c:if test="${control}">
                                    <p class="red-text text-darken-1">Credenziali errate<p>
                                    </c:if>
                            </div>

                            <div class="center col l12">
                                <button class="btn btn-flat" 
                                        id="login" type="submit" name="action">
                                    Login
                                </button> 
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="parallax"><img src="<c:url value="/resources/images/bg_index.jpg"/>"></div>
    </div>
    <div class="section white">
        <!-- page content eventi -->
        <div class="row container">
            <div class="col l3">
                <a href="#!" class="trigger" id="_1">
                    <div class="section">
                        <h5>Eventi principali</h5>
                        <p>
                            Lista degli eventi principali nei dintorni della tua posizione
                        </p>
                    </div>
                </a>
                <div id="_1_1" class="divider-active"></div>
                <a href="#!" class="trigger" id="_2">
                    <div class="section">
                        <h5>Piu votati</h5>
                        <p>Lista degli eventi pi&ugrave; popolari nella tua zona</p>
                    </div>
                </a>
                <div id="_2_2" class="divider"></div>
                <a href="#!" class="trigger" id="_3">
                    <div class="section">
                        <h5>Categorie</h5>
                        <p>Lista delle categorie presenti</p>
                    </div>
                </a>
                <div id="_3_3" class="divider"></div>
            </div>
            <div class="col l8 offset-l1">
                <div class="" id="section_1">
                    <c:forEach items="${listaEventi}" var="e">
                        <div class="col l6">
                            <div class="card hoverable">
                                <div class="card-content">
                                    <span class="card-title">${e.nome}</span>
                                    <p class="">Il: ${e.dataE}</p>
                                    <p class="">Indirizzo: ${e.via_numero} - ${e.provincia}</p>
                                </div>
                                <div class="card-action center-align teal lighten-1 white-text">
                                    ${e.categoria.nome}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="hide" id="section_2">
                    <c:forEach items="${listaEventiTop}" var="eT">
                        <div class="col l6">
                            <div class="card hoverable">
                                <div class="card-content">
                                    <span class="card-title">${eT.e.nome}</span>
                                    <p class="">Il: ${eT.e.dataE}</p>
                                    <p class="">Indirizzo: ${eT.e.via_numero} - ${eT.e.provincia}</p>
                                </div>
                                <div class="card-action center-align orange lighten-1 white-text">
                                    Voto medio: ${eT.votoMedio}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="hide" id="section_3">
                    <c:forEach items="${listaCategorie}" var="c">
                        <div class="col l6">
                            <div class="card hoverable">
                                <div class="card-content">
                                    <span class="card-title">${c.nome}</span>
                                    <p>Totale eventi: ${c.eventi.size()}</p>
                                </div>
                                <div class="card-action center-align light-blue lighten-1"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="parallax-container custom">
        <div class="parallax"><img src="<c:url value="/resources/images/bg_bottom.jpg"/>"></div>
    </div>

    <!-- footer -->
    <footer class="page-footer white">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 >WebCommunity</h5>
                    <p class="grey-text text-darken-4">
                        Sviluppo di un progetto di gruppo
                    </p>
                </div>
                <div class="col l4 offset-l2 s12">
                    <h5>Autori</h5>
                    <ul>
                        <li class="grey-text text-darken-4">Link 1</li>
                        <li class="grey-text text-darken-4">Link 2</li>
                        <li class="grey-text text-darken-4">Link 3</li>
                        <li class="grey-text text-darken-4">Link 4</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyrig3ht white">
            <div class="container black-text">
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
