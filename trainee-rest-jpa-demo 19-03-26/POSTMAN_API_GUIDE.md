# Trainee REST API Guide

Base URL: `http://localhost:8086/api/trainees`

## 1. List all trainees

`GET /api/trainees`

## 2. List trainee by name

`GET /api/trainees/name/asha`

Alternative:

`GET /api/trainees?traineeName=asha`

## 3. List trainee by id

`GET /api/trainees/101`

## 4. Insert trainee

`POST /api/trainees`

```json
{
  "traineeId": 104,
  "traineeName": "Kiran Patel",
  "traineeDomain": "React",
  "traineeLocation": "Chennai"
}
```

## 5. Update trainee

`PUT /api/trainees/104`

```json
{
  "traineeId": 104,
  "traineeName": "Kiran Patel",
  "traineeDomain": "Spring Boot",
  "traineeLocation": "Chennai"
}
```

## 6. Delete trainee by id

`DELETE /api/trainees/104`
