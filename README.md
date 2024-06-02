# Spring Boot Firebase CRUD API Demo

A demo project showcasing the integration of Firebase with Spring Boot to build a CRUD API for user management.

## Installation

1. Clone the repository: `git clone https://github.com/shubhalina94/springboot-firebase-crud-api.git`
2. Navigate to the project directory: `cd springboot-firebase-crud-api`
3. Install dependencies: `mvn install`
4. Run the application: `mvn spring-boot:run`

## Usage

### API Endpoints

- Create User: `POST /users`
  - Request Body: JSON object with user data (id, name, subjectMarks)
  - Response: Success message with user ID
- Get User: `GET /users/{id}`
  - Response: JSON object with user data
- Update User: `PUT /users`
  - Request Body: JSON object with updated user data
  - Response: Success message with user ID
- Delete User: `DELETE /users/{id}`
  - Response: Success message with user ID
- Get All Users: `GET /users`
  - Response: JSON array with user data for all users

## Configuration

- Firebase Service Account: Place your Firebase service account JSON file in the `src/main/resources` directory and update the configuration accordingly.

