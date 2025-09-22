# Calculadora de Empréstimos API

API REST para cálculo de empréstimos com sistema PRICE.

## 🚀 Funcionalidades

- **Sistema PRICE**: Parcelas fixas com juros decrescentes e amortização crescente
- Cálculo de seguros, taxas e tarifas
- Conversão de taxas de juros
- Valor presente das parcelas

## 📋 API Reference

### Endpoint
```
POST /simulations
Content-Type: application/json
```

### Request Body

```json
{
  "calculationType": "PRICE",
  "installmentQuantity": 24,
  "requestedAmount": {
    "amount": 50000.00,
    "currency": "BRL"
  },
  "contractDate": "2024-01-15",
  "firstInstallmentDate": "2024-02-15",
  "monthlyInterestRate": 0.025,
  "insurance": {
    "paymentType": "FINANCED|UPFRONT",
    "totalAmount": {
      "amount": 1000.00,
      "currency": "BRL"
    }
  },
  "fee": {
    "paymentType": "FINANCED|UPFRONT", 
    "totalAmount": {
      "amount": 500.00,
      "currency": "BRL"
    }
  },
  "tax": {
    "paymentType": "FINANCED|UPFRONT",
    "dailyFinancialOperationalTax": {
      "amount": 0.0041,
      "currency": "BRL"
    },
    "additionalFinancialOperationalTax": {
      "amount": 0.38,
      "currency": "BRL"
    }
  }
}
```

### Response

```json
{
  "requestedAmount": {
    "amount": 50000.00,
    "currency": "BRL"
  },
  "contractDate": "2024-01-15",
  "firstInstallmentDate": "2024-02-15",
  "monthlyInterestRate": 2.5,
  "annualInterestRate": 34.49,
  "totalFinancedAmount": {
    "amount": 52000.00,
    "currency": "BRL"
  },
  "installments": [
    {
      "installmentNumber": 1,
      "dueDate": "2024-02-15",
      "installmentValue": {
        "amount": 2176.32,
        "currency": "BRL"
      },
      "interestAmount": {
        "amount": 1300.00,
        "currency": "BRL"
      },
      "amortizationAmount": {
        "amount": 876.32,
        "currency": "BRL"
      },
      "balanceAmount": {
        "amount": 51123.68,
        "currency": "BRL"
      }
    }
  ]
}
```

## 📝 Exemplos

### Financiamento PRICE Simples
```bash
curl -X POST http://localhost:8080/simulations \
  -H "Content-Type: application/json" \
  -d '{
    "calculationType": "PRICE",
    "installmentQuantity": 24,
    "requestedAmount": {
      "amount": 50000.00,
      "currency": "BRL"
    },
    "contractDate": "2024-01-15",
    "firstInstallmentDate": "2024-02-15",
    "monthlyInterestRate": 0.025
  }'
```

### Financiamento com Seguro e Taxas
```bash
curl -X POST http://localhost:8080/simulations \
  -H "Content-Type: application/json" \
  -d '{
    "calculationType": "PRICE",
    "installmentQuantity": 36,
    "requestedAmount": {
      "amount": 100000.00,
      "currency": "BRL"
    },
    "contractDate": "2024-01-15",
    "firstInstallmentDate": "2024-02-15",
    "monthlyInterestRate": 0.019,
    "insurance": {
      "paymentType": "FINANCED",
      "totalAmount": {
        "amount": 2500.00,
        "currency": "BRL"
      }
    },
    "tax": {
      "paymentType": "FINANCED",
      "dailyFinancialOperationalTax": {
        "amount": 0.0041,
        "currency": "BRL"
      },
      "additionalFinancialOperationalTax": {
        "amount": 0.38,
        "currency": "BRL"
      }
    }
  }'
```

## 🛠️ Desenvolvimento

### Executar Localmente
```bash
./mvnw quarkus:dev
```

### Executar Testes
```bash
./mvnw test
```

## 📚 Documentação Técnica

Para desenvolvedores interessados nos detalhes matemáticos e algoritmos:

- **[Cálculos Matemáticos](docs/CALCULATIONS.md)** - Fórmulas, fluxos e exemplos detalhados do Sistema PRICE

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/devio/
│   │       ├── component/
│   │       │   ├── calculator/
│   │       │   └── validations/
│   │       ├── domain/
│   │       │   ├── constant/
│   │       │   ├── enumeration/
│   │       │   ├── model/
│   │       │   ├── service/
│   │       │   └── usecase/
│   │       ├── entrypoint/
│   │       │   └── resource/
│   │       ├── generated/
│   │       │   ├── api/
│   │       │   └── dto/
│   │       └── infraestructure/
│   │           └── exception/
│   └── resources/
│       ├── openapi/
│       └── application.yaml
└── test/
    └── java/
        └── br/com/devio/
```