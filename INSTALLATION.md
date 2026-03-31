# SIAD - Installation Guide

## Quick Start (5 minutes)

### Step 1: Clone Repository
```bash
git clone https://github.com/Balysys/PROGRAMMATION-EN-JAVA-MASTER-SIAD.git
cd PROGRAMMATION-EN-JAVA-MASTER-SIAD
```

### Step 2: Build Project
```bash
mvn clean package
```

### Step 3: Setup Database
```bash
mysql -u root -p < sql/siad_database.sql
```

### Step 4: Deploy
```bash
cp target/siad-sales-analytics-1.0.0.war $CATALINA_HOME/webapps/siad.war
```

### Step 5: Start Tomcat
```bash
$CATALINA_HOME/bin/catalina.sh start
```

### Step 6: Access Application
```
http://localhost:8080/siad
Username: admin
Password: admin123
```

---

## Detailed Installation

### System Requirements

| Component | Version | Download |
|-----------|---------|----------|
| Java JDK | 11+ | [Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) |
| Apache Tomcat | 10.0+ | [Download](https://tomcat.apache.org/download-10.cgi) |
| MySQL | 8.0+ | [Download](https://dev.mysql.com/downloads/mysql/) |
| Maven | 3.6+ | [Download](https://maven.apache.org/download.cgi) |

### Setup Java
```bash
# Verify installation
java -version
javac -version

# Set JAVA_HOME (Linux/Mac)
export JAVA_HOME=/path/to/java
export PATH=$JAVA_HOME/bin:$PATH

# Windows: Set system environment variable JAVA_HOME
```

### Setup Apache Tomcat
```bash
# Download and extract
wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.5/bin/apache-tomcat-10.1.5.tar.gz
tar -xzf apache-tomcat-10.1.5.tar.gz
cd apache-tomcat-10.1.5

# Set permissions
chmod +x bin/catalina.sh

# Set CATALINA_HOME
export CATALINA_HOME=$(pwd)
```

### Setup MySQL
```bash
# Start MySQL
mysql.server start

# Create database
mysql -u root -p < sql/siad_database.sql

# Verify
mysql -u root -p
> SHOW DATABASES;
> USE siad_sales_db;
> SHOW TABLES;
```

### Setup Maven
```bash
# Verify installation
mvn -version

# Configure settings.xml if needed
~/.m2/settings.xml
```

### Build Project
```bash
# Clone and build
git clone https://github.com/Balysys/PROGRAMMATION-EN-JAVA-MASTER-SIAD.git
cd PROGRAMMATION-EN-JAVA-MASTER-SIAD

# Build with Maven
mvn clean install
mvn package

# Output: target/siad-sales-analytics-1.0.0.war
```

### Deploy Application
```bash
# Copy WAR file
cp target/siad-sales-analytics-1.0.0.war $CATALINA_HOME/webapps/siad.war

# Start Tomcat
$CATALINA_HOME/bin/catalina.sh start

# Check logs
tail -f $CATALINA_HOME/logs/catalina.out
```

### Access Application
```
http://localhost:8080/siad
```

---

## Configuration

### Database Connection
Edit `src/com/siad/project/utils/DatabaseConnection.java`:

```java
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/siad_sales_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
}
```

### Tomcat Configuration
Edit `$CATALINA_HOME/conf/context.xml`:

```xml
<Context>
    <!-- Connection pooling -->
    <Resource name="jdbc/SIAD" 
              auth="Container"
              type="javax.sql.DataSource"
              maxActive="20"
              maxIdle="5"
              maxWait="10000"
              username="root"
              password="your_password"
              driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/siad_sales_db" />
</Context>
```

---

## Verification

### Check Installation
```bash
# Java
java -version

# Maven
mvn -version

# MySQL
mysql -u root -p -e "SHOW DATABASES;"

# Tomcat
curl http://localhost:8080/
```

### Test Database
```bash
mysql -u root -p
USE siad_sales_db;
SELECT * FROM users;
SELECT * FROM sales;
```

### Test Application
```bash
# Login page
curl http://localhost:8080/siad/login

# Dashboard (requires session)
curl -b cookies.txt http://localhost:8080/siad/dashboard
```

---

## Troubleshooting

### Issue: Java not found
```bash
# Install Java
sudo apt-get install default-jdk  # Ubuntu/Debian
brew install java  # macOS

# Set JAVA_HOME
export JAVA_HOME=$(which java)
```

### Issue: MySQL connection failed
```bash
# Check MySQL status
sudo systemctl status mysql

# Start MySQL
sudo systemctl start mysql

# Verify port
netstat -an | grep 3306
```

### Issue: Tomcat won't start
```bash
# Check permissions
chmod +x $CATALINA_HOME/bin/catalina.sh

# Check logs
cat $CATALINA_HOME/logs/catalina.out

# Try verbose startup
$CATALINA_HOME/bin/catalina.sh start -v
```

### Issue: Port 8080 already in use
```bash
# Find process using port
lsof -i :8080

# Kill process
kill -9 <PID>

# Or change Tomcat port in $CATALINA_HOME/conf/server.xml
```

---

## Development Setup (IDE)

### Eclipse
1. Import project: File → Import → Existing Maven Projects
2. Configure Tomcat: Window → Preferences → Server → Runtime
3. Add project to server: Right-click project → Run on Server

### IntelliJ IDEA
1. Open project: File → Open
2. Configure Tomcat: Run → Edit Configurations → Add new Tomcat
3. Deploy: Run → Edit Configurations → Deployment tab

### VS Code
1. Install Extension: Extension Pack for Java
2. Open project folder
3. Configure launch.json for debugging

---

## Next Steps

1. ✅ **Database Setup:** Run `sql/siad_database.sql`
2. ✅ **Configuration:** Update `DatabaseConnection.java`
3. ✅ **Build:** Run `mvn clean package`
4. ✅ **Deploy:** Copy WAR to Tomcat
5. ✅ **Start:** Run Tomcat server
6. ✅ **Access:** http://localhost:8080/siad
7. 📖 **Read:** Check [README.md](README.md) for full documentation

---

## Support

For issues:
- Check log files: `$CATALINA_HOME/logs/catalina.out`
- Review error messages
- GitHub Issues: [Report an issue](https://github.com/Balysys/PROGRAMMATION-EN-JAVA-MASTER-SIAD/issues)
