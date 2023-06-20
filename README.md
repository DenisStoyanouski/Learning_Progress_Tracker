# Learning_Progress_Tracker
The command-line interface application that keeps track of the registered users, their learning progress, and metrics. 
It will also provide detailed information about each user or any category of users and the overall statistics 
for the entire learning platform.

Functionalities:
1. Add students;
The user credentials and verify whether they are correct or not. Every user must provide the following credentials: 
their first and last name and a valid email address.
* First and last name ->  Accept only ASCII characters, from A to Z and from a to z as well as hyphens - and apostrophes '.
A name may contain one or more hyphens and/or apostrophes, but don't allow them as the first or the last character of
any part of the name. Also, these characters cannot be adjacent to each other. The first name and the last name must 
be at least two characters long.
* Email -> defined in RFC standards and is very complicated. In reality, email addresses are usually verified by sending
a verification letter to which the user is required to reply and confirm the validity of the address. So in general, 
it is feasible just to check whether the provided string looks like an email address. It should contain the name part, 
the @ symbol, and the domain part.

2. 


