# Learning_Progress_Tracker
The command-line interface application that keeps track of the registered users, their learning progress, and metrics. 
It will also provide detailed information about each user or any category of users and the overall statistics 
for the entire learning platform.

Functionalities:
1. ADD STUDENTS;
Read user credentials and verify whether they are correct or not. Every user must provide the following credentials: 
their first and last name and a valid email address.
* First and last name ->  Accept only ASCII characters, from A to Z and from a to z as well as hyphens - and apostrophes '.
A name may contain one or more hyphens and/or apostrophes, but don't allow them as the first or the last character of
any part of the name. Also, these characters cannot be adjacent to each other. The first name and the last name must 
be at least two characters long.
* Email -> defined in RFC standards and is very complicated. In reality, email addresses are usually verified by sending
a verification letter to which the user is required to reply and confirm the validity of the address. So in general, 
it is feasible just to check whether the provided string looks like an email address. It should contain the name part, 
the @ symbol, and the domain part.

2. LIST;
Print the Students: a header followed by the student IDs. The students must be listed in the order they 
were added. Remember, each ID must be unique. If there are no students to list, print No students found.

3. ADD POINTS;
Read learning progress data in the following format: studentId number number number number. The numbers correspond to 
the courses (Java, DSA, Databases, Spring). Number is a non-negative integer number. If there is no student with the 
specified ID, the program print "No student is found for id=%s". where %s is the invalid ID. Also, if any of the numbers
are missing, or there is an extra number or any of the numbers do not meet the requirements mentioned above, 
the program print "Incorrect points format". If the learning progress data is entered in the correct format, and 
the specified user exists, the program update the student's record and print "Points updated". Once back is entered,
the program stop reading learning progress data.

4. FIND; 
Print details of the student with the specified ID in this format: id points: Java=%d; DSA=%d; Databases=%d; Spring=%d
where %d is the respective number of points earned by the student. If the ID cannot be found, print the error message: 
"No student is found for id=%s". where %s is the invalid ID.

5. STATISTICS;
Displays the details about any course.

6. NOTIFY;
Print a list of messages to students in the format above. Below the list, print the total number of students that 
should be notified, for example: Total 5 students have been notified.
The personal information of the students, such as an email address and the full name, as well as the name of 
the relevant course, if applicable.