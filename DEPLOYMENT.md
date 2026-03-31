# SIAD DEPLOYMENT GUIDE

## Prerequisites
- Java JDK 11+
- Apache Tomcat 10.0+
- MySQL 8.0+
- Maven 3.6+

## Deployment Steps

### 1. Database Setup
```bash
# Create database
mysql -u root -p < sql/siad_database.sql
```

### 2. Build Application
```bash
# Clean and build
mvn clean package

# WAR file will be at: target/siad-sales-analytics-1.0.0.war
```

### 3. Deploy to Tomcat

#### Method A: Direct Copy
```bash
# Copy WAR to Tomcat
cp target/siad-sales-analytics-1.0.0.war $CATALINA_HOME/webapps/siad.war

# Start Tomcat
$CATALINA_HOME/bin/catalina.sh start

# Access application
# http://localhost:8080/siad
```

#### Method B: Using Tomcat Manager
1. Start Tomcat
2. Go to http://localhost:8080/manager
3. Upload WAR file
4. Access application

### 4. Verify Deployment
```bash
# Check Tomcat logs
tail -f $CATALINA_HOME/logs/catalina.out

# Test connection
curl http://localhost:8080/siad/login
```

### 5. Configuration

Edit `src/com/siad/project/utils/DatabaseConnection.java`:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/siad_sales_db";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_password";
```

## Troubleshooting

### Issue: Cannot connect to database
- Verify MySQL is running
- Check database credentials
- Ensure database exists: `CREATE DATABASE IF NOT EXISTS siad_sales_db`

### Issue: Servlets not found
- Clear Tomcat work directory: `rm -rf $CATALINA_HOME/work/`
- Redeploy WAR file

### Issue: Session expires immediately
- Check CATALINA_HOME/conf/web.xml session timeout
- Default is 30 minutes

## Production Deployment

### Security Checklist
- [ ] Change default database passwords
- [ ] Use HTTPS/SSL
- [ ] Set strong passwords for users
- [ ] Configure firewall rules
- [ ] Enable logging and monitoring
- [ ] Set up automated backups
- [ ] Configure connection pooling

### Performance Optimization
- [ ] Enable Gzip compression
- [ ] Configure connection pool size
- [ ] Set JVM heap size: -Xmx1024m
- [ ] Use CDN for static files
- [ ] Set up database indexes

### Monitoring
- Monitor Tomcat logs
- Track database performance
- Monitor memory usage
- Set up alerts for errors

## Rollback Procedure
```bash
# 1. Stop Tomcat
$CATALINA_HOME/bin/catalina.sh stop

# 2. Restore previous version
cp $BACKUP_DIR/siad.war $CATALINA_HOME/webapps/

# 3. Restore database backup
mysql -u root -p siad_sales_db < $BACKUP_DIR/siad_database_backup.sql

# 4. Start Tomcat
$CATALINA_HOME/bin/catalina.sh start
```
