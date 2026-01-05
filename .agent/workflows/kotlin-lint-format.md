---
description: Run Kotlin lint check and format using ktlint
---

# Kotlin Lint & Format Workflow

This workflow helps you maintain code quality by running lint checks and automatically formatting your Kotlin code according to the project's ktlint rules.

## Steps

### 1. Check for Linting Issues
Run the following command to see if there are any linting violations:
// turbo
```powershell
./gradlew ktlintCheck
```

### 2. Format Code
To automatically fix linting issues and format your code, run:
// turbo
```powershell
./gradlew ktlintFormat
```
