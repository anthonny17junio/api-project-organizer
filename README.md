# Project Organizer API - Anthonny Pazmiño
Micronaut + PostgreSQL + Maven

# CRUD
## Get All Tasks from a project
```
curl http://localhost:7777/task/1
```
## Insert Task
```
curl -XPOST http://localhost:7777/task -H "Content-Type: application/json" -d '{"name": "Task name", "description": "Description task", "projectId": 3}'
```
## Delete Task
```
curl -XDELETE http://localhost:7777/task/1
```
