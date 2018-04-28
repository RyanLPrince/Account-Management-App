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
    //window.alert("Hello");
    var account=new Account(document.getElementById("firstName").value,
                        document.getElementById("surname").value,
                        document.getElementById("accountNumber").value)
    
    window.alert(account.getFirstName()+" "+ account.getSurname()+" "+account.getAccountNumber())
    window.alert("Hello");
}


