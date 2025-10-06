# VertoBackendQuizApplicationTask
This is the Quiz Application API's made in Spring Boot

In this project I create the REST API's for for the Online Quiz Application. I create the API's for Create Quiz, Update Quiz, Get Single Quiz, Get All Available Quiz, Add Single and Multile Questions in Quiz, Update the Question, Delete Question, Delete Quiz and Submit Quiz and Get the Score


###Tech Stack:
   #Framework# -> Spring Boot
   #Language# -> Java
   #Database# -> MySQL
   #ORM# -> Spring Data JPA
   #Testing# -> JUnit 5, Mockito, Postman


#########    How To Set Up   #############
1. Clone this Repository => git clone https://github.com/Shivraj2610/VertoBackendQuizApplicationTask.git
2. After Cloning the Repository the entire Project will cloned in your folder
3. Install the plugin for the Lambok from your idea like Intellij, Eclips etc (I user IntelliJ)
4. Data base Configuration:
       You want to Update the Database Configuration as per your MySQL Setup
       --> Go to the src/main/resources/application.properties and Update the following Configuration
       --> #MySQL Database Configuration
            spring.datasource.url=jdbc:mysql://localhost:[Port-Number]/[DatabaseName]
            spring.datasource.username=[username]
            spring.datasource.password=[Password]



## Features : -
    **Quiz Management -> 
        1. Create new Quiz
        2. Update Existing Quiz
        3. Get Single Quiz by quizId
        4. Delete Existing Quiz
        5. Get Quiz Questions
        6. Update Quiz Questins
        7. Delete Quiz Question
        8. Add Single Question
        9. Add Multiple Questions
        10. Submit Quiz and Get Answers
        11. Get All Quiz in Page Formate

## Advanced Features
    1. exactly one correct answer per question
    2. Automatic UUID generation for unique identifiers
    3. Comprehensive error handling and validation
    4. Check the Question is exist in quiz before add If exist then do not add give error Message
    5.Clean RESTful API design with proper HTTP status codes




####################### Test the Bellow Api's using Postman  ################################

The Default port number is = 8080
If you want to change the Port number then add this line into src/main/resources/application.properties -> server.port= [Your port-number]


Method      Endpoint                                        Description

POST        `/quiz`                                         Create Quiz
json request -> {
                    "title":"Cricket Quiz"
                } 


PUT         `/quiz/{quizId}`                                Update Quiz
json request -> {
                    "title":"Indian GK"
                }


GET         `/quiz/{quizId}`                                Get Single Quiz by quizId


GET         `//quiz?pageNumber=[page-number]`               Get All Quiz with Page
            `&pageSize=[page-size]`
            `&sortBy=[title/id/etc.]`
            `$sortDir=[asc/desc]`
            

GET         `/quiz/questions/[quizId]`                      Get Quiz Questions


PUT         `/quiz/[quizId]/[questionId]`                    Update Quiz Question
json request -> {
                  "questionText": "Sub-Capital of Maharashtra",
                      "options": [
                        {
                              "optionText": "Mumbai",
                              "correct":false
                          },
                          {
                              "optionText": "Nagpur",
                              "correct":true
                          },
                          {
                              "optionText": "Sangli",
                              "correct":false
                          },
                          {
                              "optionText": "Satara",
                              "correct":false
                          }
                      ]
                  }


DELETE      `/quiz/[quizId]/[questionId]`                    Remove Question from Quiz


DELETE      `/quiz/[quizId]`                                 Remove Quiz


GET         `/quiz/score`                                    Submit Quiz and Get Score
json request -> {
                  "quizId":"c83afdd9-863e-40a3-b286-6518146e7325",
                  "answers":{
                      "b52290c1-05b2-4e44-a229-5e8f7d67b1a1":11,
                      "2a965953-c8a7-4256-b202-cece15b26906":10
                  }
              }



POST          `/quiz/question/[quizId]`                  Add Single Question
json request -> {
                  "questionText":"National Animal of India",
                  "options":[
                      {
                          "optionText":"Rahul Gandi",
                          "correct":false
                      },
                      {
                          "optionText":"Narendra Modi",
                          "correct":true
                      },
                      {
                          "optionText":"Sharad Pawar",
                          "correct":false
                      },
                      {
                          "optionText":"Yogi",
                          "correct":false
                      }
                  ]
              }


POST            `/quiz/question/multiple/[quizId]`            Add Multiple Questions
json request -> 




