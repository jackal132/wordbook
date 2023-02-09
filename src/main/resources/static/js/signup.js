function fn_chkSignUpFrm() {
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

    if(isEmpty(frm.confirm.value)){
        alert("Enter your PASSWORD CONFIRM");
        frm.confirm.focus();
        return false;
    }

    if(frm.password.value != frm.confirm.value){
        alert("PASSWORDS ARE NOT THE SAME");
        frm.confirm.focus();
        return false;
    }

    if(isEmpty(frm.name.value)){
        alert("Enter your NAME ");
        frm.name.focus();
        return false;
    }

    if(frm.dupChk.value === ""){
        alert("Check Duplicate User ID");
        frm.id.focus();
        return false;
    }
}

function fn_dupInit(){
    document.forms['frm'].dupChk.value = "";
}

function fn_chkDupId(){
    let frm = document.forms['frm'];
    let chkId = frm.id.value.trim();

    frm.dupChk.value = "";

    if(isEmpty(chkId)){
        alert("Enter your ID");
        frm.id.focus();
        return false;
    }

    $.ajax({
        data: { "chkId" : chkId},
        type: "GET",
        url: "/api/member/chkId",
        dataType: "json",
        async: false,
        contentType: "application/json",
        success:function(data){
            if(data == 0) {
                alert("사용가능한 아이디 입니다.");
                frm.dupChk.value = "0";
            } else {
                alert("아이디가 이미 존재합니다.");
                frm.id.value = "";
                frm.id.focus();
            }
        }
    });
}