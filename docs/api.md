# API Documentation

This document provides a high-level overview of the API structure. Note that actual API implementation details may expand.

## API Standards

- **Version Header**: All V1 APIs are prefixed with `/api/v1`.
- **Response Format**: JSON.
- **Error Handling**: Standardized error responses via `GlobalExceptionHandler`.

## Endpoints (Draft/Planned)

### Stock API
- `GET /api/v1/stocks`: List all stocks.
- `GET /api/v1/stocks/{ticker}`: Get stock details by ticker.
- `POST /api/v1/stocks`: Register a new stock.

### Dividend API
- `GET /api/v1/dividends`: List dividends.
- `GET /api/v1/dividends/{ticker}`: Get dividends for a specific stock ticker.
- `POST /api/v1/dividends`: Record a new dividend payout.

## Common Response Structure

### Success
```json
{
  "ticker": "AAPL",
  "companyName": "Apple Inc.",
  "exchange": "NASDAQ",
  "industry": "Technology"
}
```

### Error
```json
{
  "status": 404,
  "code": "NOT_FOUND",
  "message": "Stock with ticker AAPL not found",
  "timestamp": "2024-01-05T10:25:49"
}
```
