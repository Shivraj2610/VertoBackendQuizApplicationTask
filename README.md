# 🎯 VertoBackendQuizApplicationTask

This is a **Quiz Application API** built using **Spring Boot**.

The project provides REST APIs for managing quizzes and questions — including creating, updating, deleting, and submitting quizzes, as well as calculating scores.

---

## 🧠 Tech Stack

| Category      | Technology                |
| ------------- | ------------------------- |
| **Framework** | Spring Boot               |
| **Language**  | Java                      |
| **Database**  | MySQL                     |
| **ORM**       | Spring Data JPA           |
| **Testing**   | JUnit 5, Mockito, Postman |

---

## ⚙️ How to Set Up

1. **Clone the Repository**

   ```bash
   git clone https://github.com/Shivraj2610/VertoBackendQuizApplicationTask.git
   ```

2. **Open in IDE**

   * Import the project into **IntelliJ IDEA** or **Eclipse**.
   * Make sure **Lombok** plugin is installed and enabled.

3. **Configure the Database**

   * Navigate to `src/main/resources/application.properties`
   * Update the following configuration based on your MySQL setup:

     ```properties
     # MySQL Database Configuration
     spring.datasource.url=jdbc:mysql://localhost:[Port-Number]/[DatabaseName]
     spring.datasource.username=[username]
     spring.datasource.password=[password]
     ```

4. **Run the Application**

   * Run the project as a **Spring Boot Application**.
   * Default port: `8080`
   * To change the port, add this line to `application.properties`:

     ```properties
     server.port=[your-port-number]
     ```

---

## 🧩 Features

### 🎓 Quiz Management

1. Create a new Quiz
2. Update an existing Quiz
3. Get a single Quiz by ID
4. Delete a Quiz
5. Get all available Quizzes (with pagination and sorting)
6. Add single or multiple questions to a Quiz
7. Update a Question
8. Delete a Question
9. Submit a Quiz and Get the Score

---

## 🚀 Advanced Features

* Exactly **one correct answer** per question
* Automatic **UUID generation** for unique identifiers
* **Comprehensive error handling** and validation
* Prevent adding duplicate questions in a quiz
* Clean **RESTful API design** with proper HTTP status codes

---

## 📮 API Endpoints

| Method     | Endpoint                                       | Description                       |
| ---------- | ---------------------------------------------- | --------------------------------- |
| **POST**   | `/quiz`                                        | Create a new Quiz                 |
| **PUT**    | `/quiz/{quizId}`                               | Update Quiz                       |
| **GET**    | `/quiz/{quizId}`                               | Get Quiz by ID                    |
| **DELETE** | `/quiz/{quizId}`                               | Delete Quiz                       |
| **GET**    | `/quiz?pageNumber=&pageSize=&sortBy=&sortDir=` | Get all Quizzes (with pagination) |
| **GET**    | `/quiz/questions/{quizId}`                     | Get Quiz Questions                |
| **POST**   | `/quiz/question/{quizId}`                      | Add Single Question               |
| **POST**   | `/quiz/question/multiple/{quizId}`             | Add Multiple Questions            |
| **PUT**    | `/quiz/{quizId}/{questionId}`                  | Update Question                   |
| **DELETE** | `/quiz/{quizId}/{questionId}`                  | Delete Question                   |
| **GET**    | `/quiz/score`                                  | Submit Quiz and Get Score         |

---

## 🧾 Example Requests

### ➕ Create Quiz

**POST** `/quiz`

```json
{
  "title": "Cricket Quiz"
}
```

---

### ✏️ Update Quiz

**PUT** `/quiz/{quizId}`

```json
{
  "title": "Indian GK"
}
```

---

### 📋 Get All Quizzes

**GET** `/quiz?pageNumber=0&pageSize=5&sortBy=title&sortDir=asc`

---

### 📘 Get Quiz Questions

**GET** `/quiz/questions/{quizId}`

---

### 🧩 Add Single Question

**POST** `/quiz/question/{quizId}`

```json
{
  "questionText": "National Animal of India",
  "options": [
    { "optionText": "Rahul Gandhi", "correct": false },
    { "optionText": "Narendra Modi", "correct": true },
    { "optionText": "Sharad Pawar", "correct": false },
    { "optionText": "Yogi", "correct": false }
  ]
}
```

---

### 🧠 Add Multiple Questions

**POST** `/quiz/question/multiple/{quizId}`

```json
[
  {
    "questionText": "Capital of India",
    "options": [
      { "optionText": "Mumbai", "correct": false },
      { "optionText": "Delhi", "correct": true },
      { "optionText": "Kolkata", "correct": false },
      { "optionText": "Pune", "correct": false }
    ]
  },
  {
    "questionText": "National Bird of India",
    "options": [
      { "optionText": "Crow", "correct": false },
      { "optionText": "Peacock", "correct": true },
      { "optionText": "Parrot", "correct": false },
      { "optionText": "Sparrow", "correct": false }
    ]
  }
]
```

---

### 🧮 Submit Quiz and Get Score

**GET** `/quiz/score`

```json
{
  "quizId": "c83afdd9-863e-40a3-b286-6518146e7325",
  "answers": {
    "b52290c1-05b2-4e44-a229-5e8f7d67b1a1": 11,
    "2a965953-c8a7-4256-b202-cece15b26906": 10
  }
}
```

---

## 🧪 Testing

* Use **Postman** to test all API endpoints.
* Run **JUnit 5** and **Mockito** test cases for unit testing.

---

## 🧔 Author

**👨‍💻 Shivraj Nalavade**
💼 Full Stack Java Developer
📍 Sangli, India
🔗 [GitHub Profile](https://github.com/Shivraj2610)

