// Sign in and Register Modals
function signin(){
    if($('#register').is(':visible')){
        $('#register').modal('hide')
    }
    $('#signin').modal('show')
}
function register(){
    if($('#signin').is(':visible')){
        $('#signin').modal('hide')
    }
    $('#register').modal('show')
}

// Stall Modal
function stallmodal(){
    $('#stallmodal').modal('show')
}
function closeModal(){
    $('#stallmodal').modal('hide')
}