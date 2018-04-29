-Account Number is primary key
	therefore it cannot be null
	therefore generate account number is currently redundant 
	users must give a valid account number
	create id field as primary key?
-Get all accounts returns null if no accounts exist
	return a JSON message instead
-Bean validation

currently have two home pages, 
1. http://localhost:8080/Application-Management-App/home (from servlet annotation)
2. http://localhost:8080/Application-Management-App/index.xhtml (from index.xhtml/web.xml)