$(".modifyBtn").click(function(){
    let obj = $(this);
    console.log(obj);
});

function fn_chkSearchFrm(){

    let frm = document.forms['frm'];

    if(isEmpty(frm.searchField.value)){
        alert("Choose SELECT");
        frm.searchField.focus();
        return false;
    }

    if(isEmpty(frm.keyword.value)){
        alert("Enter keyowrd");
        frm.keyword.focus();
        return false;
    }
}

function fn_pageMove(page){
    let frm1 = document.forms['frm1'];
    frm1.pageNum.value = page;
    frm1.submit();
}

function fn_modify(pk,idx){

    let form2 = document.createElement("form");
    form2.setAttribute("id", "frm2");
    form2.setAttribute("charset", "UTF-8");
    form2.setAttribute("method", "Post");
    form2.setAttribute("action", "/word/modify");

    let hiddenField1 = document.createElement("input");
    hiddenField1.setAttribute("type", "hidden");
    hiddenField1.setAttribute("name", "word");
    hiddenField1.setAttribute("value", $("#word"+idx).val());

    let hiddenField2 = document.createElement("input");
    hiddenField2.setAttribute("type", "hidden");
    hiddenField2.setAttribute("name", "meaning");
    hiddenField2.setAttribute("value", $("#meaning"+idx).val());

    let hiddenField3 = document.createElement("input");
    hiddenField3.setAttribute("type", "hidden");
    hiddenField3.setAttribute("name", "word_pk");
    hiddenField3.setAttribute("value", pk);

    form2.appendChild(hiddenField1);
    form2.appendChild(hiddenField2);
    form2.appendChild(hiddenField3);

    document.body.appendChild(form2);
    form2.submit();
}
