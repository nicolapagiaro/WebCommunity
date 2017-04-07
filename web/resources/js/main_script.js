$(document).ready(function(){
    $('.parallax').parallax();
    
    var section_active = "_1";
    
    // per il login
    $('#login').on('click', function(e) {
        var nick = $('#nickname').val();
        var email = $('#email').val();
        if(nick.length === 0 || email.length === 0) {
            Materialize.toast('Inserire dei dati validi', 4000);
            e.preventDefault();
        }
    });
    
     // per il register
    $('#register').on('click', function(e) {
        var nick = $('#nick').val();
        var nome = $('#nome').val();
        var cognome = $('#cognome').val();
        var email = $('#email').val();
        if(nick.length === 0 || email.length === 0 || nome.length === 0 || cognome.length === 0) {
            Materialize.toast('Inserire dei dati validi', 4000);
            e.preventDefault();
        }
    });
    
    // per l'index e le tre sezioni
    $('.trigger').on('click', function(e) {
        var id = $(this).attr('id');
        $('#' + section_active + section_active).removeClass('divider-active');
        $('#' + section_active + section_active).addClass('divider');
        
        $('#' + id + id).removeClass('divider');
        $('#' + id + id).addClass('divider-active');
        
        $('#section' + section_active).addClass('hide');
        $('#section' + id).removeClass('hide');
        
        section_active = id;
    });
});