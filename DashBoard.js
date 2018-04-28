var Account={
    firstName:"",
    surname:"",
    accountNumber:0,
    generateAccountNumber:false,
}

function createAccount(){
    Account.firstName=document.getElementById("firstName").value;
    Account.surname=document.getElementById("surname").value;
    Account.accountNumber=document.getElementById("accountNumber").value;
    
    window.alert(Account.firstName+" "+ Account.surname+" "+Account.accountNumber)
}