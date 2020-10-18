# Project Organizer API - Anthonny Pazmiño
Micronaut + PostgreSQL + Maven

# CRUD
## Get All Tasks from a project
```
GET http://localhost:7777/task/1
```
## Insert Task
```
POST http://localhost:7777/task "Content-Type: application/json" -d '{"name": "Task name", "description": "Description task", "projectId": 3}'
```
## Update Task
```
PUT http://localhost:7777/task "Content-Type: application/json" -d '{"name": "Task name", "description": "Description task", "id": 1}'
```
## Delete Task
```
DELETE http://localhost:7777/task/1
```
