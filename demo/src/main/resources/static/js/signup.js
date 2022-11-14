

var register_button =document.getElementById("register_button");


register_button.onclick = function(){
var name=document.getElementById("name").value;
var email=document.getElementById("email").value;
var password=document.getElementById("password").value;
var re_password=document.getElementById("re_password").value;

if(name && email && password && re_password){
if(password===re_password){
  if(validateEmail(email)){
    var data={
    "emailId":email,
    "password":password,
    "name":name
    }
   fetch("http://localhost:8080/api/signup",{method:'POST',headers:{
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
  alert("Enter the valid emailId")
  document.getElementById("email").value="";
  }
}else{
alert("password does not match");
document.getElementById("password").value="";
document.getElementById("re_password").value="";
}}else{
alert("Please fill all the fields");
}

}


const validateEmail = (email) => {
  return String(email)
    .toLowerCase()
    .match(
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );

};
