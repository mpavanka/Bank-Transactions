# 🏦 Bank Transactions Spring Boot Project

## 📦 Overview
This project is a Spring Boot application for managing bank transactions, including account details, transaction processing, and related operations. It demonstrates a layered architecture with controllers, services, DAOs, and models.

## ✨ Features
- 🧑‍💼 Account management
- 💸 Transaction processing
- 🌐 RESTful API endpoints
- 💾 Data persistence
- 📬 Example Postman collection for API testing

## 🖥️ Prerequisites
- ☕ Java 17 or above
- ⚡ Gradle (wrapper included)
- 🐳 Docker (optional, for containerization)

## ⚙️ Installation
1. **Clone the repository:**
   ```cmd
   git clone <your-repo-url>
   cd Bank-Transactions
   ```
2. **Build the project:**
   ```cmd
   gradlew build
   ```
   Or, if you have Gradle installed globally:
   ```cmd
   gradle build
   ```

## 🚀 Running the Application
1. **Run with Gradle:**
   ```cmd
   gradlew bootRun
   ```
   Or:
   ```cmd
   gradle bootRun
   ```
2. **Run the JAR directly:**
   ```cmd
   java -jar build\libs\spring-test-demo-0.0.1-SNAPSHOT.jar
   ```
3. **Run with Docker (optional):**
   Build the Docker image:
   ```cmd
   docker build -t bank-transactions .
   ```
   Run the container:
   ```cmd
   docker run -p 8080:8080 bank-transactions
   ```

## 🧪 API Documentation & Testing
- The API endpoints can be tested using Postman.
- A sample Postman collection is provided in `src/test/resources/postman/test`.
- To import:
  1. Open Postman.
  2. Click `Import` > `Upload Files`.
  3. Select the file from `src/test/resources/postman/test`.

## 📁 Project Structure
- `src/main/java/java_Practice/spring_test_demo/` - Main source code
- `src/main/resources/` - Application properties and SQL scripts
- `src/test/java/java_Practice/spring_test_demo/` - Unit and integration tests
- `src/test/resources/postman/` - Postman collections

## 🛠️ Useful Commands
- **Clean build:**
  ```cmd
  gradlew clean build
  ```
- **Run tests:**
  ```cmd
  gradlew test
  ```

## 📬 Contact
For any queries, please contact the project owner:

**Pavan**
