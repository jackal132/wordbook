// 빈값 체크
function isEmpty(value){
    if(value == "" || value == null ||
        value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)) {
        return true;
    } else {
        return false;
    }
}