class Account{
    constructor(firstName,surname,accountNumber){
        this.firstName=firstName;
        this.surname=surname;
        this.accountNumber=accountNumber;
        this.generateAccountNumber=false;
    }
    
    getFirstName(){
        return this.firstName;
    }
    getSurname(){
        return this.surname;
    }
    getAccountNumber(){
        return this.accountNumber;
    }
    
}
 

function createAccount(){
    var account=new Account(document.getElementById("firstName").value,
                        document.getElementById("surname").value,
                        document.getElementById("accountNumber").value)
    
    var accountAsJSON=JSON.stringify(account)
    window.alert(accountAsJSON);
    sendRequest(accountAsJSON,account.getAccountNumber());
   
}

function sendRequest(accountAsJSON){
    var xhr=new XMLHttpRequest();
    xhr.open('POST',"http://localhost:8080/Application-Management-App/api/account/json/",true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(accountAsJSON);
    
    xhr.addEventListener("readystatechange", processRequest, false);
    xhr.onreadystatechange = processRequest;
 
    
   
   
}

 function processRequest(e) {   
     window.alert(xhr.status+"");
     if (xhr.readyState == 4 && xhr.status == 200) {
       //var response = JSON.parse(xhr.responseText);//redundant?
        window.alert(responseText);
    }
        
    }