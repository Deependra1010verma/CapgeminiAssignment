# Spring Boot Employee REST API - Postman Guide

## Application Details
- **Base URL**: `http://localhost:8085`
- **API Base Path**: `/api/employees`
- **Port**: 8085
- **Version**: 0.0.1-SNAPSHOT

## How to Start the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8085`

---

## API Endpoints

### 1. GET All Employees
**Endpoint**: `GET /api/employees`
- **URL**: `http://localhost:8085/api/employees`
- **Description**: Retrieve list of all employees
- **Request Method**: GET
- **Response**: List of Employee objects with status 200 OK

**Example cURL**:
```bash
curl -X GET "http://localhost:8085/api/employees" -H "Content-Type: application/json"
```

**Sample Response**:
```json
[
  {
    "empid": 1001,
    "nameString": "Rajesh Kumar",
    "dobDate": "1990-05-15",
    "salary": 50000
  },
  {
    "empid": 1002,
    "nameString": "Priya Singh",
    "dobDate": "1992-08-22",
    "salary": 55000
  }
]
```

---

### 2. GET Employee by ID
**Endpoint**: `GET /api/employees/{empid}`
- **URL**: `http://localhost:8085/api/employees/1001`
- **Description**: Retrieve a specific employee by ID
- **Request Method**: GET
- **Response**: Single Employee object with status 200 OK or 404 Not Found

**Example cURL**:
```bash
curl -X GET "http://localhost:8085/api/employees/1001" -H "Content-Type: application/json"
```

**Sample Response**:
```json
{
  "empid": 1001,
  "nameString": "Rajesh Kumar",
  "dobDate": "1990-05-15",
  "salary": 50000
}
```

---

### 3. CREATE New Employee
**Endpoint**: `POST /api/employees`
- **URL**: `http://localhost:8085/api/employees`
- **Description**: Create a new employee
- **Request Method**: POST
- **Request Headers**: `Content-Type: application/json`
- **Response**: New Employee object with status 201 Created

**Example cURL**:
```bash
curl -X POST "http://localhost:8085/api/employees" \
  -H "Content-Type: application/json" \
  -d '{
    "nameString": "Amit Sharma",
    "dobDate": "1998-03-20",
    "salary": 60000
  }'
```

**Request Body**:
```json
{
  "nameString": "John Doe",
  "dobDate": "1995-06-15",
  "salary": 60000
}
```

**Sample Response**:
```json
{
  "empid": 1003,
  "nameString": "John Doe",
  "dobDate": "1995-06-15",
  "salary": 60000
}
```

**Note**: The `empid` is auto-generated. You don't need to provide it.

---

### 4. UPDATE Existing Employee
**Endpoint**: `PUT /api/employees/{empid}`
- **URL**: `http://localhost:8085/api/employees/1001`
- **Description**: Update an existing employee
- **Request Method**: PUT
- **Request Headers**: `Content-Type: application/json`
- **Response**: Updated Employee object with status 200 OK or 404 Not Found

**Example cURL**:
```bash
curl -X PUT "http://localhost:8085/api/employees/1001" \
  -H "Content-Type: application/json" \
  -d '{
    "empid": 1001,
    "nameString": "Rajesh Kumar Updated",
    "dobDate": "1990-05-15",
    "salary": 65000
  }'
```

**Request Body**:
```json
{
  "empid": 1001,
  "nameString": "Updated Name",
  "dobDate": "1990-05-15",
  "salary": 65000
}
```

**Sample Response**:
```json
{
  "empid": 1001,
  "nameString": "Updated Name",
  "dobDate": "1990-05-15",
  "salary": 65000
}
```

---

### 5. DELETE Employee
**Endpoint**: `DELETE /api/employees/{empid}`
- **URL**: `http://localhost:8085/api/employees/1001`
- **Description**: Delete an employee by ID
- **Request Method**: DELETE
- **Response**: Success message with status 200 OK or 404 Not Found

**Example cURL**:
```bash
curl -X DELETE "http://localhost:8085/api/employees/1001" -H "Content-Type: application/json"
```

**Sample Response**:
```json
"Employee with ID 1001 deleted successfully"
```

---

### 6. GET Demo Employee (Sample Endpoint)
**Endpoint**: `GET /api/employees/demo/sample`
- **URL**: `http://localhost:8085/api/employees/demo/sample`
- **Description**: Get a sample employee for testing
- **Request Method**: GET
- **Response**: Sample Employee object with status 200 OK

**Example cURL**:
```bash
curl -X GET "http://localhost:8085/api/employees/demo/sample" -H "Content-Type: application/json"
```

**Sample Response**:
```json
{
  "empid": 9999,
  "nameString": "Demo User",
  "dobDate": "1990-01-01",
  "salary": 50000
}
```

---

## Postman Collection JSON

You can import this collection directly into Postman:

```json
{
  "info": {
    "name": "Employee REST API",
    "description": "Spring Boot Employee Management REST API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Get All Employees",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/api/employees",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8085",
          "path": ["api", "employees"]
        }
      }
    },
    {
      "name": "Get Employee by ID",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/api/employees/1001",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8085",
          "path": ["api", "employees", "1001"]
        }
      }
    },
    {
      "name": "Create New Employee",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"nameString\": \"John Doe\", \"dobDate\": \"1995-06-15\", \"salary\": 60000}"
        },
        "url": {
          "raw": "http://localhost:8085/api/employees",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8085",
          "path": ["api", "employees"]
        }
      }
    },
    {
      "name": "Update Employee",
      "request": {
        "method": "PUT",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"empid\": 1001, \"nameString\": \"Updated Name\", \"dobDate\": \"1990-05-15\", \"salary\": 65000}"
        },
        "url": {
          "raw": "http://localhost:8085/api/employees/1001",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8085",
          "path": ["api", "employees", "1001"]
        }
      }
    },
    {
      "name": "Delete Employee",
      "request": {
        "method": "DELETE",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/api/employees/1001",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8085",
          "path": ["api", "employees", "1001"]
        }
      }
    }
  ]
}
```

---

## Testing Steps in Postman

1. **Open Postman** and start a new request
2. **Set the request method** (GET, POST, PUT, DELETE)
3. **Enter the URL** from the endpoints above
4. **For POST/PUT requests**:
   - Go to the **Body** tab
   - Select **raw**
   - Select **JSON** from the dropdown
   - Paste the JSON request body
5. **Click Send** to execute the request
6. **View the response** at the bottom

---

## Initial Sample Data

When the application starts, it comes with two pre-loaded employees:

```json
[
  {
    "empid": 1001,
    "nameString": "Rajesh Kumar",
    "dobDate": "1990-05-15",
    "salary": 50000
  },
  {
    "empid": 1002,
    "nameString": "Priya Singh",
    "dobDate": "1992-08-22",
    "salary": 55000
  }
]
```

---

## HTTP Status Codes

- **200 OK**: Request succeeded
- **201 Created**: Resource created successfully
- **404 Not Found**: Employee with given ID not found
- **400 Bad Request**: Invalid request format
- **500 Internal Server Error**: Server error

---

## Notes

- All data is stored in-memory, so it will be lost when the application is restarted
- Employee IDs are auto-generated starting from 1003 onwards (1001-1002 are reserved for sample data)
- Date format should be: `YYYY-MM-DD`
- All requests require `Content-Type: application/json` header for POST/PUT requests
