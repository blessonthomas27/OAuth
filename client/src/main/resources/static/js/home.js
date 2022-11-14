onStart()
getVaildToken()

setInterval(getVaildToken,5000)

function getVaildToken(){
    fetch("http://localhost:8080/api/validate", {method:'GET',headers:{"Authorization":getCookie('access_token')} })
    .then(response =>response.json()).then((res)=>{
       if(res.status==200){
        
       }else if(res.status==401){
        console.log("token was expired")
        revokeToken();
       }else{
        delete_cookie('access_token');
        onStart()
       }
    }).catch((err)=>{alert(err);})
}

function revokeToken(){
    fetch("http://localhost:8080/api/revoke", {method:'GET',headers:{"Authorization":getCookie('refresh_token')} })
    .then(response =>response.json()).then((res)=>{
        const accesstoken =res.access_token
       if(typeof accesstoken !== "undefined"){
        localStorage.setItem('access_token', accesstoken);
        setCookie('access_token',accesstoken,30);
       }else{
        delete_cookie('access_token');
        onStart()
       }
    }).catch((err)=>{alert(err);})
}

function onStart(){
if(getCookie('access_token')===null){
location.href = "http://localhost:8082/auth";
}
}
function delete_cookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
  }

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function moveTo(){
location.href = "http://localhost:8082/course.html";
}

function logout(){
    delete_cookie('access_token');
    delete_cookie('refresh_token');
    alert("you are logging out !!");
    onStart()
}
