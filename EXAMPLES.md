# SIAD - Usage Examples

## Quick Start Examples

### 1. Login to the System

```
URL: http://localhost:8080/siad/login
Username: admin
Password: admin123
```

### 2. View Dashboard

After login, you'll see:
- **KPI Cards:** Total Sales, Revenue, Units, Growth Rate
- **Performance Metrics:** Top Product, Best Region, Monthly Change
- **Recommendations:** AI-generated action items
- **Sales Chart:** 12-month trend visualization

### 3. Add a Sale

1. Click "Sales Data" in sidebar
2. Fill the form:
   - Product Name: Laptop HP
   - Region: Parakou
   - Quantity: 5
   - Price: 450000
   - Sale Date: 2024-03-31
3. Click "Add Sale"

### 4. Filter Sales by Region

1. Go to "Sales Data"
2. Select region from dropdown: "Cotonou"
3. Click "Filter"
4. View sales for that region only

### 5. View Recommendations

1. Click "Recommendations" in sidebar
2. Review all AI-generated suggestions
3. Priority levels: HIGH (red), MEDIUM (yellow), LOW (green)
4. Impact scores show likelihood of success

---

## Business Scenarios

### Scenario 1: Sales Decline Alert

**Situation:**
- Parakou sales decreased 18% from last month

**System Response:**
```
Priority: HIGH
Recommendation: Apply 10% discount in Parakou
Reason: Sales decreased more than 15%
Impact Score: 85%
```

**Action:**
- Implement discount immediately
- Launch promotional campaign
- Monitor next month's results

### Scenario 2: High Demand Detection

**Situation:**
- Cotonou sales increased 25% from last month
- Product "Smartphone Samsung" demand up 30%

**System Response:**
```
Priority: HIGH
Recommendation: Increase stock levels and expand marketing
Reason: High demand detected
Impact Score: 90%
```

**Action:**
- Order more inventory
- Increase ad spend
- Prepare for growth

### Scenario 3: Stagnant Sales

**Situation:**
- Porto-Novo monthly sales: 50,000 CFA
- No growth for 3 months

**System Response:**
```
Priority: MEDIUM
Recommendation: Launch promotional campaign
Reason: Sales are stagnant
Impact Score: 70%
```

**Action:**
- Plan marketing activities
- Run social media campaign
- Consider pricing adjustments

---

## API Usage Examples

### Get KPIs
```java
KPI kpi = AnalyticsEngine.calculateKPIs();
System.out.println("Total Revenue: " + kpi.getTotalRevenue());
System.out.println("Growth Rate: " + kpi.getGrowthRate() + "%");
System.out.println("Top Product: " + kpi.getTopProduct());
```

### Get Recommendations
```java
List<Recommendation> recs = RecommendationEngine.generateRecommendations();
for (Recommendation rec : recs) {
    System.out.println(rec.getRegion() + ": " + rec.getRecommendation());
    System.out.println("Priority: " + rec.getPriority());
}
```

### Add Sale
```java
Sale sale = new Sale(
    "Laptop HP",
    "Parakou",
    5,
    450000,
    LocalDate.now()
);
SaleDAO.addSale(sale);
```

### Get Sales by Region
```java
List<Sale> sales = SaleDAO.getSalesByRegion("Parakou");
for (Sale sale : sales) {
    System.out.println(sale.getProductName() + ": " + sale.getQuantity());
}
```

---

## Database Query Examples

### View All Regions
```sql
SELECT region, COUNT(*) as sales_count, SUM(quantity * price) as revenue
FROM sales
GROUP BY region
ORDER BY revenue DESC;
```

### Top Performing Products
```sql
SELECT product_name, SUM(quantity) as units, SUM(quantity * price) as revenue
FROM sales
GROUP BY product_name
ORDER BY revenue DESC
LIMIT 5;
```

### Monthly Sales Trend
```sql
SELECT 
    DATE_FORMAT(sale_date, '%Y-%m') as month,
    SUM(quantity * price) as monthly_revenue
FROM sales
GROUP BY DATE_FORMAT(sale_date, '%Y-%m')
ORDER BY month DESC;
```

### Regional Performance
```sql
SELECT 
    region,
    DATE_FORMAT(sale_date, '%Y-%m') as month,
    SUM(quantity * price) as revenue
FROM sales
GROUP BY region, DATE_FORMAT(sale_date, '%Y-%m')
ORDER BY region, month DESC;
```

---

## Excel Export Example

```javascript
function exportToExcel() {
    // Get table
    const table = document.querySelector('.sales-table');
    
    // Create workbook
    const wb = XLSX.utils.table_to_book(table);
    
    // Download
    XLSX.writeFile(wb, "sales_report.xlsx");
}
```

---

## CSV Export Example

```java
public static void exportSalesToCSV(String filename) {
    List<Sale> sales = SaleDAO.getAllSales();
    
    try (FileWriter writer = new FileWriter(filename)) {
        writer.append("ID,Product,Region,Quantity,Price,Date\n");
        
        for (Sale sale : sales) {
            writer.append(sale.getId() + ",");
            writer.append(sale.getProductName() + ",");
            writer.append(sale.getRegion() + ",");
            writer.append(sale.getQuantity() + ",");
            writer.append(sale.getPrice() + ",");
            writer.append(sale.getSaleDate() + "\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

## Recommendation Rules Examples

### Rule: High Growth Detection
```java
if (currentMonthRevenue > previousMonthRevenue * 1.20) {
    Recommendation rec = new Recommendation(
        region,
        "Increase stock levels and expand marketing",
        "High demand detected - sales up 20%+",
        "HIGH",
        0.90
    );
}
```

### Rule: Price Optimization
```java
if (demandIncreasePercent > 30) {
    return new Recommendation(
        region,
        "Consider price increase",
        "High demand allows for premium pricing",
        "MEDIUM",
        0.75
    );
}
```

### Rule: Market Expansion
```java
if (regionalPerformance > nationalAverage * 1.5) {
    return new Recommendation(
        region,
        "Expand operations in this region",
        "Exceptional regional performance",
        "HIGH",
        0.85
    );
}
```

---

## Dashboard Configuration Examples

### Customize KPI Card
```html
<div class="kpi-card">
    <h3>Total Revenue</h3>
    <p class="kpi-value">CFA <%= String.format("%.2f", kpi.getTotalRevenue()) %></p>
    <p class="trend">↑ 15% vs last month</p>
</div>
```

### Customize Chart Data
```javascript
const chartData = {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
    datasets: [{
        label: 'Sales Trend',
        data: [100, 120, 95, 150, 180],
        borderColor: '#667eea',
        tension: 0.4
    }]
};
```

---

## Performance Tips

### 1. Optimize Database Queries
- Use indexed columns
- Add WHERE clauses
- Limit result sets

### 2. Cache KPIs
- Calculate once per hour
- Store in session
- Refresh on demand

### 3. Optimize Charts
- Limit data points to 12 months
- Use aggregated data
- Load asynchronously

### 4. Browser Optimization
- Minify CSS/JS
- Compress images
- Enable caching headers

---

## Troubleshooting Guide

### Issue: Recommendations not showing
```
Solution:
1. Check if sales data exists
2. Verify date range is valid
3. Ensure revenue thresholds are met
```

### Issue: Dashboard loads slowly
```
Solution:
1. Check database connection pool size
2. Verify no large queries
3. Clear browser cache
4. Check Tomcat heap size
```

### Issue: Chart not rendering
```
Solution:
1. Verify Chart.js is loaded
2. Check browser console for errors
3. Ensure data format is correct
4. Check for null values
```

---

## Best Practices

### For Data Entry
- ✅ Enter consistent region names
- ✅ Use realistic product prices
- ✅ Enter daily sales data
- ❌ Avoid duplicate entries
- ❌ Don't modify old records

### For Analysis
- ✅ Review recommendations monthly
- ✅ Track implementation impact
- ✅ Compare forecasts vs actual
- ✅ Adjust thresholds as needed
- ❌ Don't ignore HIGH priority items

### For Security
- ✅ Change default password
- ✅ Use strong credentials
- ✅ Log out when done
- ✅ Review user access
- ❌ Share login credentials
- ❌ Use default passwords

---

## Support & Resources

- 📖 [Full Documentation](README.md)
- 🏗️ [Architecture Guide](ARCHITECTURE.md)
- 📦 [Installation Guide](INSTALLATION.md)
- 🚀 [Deployment Guide](DEPLOYMENT.md)
- 🤝 [Contributing Guide](CONTRIBUTING.md)

---

Last Updated: March 31, 2026
