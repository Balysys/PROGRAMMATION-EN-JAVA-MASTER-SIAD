<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 500 - Server Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="error-container" style="display: flex; justify-content: center; align-items: center; height: 100vh; background: linear-gradient(135deg, #dc3545 0%, #c82333 100%); color: white;">
        <div style="text-align: center;">
            <h1 style="font-size: 80px; margin: 0;">500</h1>
            <h2>Internal Server Error</h2>
            <p>An unexpected error occurred. Please try again later.</p>
            <a href="dashboard" class="btn-primary" style="display: inline-block; margin-top: 20px; padding: 10px 30px; background: white; color: #dc3545; text-decoration: none; border-radius: 5px; font-weight: 600;">Go to Dashboard</a>
        </div>
    </div>
</body>
</html>
