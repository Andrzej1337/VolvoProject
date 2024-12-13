# API Documentation

## Endpoints

### Get Country Details
Retrieve information about a specific country by its country code.

**Endpoint:**
```bash
GET http://localhost:8080/api/countries/{country_code}
```

#### Example Usage:
- **Using cURL:**
  ```bash
  curl -X GET http://localhost:8080/api/countries/US
  ```
- **Using Postman:**
  - Set the request type to `GET`.
  - Enter the URL: `http://localhost:8080/api/countries/{country_code}`.
  - Replace `{country_code}` with the desired country code.
- **Using a Web Browser:**
  - Navigate to `http://localhost:8080/api/countries/{country_code}`.

---

## Database Access

The country data is stored in an in-memory H2 database. You can access it via the H2 Console.

**H2 Console URL:**
```
http://localhost:8080/h2-console/
```

### Database Configuration:
- **Driver Class:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:mem:db`
- **Username:** `sa`
- **Password:** `sa`

### Query Example:
To view all the countries stored in the database:
```sql
SELECT * FROM COUNTRY_ENTITY;
```


