// Analytics page scripts
document.addEventListener('DOMContentLoaded', function() {
    // Region Pie Chart
    const regionCtx = document.getElementById('regionPieChart');
    if (regionCtx) {
        new Chart(regionCtx, {
            type: 'doughnut',
            data: {
                labels: ['Parakou', 'Cotonou', 'Porto-Novo', 'Abomey'],
                datasets: [{
                    data: [25, 35, 20, 20],
                    backgroundColor: [
                        '#667eea',
                        '#764ba2',
                        '#f093fb',
                        '#4facfe'
                    ],
                    borderColor: white,
                    borderWidth: 2
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom'
                    }
                }
            }
        });
    }

    // Product Bar Chart
    const productCtx = document.getElementById('productBarChart');
    if (productCtx) {
        new Chart(productCtx, {
            type: 'bar',
            data: {
                labels: ['Laptop HP', 'Smartphone', 'Clothing', 'Footwear', 'Electronics', 'Food'],
                datasets: [{
                    label: 'Sales Volume',
                    data: [13, 25, 35, 30, 22, 75],
                    backgroundColor: '#667eea',
                    borderColor: '#764ba2',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    // Trend Chart
    const trendCtx = document.getElementById('trendChart');
    if (trendCtx) {
        new Chart(trendCtx, {
            type: 'line',
            data: {
                labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
                datasets: [{
                    label: 'Weekly Sales',
                    data: [50, 60, 75, 85],
                    borderColor: '#667eea',
                    backgroundColor: 'rgba(102, 126, 234, 0.1)',
                    tension: 0.4,
                    fill: true,
                    pointRadius: 5,
                    pointBackgroundColor: '#667eea'
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });
    }
});

function exportToCSV() {
    alert('CSV Export functionality will be implemented');
}

function exportToPDF() {
    alert('PDF Export functionality will be implemented');
}

function printReport() {
    window.print();
}
