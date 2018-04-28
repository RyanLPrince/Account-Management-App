-Account Number is primary key
	therefore it cannot be null
	therefore generate account number is currently redundant 
	users must give a valid account number
	create id field as primary key?
-Get all accounts returns null if no accounts exist
	return a JSON message instead
-Bean validation