$(document).ready(function () {
    //per i multiple select degli artisti
    $('select').material_select();

    //per l'effetto parallax della homepage
    $('.parallax').parallax();

    //per la richiesta di eliminazione del commento
    $('.modal').modal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .5, // Opacity of modal background
        inDuration: 200, // Transition in duration
        outDuration: 200, // Transition out duration
        startingTop: '4%', // Starting top style attribute
        endingTop: '10%' // Ending top style attribute
    });

    // quando l'utente preme il SI di conferma dell'eliminazione del commento
    $('#confirm_delete_si').on('click', function () {
        // Send the data using post
        var getting = $.get("/WebCommunity/homepage/evento/eliminaRecensione");
        getting.done(function() {
            window.location.reload();
        });
    });
    
    
    // controllo la correttezza  del voto immesso del commento
    $("#btn_commenta").on('click', function(e) {
        var v = $("#voto").text();
        if(v === "Voto") {
            e.preventDefault();
            Materialize.toast("Votare l'evento", 4000);
        }
    });

    // per il login
    $('#login').on('click', function (e) {
        var nick = $('#nickname').val();
        var email = $('#email').val();
        if (nick.length === 0 || email.length === 0) {
            Materialize.toast('Inserire dei dati validi', 4000);
            e.preventDefault();
        }
    });

    // per il register
    $('#register').on('click', function (e) {
        var nick = $('#nick').val();
        var nome = $('#nome').val();
        var cognome = $('#cognome').val();
        var email = $('#email').val();
        if (nick.length === 0 || email.length === 0 || nome.length === 0 || cognome.length === 0) {
            Materialize.toast('Inserire dei dati validi', 4000);
            e.preventDefault();
        }
    });
    
    var section_active = "_1";
    // per l'index e le tre sezioni
    $('.trigger').on('click', function (e) {
        var id = $(this).attr('id');
        $('#' + section_active + section_active).removeClass('divider-active');
        $('#' + section_active + section_active).addClass('divider');

        $('#' + id + id).removeClass('divider');
        $('#' + id + id).addClass('divider-active');

        $('#section' + section_active).addClass('hide');
        $('#section' + id).removeClass('hide');

        section_active = id;
    });


    // fa venire fuori la roba per selzionare data
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    });

    //per autocomplete provincia
    $('input.autocomplete').autocomplete({
        data: {
            "Agrigento": null, "Alessandria": null, "Ancona": null, "Aosta": null, "Aquila ": null, "Arezzo": null, "Ascoli Piceno": null, "Asti": null, "Avellino": null,
            "Bari": null, "Belluno": null, "Benevento": null, "Bergamo": null, "Biella": null, "B ologna": null, "Bolzano": null, "Brescia": null, "Brindisi": null,
            "Cagliari": null, "Caltanissetta": null, "Campobasso": null, "Caserta": null, "Catania": null, "Catanzaro": null, "Chieti": null, "Como": null, "Cosenza": null, "C remona": null, "Crotone": null, "Cuneo": null,
            "Enna": null,
            "Ferrara": null, "Firenze": null, "Foggia": null, "Forlì e Cesena": null, "Frosinone": null,
            "Genova": null, "Gorizia": null, "Grosseto": null,
            "Imperia": null, "Isernia": null,
            "La Spezia": null, "Latina": null, "Lecce": null, "Lecco": null, "Livorno": null, "Lodi": null, "Lucca": null,
            "Macerata": null, "Mantova": null, "Massa-Carrara": null, "Matera": null, "Messina": null, "Milano": null, "Modena": null,
            "Napoli": null, "Novara": null, "Nuoro": null,
            "Oristano": null,
            "Padova": null, "Palermo": null, "Parma": null, "Pavia": null, "Perugia": null, "Pesa ro e Urbino": null, "Pescara": null, "Piacenza": null, "Pisa": null, "Pistoia": null, "Por denone": null, "Potenza": null, "Prato": null,
            "Ragusa": null, "Ravenna": null, "Reggio Calabria": null, "Reggio Emilia": null, "Rieti": null, "Rimini": null, "Roma": null, "Rovigo": null,
            "Salerno": null, "Sassari": null, "Savona": null, "Siena": null, "Siracusa": null, "S ondrio": null,
            "Taranto": null, "Teramo": null, "Terni": null, "Torino": null, "Trapani": null, "Trento": null, "Treviso": null, "Trieste": null,
            "Udine": null,
            "Varese": null, "Venezia": null, "Verbano-Cusio-Ossola": null, "Vercelli": null, "Verona": null, "Vibo Valentia": null, "Vicenza": null, "Viterbo": null
        },
        limit: 8, // The max amount of results that can be shown at once. Default: Infinity.
        onAutocomplete: function (val) {
            // Callback function when value is autcompleted.
        },
        minLength: 1, // The minimum length of the input for the autocomplete to start. Default: 1.
    });

    // per mostrare il voto dato ad un evento
    $("#range").on('change', function () {
        var v = $(this).val();
        var commento;
        switch(v) {
            case '0': 
                commento = "Schifoso";
                break;
            case '1': 
                commento = "Penoso";
                break;
            case '2': 
                commento = "Uhm";
                break;
            case '3':
                commento = "Carino dai";
                break;
            case '4': 
                commento = "Molto bello";
                break;
            case '5':
                commento = "Fantastico!";
                break;
            default:
                commento = "";
                break;
        }
        $("#voto").text("Voto: " + v + " - " + commento);
    });

    // per mostrare/nascondere i campi di nuovi artisti
    $("#nuoviArtisti").on('click', function (n) {
        if (this.checked)
            $('#numArtisti').removeClass('hide');
        else
            $('#numArtisti').addClass('hide');
    });
});