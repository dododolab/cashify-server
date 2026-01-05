---
description: Update API documentation based on Controller code
---

# API Documentation Sync Workflow

This workflow ensures that `docs/api.md` stays in sync with the actual Spring Boot Controller implementation.

## Steps

### 1. Analyze Controller and DTOs
Identify the target Controller and its associated Request/Response DTOs to extract:
- Endpoint paths and HTTP methods.
- Path variables and Query parameters.
- Request/Response body structures.

### 2. Compare with Documentation
Read `docs/api.md` and check if the existing documentation matches the code. Look for:
- Missing endpoints.
- Changed paths or parameters.
- Outdated response examples.

### 3. Update docs/api.md
Modify the documentation to reflect the current code state.

#### **Documentation Standard Format**
For each endpoint, include:
- **Heading**: `### [Method] [Path]`
- **Description**: Brief explanation of the endpoint.
- **Request (if applicable)**: Body fields and types.
- **Response**: Body fields and types with a JSON example.

## Instructions for AI
When this workflow is called:
1. Scan the `src/main/kotlin/io/cashify/*/api` directory.
2. For each Controller, extract the API specifications.
3. Update `docs/api.md` following the "Endpoints" and "Response Structure" sections.
4. If a new domain is added, create a new section under `## Endpoints`.

## Verification
// turbo
```powershell
# No specific command, but ensure docs/api.md is updated correctly.
cat docs/api.md
```
