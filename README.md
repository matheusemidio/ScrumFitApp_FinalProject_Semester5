# Scrum Fit Application 
![Project Design]( ScrumFitApp/app/src/main/res/drawable/logo.png
)
## Project Description
### Motivation
This application was developed for the final project of the Android Mobile course, on the fifth semester of the Computer Science Technology DEC program. It was presented in December of 2021. 
### About the application
The idea of the application is to allow logged users to create a routine of exercises for the desired number of days and then allow them to view which exercises where chosen for each day. The exercises are taken from our database. 
### Technologies used
-	Firebase
We initially thought of using a relational database, but since we only had less than a month to develop it, we decided to go with a non-relational database, so we chose Firebase. In that context, we created the documents to simulate a relational interaction, like it was needed for our project.
-	Trello
To organize the tasks, we decided to use Trello, since all the members were already familiar with the way it worked.
-	Android Studio
-	Visual Studio
We used Visual Studio code to better visualize the returned JSONs that came from the database response. 

### Timeline
1.	Worked on the necessary user stories and acceptance criteria. 
2.	Work on the database design
3.	Work on building all the views of the application
4.	Worked on the necessary Android resource files that would be used on the entire application
5.	Worked on the backend of the views
6.	Externalized the strings to make the application multilanguage. 
### Challenges
-	GitHub smooth collaboration
The first challenge we had was to collaborate on GitHub during the development of this project. Since it was not ideal to use knowledge outside of the course content, we could not modularize the project for better organization. Due to this, many important files were grouped together and, since all the members had to edit those files, it would create a lot of conflicts. Since we only had less than a month to develop it, we decided to share our progress “manually”, having at total of 13 versions for now. 
-	HashMap response from the Database
Another great challenge in this project was having to build a big chain of nested objects on the Firebase, which modified the way we received those calls from the database. The first feature that was developed was the “Create Routine”. On the development of the “List Routine” feature, the response of the database returned a HashMap. With that in mind, it was necessary for the object to extend from the HashMap class, and this modified the behavior of the “Create Routine” feature. At the time, since the deadline was very close, the solution taken was to create two classes, one to be used on each feature.
### Future features
-	Create a database with the target muscles in French as well.
-	Allow the user to track his progression of sets, reps and weight per exercise
-	Allow the user to create multiple routines
-	Include more exercises on the database
-	Implement the progression bar on the home page to allow the user to see how many workouts he already completed
-	Implement validation for the registration of the user
	
	
## Key Features and Demo
### Register
The user can create a new account.
<br/>
<img src="https://user-images.githubusercontent.com/62715352/149253502-ae628c01-f4c1-423e-a3a5-398e6475bbce.gif" width="200" height="400" />

### Login
The user can enter his username and password, if they match with the ones on the database, the user successfully logins on the application.
<br/>
<img src="https://user-images.githubusercontent.com/62715352/149253497-8f50b006-d23c-4853-a692-bb20f6345898.gif" width="200" height="400" />

### Home Page
Here the idea was to have a progress bar that would show how many workouts he already completed and what is the next workout that he should complete (if any). Since this was not a priority at the moment, we could not deliver before the deadline, but it will be done in a near future.

### Profile
In this screen, the user can update his personal information.
<br/>
<img src="https://user-images.githubusercontent.com/62715352/149253396-9adddae0-1519-4b75-9e30-d5daa1996a81.gif" width="200" height="400" />

### Create Routine
In this feature, the user can select the number of days that he wants to workout and choose the exercises for each day. As said before, the exercises are taken from our database.
<br/>
<img src="https://user-images.githubusercontent.com/62715352/149259936-5e02089f-dca3-463a-9df9-6d7a549b123c.gif" width="200" height="400" />
<img src="https://user-images.githubusercontent.com/62715352/149259944-7c31af1b-386e-465e-8d61-464cc8929cee.gif" width="200" height="400" />

### List Routine
At this spot, the user can see his workouts. It will show a screen with the number of days he chose and, if he clicks on a day, he can see the workouts that it contains. Here, the user will also see the target muscles that every day of workout is targeting. This is taken from the exercises that it contains, since on the database, every exercise has a target muscle(s). We use a set of the target muscles in every day of workout to avoid repetitive information. 
<br/>
<img src="https://user-images.githubusercontent.com/62715352/149253394-b274afac-0d07-472d-a173-113b717be7c5.gif" width="200" height="400" />

### French
All the strings were externalized in order to make the application multilanguage.
<br/>
<img src="https://user-images.githubusercontent.com/62715352/149253389-d70ef516-6e80-4b84-a63b-99fff562cafe.gif" width="200" height="400" />

## Summary of versions
<img src="https://user-images.githubusercontent.com/62715352/149411383-0eaa19e4-6c9a-4855-91d3-52c65b29081b.png" width="400" height="400" />

## Group members
1.	Andres Eduardo Ardila
2.	Camilo Andres Restrepo
3.	Matheus Emidio de Melo Cadena
4.	Zahra Soltani
