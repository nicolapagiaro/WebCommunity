$(document).ready(function(){
    $('.parallax').parallax();
    $('#login').on('click', function(e) {
        var nick = $('#nickname').val();
        var email = $('#email').val();
        if(nick.length === 0 || email.length === 0) {
            Materialize.toast('Inserire dei dati validi', 4000);
            e.preventDefault();
        }
    });
    $('#register').on('click', function(e) {
        var nick = $('#nick').val();
        var nome = $('#nome').val();
        var cognome = $('#cognome').val();
        var email = $('#email').val();
        if(nick.length === 0 || email.length === 0 || nome.length === 0 || cognome.length === 0) {
            Materialize.toast('Inserire dei dati validi', 4000);
            e.preventDefault();
        }
    })
});