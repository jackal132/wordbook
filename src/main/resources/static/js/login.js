function fn_chkLoginFrm(){
    let frm = document.forms['frm'];

    if(isEmpty(frm.id.value)){
        alert("Enter your ID");
        frm.id.focus();
        return false;
    }

    if(isEmpty(frm.password.value)){
        alert("Enter your PASSWORD");
        frm.password.focus();
        return false;
    }
}