var login_button =document.getElementById("login");

login_button.onclick = function(){
var emailAddress=document.getElementById("email").value;
var password=document.getElementById("password").value;
if(validateEmail(emailAddress) && password!==""){
var data={
"emailId":emailAddress,
"password":password
}

fetch("http://localhost:8080/api/login",{method:'POST',headers:{
   'Content-Type':'application/json'
},
body:JSON.stringify(data)
}).then(res=>{
    return res.json();
}).then(data=>{
crossDomain(data)
}).catch(err=>{
alert(err)
})

}else{
alert("Enter a valid email and password")
}
};

const validateEmail = (email) => {
  return String(email)
    .toLowerCase()
    .match(
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );

};



