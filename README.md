# API Documentation

## To run project

```bash
mvn clean install
```
```bash
java -jar country-services-0.1.0-SNAPSHOT.jar
```
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
  curl -X GET http://localhost:8080/api/countries/usa
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
SELECT 
    ce.id,
    ce.alpha3Code,
    ce.common_Name,
    ce.official_Name,
    ce.currencies,
    ce.capital,
    ce.region,
    ce.subregion,
    ce.population,
    ce.borders,
    GROUP_CONCAT(DISTINCT cl.language_key) AS languages_key,
    GROUP_CONCAT(DISTINCT cl.language_value) AS languages_value,
    GROUP_CONCAT(DISTINCT ct.timezone) AS timezones
FROM 
    country_entity ce
LEFT JOIN 
    country_languages cl ON ce.id = cl.country_id
LEFT JOIN 
    country_timezones ct ON ce.id = ct.country_id
GROUP BY 
    ce.id, ce.alpha3Code, ce.common_Name;

```


