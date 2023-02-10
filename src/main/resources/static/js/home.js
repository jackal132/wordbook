$(function(){
   $("ul > li").bind("click", fn_tabClick);
});

function fn_tabClick(){
    let obj = $(this);
    obj.siblings().removeClass("on");
    obj.addClass("on");

    $("#content").empty();

    let mode = "wrong";
    if( obj.text() === "Wrong"){
        mode = "wrong";
    } else{
        mode = "question";
    }

    $("#mode").val(mode);

    $.ajax({
        data: { "mode" : mode },
        type: "GET",
        url: "/api/word/question/list",
        dataType: "json",
        async: false,
        contentType: "application/json",
        success:function(data){
            let result = "";
            for(let i = 0; i < data.length; i++){
                result += "<div>";
                result += "<label for=\"answer"+(i+1)+"\">"+ (i+1) +". "+data[i].word+"</label>";
                result += "<input type=\"text\" name=\"answer\" maxlength=\"50\" value=\"\">";
                result += "<input type=\"hidden\" name=\"hiddenMeaning\" value=\""+data[i].meaning+"\">";
                result += "<input type=\"hidden\" name=\"word_pk\" value=\""+data[i].word_pk+"\">";
                result += "</div>";
            }

            $("#content").append(result);
        }
    });

}

function fn_chkWordRegisterFrm(){
    let frm = document.forms['frm1'];

    if(isEmpty(frm.word.value)){
        alert("단어를 입력해주세요.");
        frm.word.focus();
        return false;
    }

    if(isEmpty(frm.meaning.value)){
        alert("단어의 뜻을 입력해주세요.");
        frm.meaning.focus();
        return false;
    }
}

function fn_reset(){
    location.href="/reset";
}

