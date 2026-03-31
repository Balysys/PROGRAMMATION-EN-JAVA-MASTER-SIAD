# Contributing to SIAD

Thank you for your interest in contributing to the SIAD project! This document provides guidelines and instructions for contributing.

## Code of Conduct

- Be respectful and inclusive
- Welcome constructive criticism
- Focus on what is best for the community
- Show empathy towards other community members

## Getting Started

### Fork & Clone
```bash
# 1. Fork the repository
# Click "Fork" on GitHub

# 2. Clone your fork
git clone https://github.com/YOUR_USERNAME/PROGRAMMATION-EN-JAVA-MASTER-SIAD.git

# 3. Add upstream
git remote add upstream https://github.com/Balysys/PROGRAMMATION-EN-JAVA-MASTER-SIAD.git
```

### Setup Development Environment
```bash
# Build project
mvn clean install

# Run tests
mvn test

# Create WAR
mvn package
```

## Making Changes

### Create Feature Branch
```bash
# Update master
git checkout main
git pull upstream main

# Create feature branch
git checkout -b feature/YourFeatureName
```

### Code Style Guidelines

#### Java Code
- Follow Google Java Style Guide
- Use descriptive variable names
- Add JavaDoc comments for public methods
- Keep methods focused and small

Example:
```java
/**
 * Calculate KPIs from sales data
 * @return KPI object with computed metrics
 */
public static KPI calculateKPIs() {
    // Implementation
}
```

#### SQL Queries
- Use proper indexing
- Avoid SELECT *
- Use meaningful table aliases
- Add comments for complex queries

#### HTML/CSS/JavaScript
- Use semantic HTML5
- Follow CSS naming conventions
- Use consistent indentation (2 spaces)
- Add comments for complex logic

### Commit Guidelines

```bash
# Commit with meaningful message
git commit -m "feat: Add new recommendation rule for seasonal sales"

# Message format:
# <type>(<scope>): <subject>
# <body>
# <footer>

# Types: feat, fix, docs, style, refactor, test, chore
# Scope: engine, dao, servlet, ui, etc.
# Subject: brief description (50 chars)
# Body: detailed explanation (optional)
# Footer: related issues (optional)
```

## Testing Requirements

### Add Tests for New Features
```java
@Test
public void testSalesCalculation() {
    // Arrange
    List<Sale> sales = createTestSales();
    
    // Act
    KPI kpi = AnalyticsEngine.calculateKPIs();
    
    // Assert
    assertEquals(expected, actual);
}
```

### Run All Tests
```bash
mvn clean test
```

## Documentation

### Update README for Major Changes
- Add new features to Features section
- Update architecture diagram if applicable
- Add new API endpoints to documentation

### Add Comments for Complex Logic
```java
// Calculate growth rate considering seasonal factors
double adjustedGrowth = ...;
```

## Pull Request Process

### Before Submitting
1. Update documentation
2. Add/update tests
3. Run full test suite
4. Build WAR file
5. Check for merge conflicts

### Submit Pull Request
```bash
# Push to your fork
git push origin feature/YourFeatureName

# Go to GitHub and create Pull Request
# Fill out PR template
```

### PR Description Template
```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Related Issues
Closes #issue_number

## Testing Done
- [ ] Unit tests
- [ ] Integration tests
- [ ] Manual testing

## Screenshots (if applicable)
Add screenshots for UI changes

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] Tests pass
- [ ] No breaking changes
```

## Review Process

1. **Automated Checks:** GitHub Actions verify build
2. **Code Review:** Team reviews code
3. **Testing:** Reviewers test changes
4. **Approval:** Merge approved by maintainers
5. **Merge:** Changes merged to main

## Getting Help

### Questions?
- Open a GitHub discussion
- Check existing issues
- Review documentation

### Found a Bug?
1. Check if already reported
2. Create detailed bug report
3. Include steps to reproduce
4. Add screenshots/logs if applicable

## Areas for Contribution

### High Priority
- [ ] Performance optimization
- [ ] Bug fixes
- [ ] Security improvements
- [ ] Documentation

### Medium Priority
- [ ] New features
- [ ] Code refactoring
- [ ] Test coverage
- [ ] UI improvements

### Nice to Have
- [ ] Code comments
- [ ] Examples
- [ ] Tutorials
- [ ] Translations

## Recognition

Contributors will be:
- Added to CONTRIBUTORS.md
- Mentioned in release notes
- Recognized in project README
- Listed as contributors on GitHub

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

## Questions?

- Email: [maintainer@email.com]
- GitHub Issues: [Project issues](https://github.com/Balysys/PROGRAMMATION-EN-JAVA-MASTER-SIAD/issues)
- Discussions: [Project discussions](https://github.com/Balysys/PROGRAMMATION-EN-JAVA-MASTER-SIAD/discussions)

---

Thank you for contributing! 🎉
