# Calculadora de Empréstimos

API REST para cálculo de empréstimos com suporte aos sistemas PRICE e SAC, desenvolvida com Quarkus e preparada para deployment em AWS Lambda.

## 🚀 Funcionalidades

### Sistemas de Amortização
- **PRICE (Sistema Francês)**: Parcelas fixas com juros decrescentes e amortização crescente
- **SAC (Sistema de Amortização Constante)**: Amortização fixa com parcelas decrescentes

### Cálculos Matemáticos
- **XIRR**: Taxa Interna de Retorno usando método da bisseção
- **VPL**: Valor Presente Líquido das parcelas
- **Taxa de Juros**: Conversão entre taxas mensais e anuais

### Componentes Financeiros
- **Seguros**: Cálculo com pagamento à vista ou financiado
- **Taxas**: IOF diário e adicional
- **Tarifas**: Valores adicionais ao financiamento

## 📋 Estrutura da API

### Endpoint Principal
```
POST /calculator
```

### Parâmetros de Entrada

| Campo | Tipo | Obrigatório | Descrição |
|-------|------|-------------|-----------|
| `calculationType` | String | Sim | Tipo de cálculo: `PRICE` ou `SAC` |
| `installmentQuantity` | Integer | Sim | Quantidade de parcelas |
| `requestedAmount` | BigDecimal | Sim | Valor solicitado |
| `contractDate` | LocalDate | Sim | Data do contrato |
| `firstInstallmentDate` | LocalDate | Sim | Data da primeira parcela |
| `lastInstallmentDate` | LocalDate | Não | Data da última parcela |
| `monthlyInterestRate` | BigDecimal | Sim | Taxa de juros mensal (decimal) |
| `insurance` | Object | Não | Configurações do seguro |
| `fee` | Object | Não | Configurações de tarifas |
| `tax` | Object | Não | Configurações de impostos |

### Estrutura dos Objetos Opcionais

#### Insurance/Fee
```json
{
  "paymentType": "FINANCED|UPFRONT",
  "totalAmount": 1000.00
}
```

#### Tax
```json
{
  "paymentType": "FINANCED|UPFRONT",
  "dailyFinancialOperationalTax": 0.0041,
  "additionalFinancialOperationalTax": 0.38
}
```

### Resposta da API

```json
{
  "requestedAmount": 50000.00,
  "contractDate": "2024-01-15",
  "firstInstallmentDate": "2024-02-15",
  "monthlyInterestRate": 2.5,
  "annualInterestRate": 34.49,
  "totalFinancedAmount": 52000.00,
  "totalGrantedAmount": 50000.00,
  "totalLoanAmount": 65432.10,
  "installments": [
    {
      "installmentNumber": 1,
      "dueDate": "2024-02-15",
      "installmentValue": 2176.32,
      "interestAmount": 1300.00,
      "amortizationAmount": 876.32,
      "balanceAmount": 51123.68,
      "presentValue": 2123.24
    }
  ]
}
```

## 🛠️ Como Usar

### Pré-requisitos
- Java 21+
- Maven 3.8+
- Docker (opcional, para build nativo)

### Executar Localmente

1. **Clone o repositório**
```bash
git clone <repository-url>
cd app-loan-calculator
```

2. **Execute a aplicação**
```bash
./mvnw quarkus:dev
```

3. **Acesse a API**
```
http://localhost:8080/calculator
```

### Build para Produção

**JAR tradicional:**
```bash
./mvnw clean package
```

**Imagem nativa:**
```bash
./mvnw clean package -Pnative
```

## 📝 Exemplos de Uso

### Exemplo 1: Financiamento PRICE Simples

```bash
curl -X POST http://localhost:8080/calculator \
  -H "Content-Type: application/json" \
  -d '{
    "calculationType": "PRICE",
    "installmentQuantity": 24,
    "requestedAmount": 50000.00,
    "contractDate": "2024-01-15",
    "firstInstallmentDate": "2024-02-15",
    "monthlyInterestRate": 0.025
  }'
```

### Exemplo 2: Financiamento SAC com Seguro

```bash
curl -X POST http://localhost:8080/calculator \
  -H "Content-Type: application/json" \
  -d '{
    "calculationType": "SAC",
    "installmentQuantity": 36,
    "requestedAmount": 100000.00,
    "contractDate": "2024-01-15",
    "firstInstallmentDate": "2024-02-15",
    "monthlyInterestRate": 0.019,
    "insurance": {
      "paymentType": "FINANCED",
      "totalAmount": 2500.00
    }
  }'
```

### Exemplo 3: Financiamento Completo com Todos os Componentes

```bash
curl -X POST http://localhost:8080/calculator \
  -H "Content-Type: application/json" \
  -d '{
    "calculationType": "PRICE",
    "installmentQuantity": 48,
    "requestedAmount": 75000.00,
    "contractDate": "2024-01-15",
    "firstInstallmentDate": "2024-02-15",
    "monthlyInterestRate": 0.022,
    "insurance": {
      "paymentType": "FINANCED",
      "totalAmount": 1800.00
    },
    "fee": {
      "paymentType": "UPFRONT",
      "totalAmount": 500.00
    },
    "tax": {
      "paymentType": "FINANCED",
      "dailyFinancialOperationalTax": 0.0041,
      "additionalFinancialOperationalTax": 0.38
    }
  }'
```

## 🏗️ Arquitetura

### Padrões Utilizados
- **Chain of Responsibility**: Para processamento sequencial dos cálculos
- **Command Pattern**: Para encapsulamento das operações matemáticas
- **Template Method**: Para geração do plano de pagamento
- **Factory Pattern**: Para criação de calculadoras específicas

### Estrutura de Pacotes
```
br.com.devio
├── component.calculator     # Lógica de cálculo
├── domain                   # Modelos e regras de negócio
└── entrypoint              # Controllers e DTOs
```

## 🚀 Deploy AWS Lambda

A aplicação está configurada para deployment em AWS Lambda:

1. **Build do pacote**
```bash
./mvnw clean package
```

2. **Deploy usando SAM**
```bash
sam deploy --template-file target/sam.jvm.yaml
```

## 🧪 Testes

```bash
# Executar testes unitários
./mvnw test

# Executar com cobertura
./mvnw clean verify
```

## 📊 Relatórios

- **Cobertura de código**: `target/site/jacoco/index.html`
- **Testes**: `target/surefire-reports/`

## 🔧 Configurações

### application.yaml
```yaml
quarkus:
  lambda:
    enabled: false  # Para desenvolvimento local
  http:
    enable-compression: true
    limits:
      max-body-size: 10M
```

## 📄 Licença

Este projeto está sob licença MIT.