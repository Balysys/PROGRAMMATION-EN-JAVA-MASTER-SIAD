# SIAD - Architecture & Design Documentation

## System Overview

The Sales Data Analytics Dashboard is built using a 3-tier web application architecture following the MVC (Model-View-Controller) pattern.

```
┌─────────────────────────────────────────────────────────────┐
│                         CLIENT LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │   HTML/CSS   │  │  JavaScript  │  │   Chart.js       │  │
│  │   Responsive │  │   Dynamic UI │  │   Visualization  │  │
│  └──────────────┘  └──────────────┘  └──────────────────┘  │
└────────────────────────┬──────────────────────────────────┘
                         │ HTTP/HTTPS
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                  APPLICATION LAYER                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │  Servlets    │  │     JSPs     │  │  Controllers     │  │
│  │  Handle HTTP │  │   Render UI  │  │  Business Logic  │  │
│  └──────────────┘  └──────────────┘  └──────────────────┘  │
│                         │                                    │
│  ┌──────────────────────────────────────────────────────┐   │
│  │           BUSINESS LOGIC LAYER                       │   │
│  │  ┌────────────────┐  ┌────────────────────────────┐ │   │
│  │  │ AnalyticsEngine│  │RecommendationEngine        │ │   │
│  │  │ - KPI Calc     │  │ - Rule evaluation          │ │   │
│  │  │ - Trends       │  │ - Priority scoring         │ │   │
│  │  │ - Growth rate  │  │ - Impact analysis          │ │   │
│  │  └────────────────┘  └────────────────────────────┘ │   │
│  └──────────────────────────────────────────────────────┘   │
│                         │                                    │
│  ┌──────────────────────────────────────────────────────┐   │
│  │              DATA ACCESS LAYER                       │   │
│  │  ┌──────────┐  ┌──────────┐  ┌────────────────────┐ │   │
│  │  │ UserDAO  │  │ SaleDAO  │  │ ProductDAO         │ │   │
│  │  │ - CRUD   │  │ - Query  │  │ - Manage products  │ │   │
│  │  │ - Auth   │  │ - Filter │  │ - Categories       │ │   │
│  │  └──────────┘  └──────────┘  └────────────────────┘ │   │
│  └──────────────────────────────────────────────────────┘   │
└────────────────────────┬──────────────────────────────────┘
                         │ JDBC
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                      DATA LAYER                             │
│  ┌────────────────────────────────────────────────────────┐ │
│  │              MySQL Database                           │ │
│  │  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐  │ │
│  │  │ users        │ │ sales        │ │ products     │  │ │
│  │  │ - id         │ │ - id         │ │ - id         │  │ │
│  │  │ - username   │ │ - product    │ │ - name       │  │ │
│  │  │ - password   │ │ - region     │ │ - category   │  │ │
│  │  │ - role       │ │ - quantity   │ │              │  │ │
│  │  │              │ │ - price      │ │              │  │ │
│  │  │              │ │ - date       │ │              │  │ │
│  │  └──────────────┘ └──────────────┘ └──────────────┘  │ │
│  │                                                        │ │
│  │  Views:                                               │ │
│  │  - sales_by_region            - monthly_sales        │ │
│  │  - sales_by_product                                   │ │
│  └────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

## Design Patterns Used

### 1. Model-View-Controller (MVC)
- **Model:** Data models (User, Sale, Product, KPI, Recommendation)
- **View:** JSP pages for rendering UI
- **Controller:** Servlets handling HTTP requests

### 2. Data Access Object (DAO)
Encapsulates database access logic:
```java
public class SaleDAO {
    public static boolean addSale(Sale sale) { }
    public static List<Sale> getAllSales() { }
    public static List<Sale> getSalesByRegion(String region) { }
}
```

### 3. Business Logic Separation
Core logic in dedicated engines:
```java
public class AnalyticsEngine {
    public static KPI calculateKPIs() { }
    public static double calculateGrowthRate(List<Sale> sales) { }
}
```

### 4. Utility Classes
Common functionality in utilities:
```java
public class PasswordUtils {
    public static String hashPassword(String password) { }
    public static boolean verifyPassword(String password, String hash) { }
}
```

## Class Hierarchy

```
com.siad.project
├── model/
│   ├── User                    (5 properties)
│   ├── Sale                    (7 properties)
│   ├── Product                 (3 properties)
│   ├── KPI                     (7 properties)
│   └── Recommendation          (6 properties)
├── dao/
│   ├── UserDAO                 (6 methods)
│   ├── SaleDAO                 (8 methods)
│   └── ProductDAO              (6 methods)
├── servlet/
│   ├── LoginServlet            (Auth & Session)
│   ├── LogoutServlet           (Session cleanup)
│   ├── DashboardServlet        (Main dashboard)
│   ├── SalesServlet            (CRUD operations)
│   └── RecommendationServlet   (Recommendations)
├── utils/
│   ├── DatabaseConnection      (JDBC setup)
│   └── PasswordUtils           (Security)
└── engine/
    ├── AnalyticsEngine         (KPI calculations)
    └── RecommendationEngine    (Decision logic)
```

## Data Flow

### Login Flow
```
1. User inputs credentials → login.jsp
2. LoginServlet receives POST request
3. UserDAO.authenticateUser() validates
4. If valid: session created, redirect to dashboard
5. If invalid: error message, reload login.jsp
```

### Dashboard Data Flow
```
1. DashboardServlet checks session
2. AnalyticsEngine.calculateKPIs() computes metrics
3. RecommendationEngine.generateRecommendations() creates suggestions
4. Data passed to dashboard.jsp
5. JSP renders with Chart.js visualizations
```

### Sales CRUD Flow
```
1. GET /sales → Display all sales
2. POST /sales?action=add → Insert new sale
3. POST /sales?action=delete → Remove sale
4. Optional filters applied in SaleDAO.getSalesByRegion()
5. Redirect to sales list
```

## Database Relationships

```
┌──────────────┐
│    users     │
│  id (PK)     │
│  username    │
│  password    │
│  role        │
└──────────────┘

┌──────────────┐        1     ┌──────────────┐
│   products   │◄─────────────┤    sales     │
│  id (PK)     │              │  id (PK)     │
│  name        │              │  product_name│ (FK)
│  category    │              │  region      │
└──────────────┘              │  quantity    │
                              │  price       │
                              │  sale_date   │
                              └──────────────┘
```

## Request/Response Cycle

```
User Request
    ↓
┌─────────────────────────┐
│ Web Container (Tomcat)  │
│ - Routes to Servlet     │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│ Servlet Execution       │
│ - Check Session         │
│ - Validate Input        │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│ DAO Layer               │
│ - Database Query        │
│ - Data Processing       │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│ Business Logic          │
│ - Analytics Calc        │
│ - Recommendations       │
└────────────┬────────────┘
             ↓
┌─────────────────────────┐
│ View Rendering          │
│ - JSP Processing        │
│ - HTML Generation       │
└────────────┬────────────┘
             ↓
        HTTP Response
             ↓
        Browser Rendering
```

## Security Architecture

### Authentication
```java
// 1. Password hashing on registration
String hashedPassword = PasswordUtils.hashPassword(password);
UserDAO.createUser(username, hashedPassword, role);

// 2. Verification on login
if (PasswordUtils.verifyPassword(inputPassword, storedHash)) {
    // Create session
}
```

### Authorization
```java
// In servlet
HttpSession session = request.getSession();
Object userId = session.getAttribute("userId");
if (userId == null) {
    response.sendRedirect("login");
}
```

### SQL Injection Prevention
```java
// Using PreparedStatement
String query = "SELECT * FROM users WHERE username = ?";
PreparedStatement stmt = conn.prepareStatement(query);
stmt.setString(1, username);  // Parameter binding
ResultSet rs = stmt.executeQuery();
```

## API Endpoints Structure

```
/siad (Context root)
├── /login                   (POST) - Authentication
├── /logout                  (GET)  - Session cleanup
├── /dashboard               (GET)  - Main dashboard
├── /sales                   (GET)  - View sales
│   └── (POST) add/delete     - CRUD operations
├── /recommendations         (GET)  - Get recommendations
└── /analytics               (GET)  - Advanced analytics
```

## Configuration Files

### web.xml
Maps servlets to URL patterns:
```xml
<servlet>
    <servlet-name>DashboardServlet</servlet-name>
    <servlet-class>com.siad.project.servlet.DashboardServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>DashboardServlet</servlet-name>
    <url-pattern>/dashboard</url-pattern>
</servlet-mapping>
```

### pom.xml
Maven dependencies and plugins:
```xml
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>5.0.0</version>
</dependency>
```

## Components Interaction

### Decision Support Engine Flow
```
Sales Data (From Database)
    ↓
AnalyticsEngine
    ├── calculateKPIs()           → KPI object
    ├── calculateGrowthRate()     → Double (%)
    └── calculateMonthlyChange()  → Double (%)
    ↓
RecommendationEngine
    ├── generateRecommendations() → List<Recommendation>
    ├── analyzeRegion()           → Decision logic
    └── Rule evaluation           → Priority & Impact
    ↓
Dashboard/Recommendations Page
    ↓
User Actions & Decision Making
```

## Performance Considerations

1. **Database Indexing:** Key columns indexed for fast queries
2. **Connection Pooling:** Reuse database connections
3. **Caching:** Session-based data caching
4. **Lazy Loading:** Data loaded on demand
5. **Pagination:** Large datasets handled efficiently

## Scalability Aspects

- Stateless servlet design
- Database can scale independently
- Session management via server
- Load balancing compatible
- Horizontal scaling possible

## Future Architecture Extensions

```
Current:
[Tomcat] → [MySQL] ← [Dashboard]

Phase 2:
[Load Balancer] → [Tomcat1, Tomcat2, Tomcat3]
                   ↓
              [Connection Pool]
                   ↓
            [MySQL Cluster]
                   ↓
            [Cache Layer - Redis]

Phase 3:
[API Gateway] → [Microservices]
              [Analytics Service]
              [Recommendation Service]
              [Data Service]

Phase 4:
[Real-time Events] → [WebSocket Server]
[ML Pipeline] → [TensorFlow Models]
[Data Warehouse] → [ETL Process]
```
