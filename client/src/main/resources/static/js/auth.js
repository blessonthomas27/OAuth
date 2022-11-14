onStart()
function onStart(){
var iframe=document.getElementById("iframe");
if(getCookie('access_token')!==null){
location.href = "http://localhost:8082/home.html";
}
}

window.addEventListener('message', function (e) {
    try {
      const refresh_token=e.data.refresh_token;
         const access_token=e.data.access_token;
         if(typeof refresh_token !== "undefined"){
          setCookie('refresh_token',refresh_token,30);
         setCookie('access_token',access_token,30);
      onStart()
         }else{
         location.href = "http://localhost:8082/auth";
         }
    }
    catch(err) {
     alert(err)
    }
});

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