# Sistema PRICE - Cálculos Matemáticos

Documentação técnica dos cálculos realizados pela API de empréstimos.

## 📊 Visão Geral

O Sistema PRICE (Sistema Francês) gera parcelas fixas onde:
- **Parcela**: Valor fixo durante todo o financiamento
- **Juros**: Decrescem a cada parcela
- **Amortização**: Cresce a cada parcela

## 🧮 Fórmulas Principais

### 1. Valor Total Financiado (TF)
```
TF = VS + Seguros + Tarifas + IOF

Onde:
- VS = Valor Solicitado
- Seguros = Valor do seguro (se financiado)
- Tarifas = Valor das tarifas (se financiado)
- IOF = Imposto sobre Operações Financeiras
```

### 2. Parcela Fixa (PMT)
```
PMT = (TF × r) ÷ (1 - (1+r)^-n)

Onde:
- TF = Valor Total Financiado
- r = Taxa de juros mensal (decimal)
- n = Número de parcelas
```

### 3. Cálculos por Parcela
```
Juros(i) = Saldo(i-1) × r
Amortização(i) = PMT - Juros(i)
Saldo(i) = Saldo(i-1) - Amortização(i)
```

## 💰 Cálculo do IOF (Imposto sobre Operações Financeiras)

### IOF Diário
```
IOF Diário = (P × t × d) ÷ 100

Onde:
- P = Principal (valor financiado)
- t = Taxa IOF diário (0,0041% por dia)
- d = Dias entre contrato e primeira parcela
```

### IOF Adicional
```
IOF Adicional = (P × t) ÷ 100

Onde:
- P = Principal (valor financiado)  
- t = Taxa IOF adicional (0,38% fixo)
```

### IOF Total
```
IOF Total = IOF Diário + IOF Adicional
```

## 📋 Fluxo de Cálculo

### Etapa 1: Preparação Inicial
1. **Valor Solicitado**: R$ 50.000,00
2. **Seguro Financiado**: R$ 1.000,00
3. **Taxa Financiada**: R$ 500,00
4. **Valor Inicial**: R$ 51.500,00

### Etapa 2: Cálculo do IOF
1. **IOF Diário**: (51.500 × 0,0041 × 30) ÷ 100 = R$ 6,33
2. **IOF Adicional**: (51.500 × 0,38) ÷ 100 = R$ 195,70
3. **IOF Total**: 6,33 + 195,70 = R$ 202,03

### Etapa 3: Valor Final Financiado
```
Valor Total Financiado = 51.500,00 + 202,03 = R$ 51.702,03
```

### Etapa 4: Cálculo da Parcela
```
PMT = (51.702,03 × 0,025) ÷ (1 - (1,025)^-24)
PMT = 1.292,55 ÷ 0,5919
PMT = R$ 2.184,65
```

### Etapa 5: Tabela de Parcelas
| Parcela | Saldo Inicial | Juros | Amortização | Parcela | Saldo Final |
|---------|---------------|-------|-------------|---------|-------------|
| 1 | 51.702,03 | 1.292,55 | 892,10 | 2.184,65 | 50.809,93 |
| 2 | 50.809,93 | 1.270,25 | 914,40 | 2.184,65 | 49.895,53 |
| 3 | 49.895,53 | 1.247,39 | 937,26 | 2.184,65 | 48.958,27 |

## 🔄 Fluxo Completo com IOF

### Quando há IOF configurado:
1. **Calcula valor financiado inicial** (sem IOF)
2. **Calcula parcelas provisórias**
3. **Calcula IOF** baseado no valor financiado
4. **Soma IOF ao valor financiado**
5. **Recalcula parcelas** com o valor final
6. **Gera tabela definitiva**

### Quando não há IOF:
1. **Calcula valor financiado**
2. **Calcula parcelas**
3. **Gera tabela**

## 💰 Componentes Opcionais

### Seguros e Tarifas
- **À Vista**: Deduzido do valor liberado
- **Financiado**: Somado ao valor financiado

### Modalidades de Pagamento
- **FINANCED**: Valor é somado ao financiamento
- **UPFRONT**: Valor é pago à vista (deduzido do liberado)

## 🔢 Exemplo Completo

### Dados de Entrada
- Valor Solicitado: R$ 50.000,00
- Parcelas: 24
- Taxa Mensal: 2,5%
- Seguro: R$ 1.000,00 (Financiado)
- Taxa: R$ 500,00 (Financiado)
- IOF Diário: 0,0041%
- IOF Adicional: 0,38%
- Dias até primeira parcela: 30

### Cálculos Finais
1. **Valor Inicial**: 50.000 + 1.000 + 500 = R$ 51.500,00
2. **IOF Total**: 6,33 + 195,70 = R$ 202,03
3. **Valor Final Financiado**: R$ 51.702,03
4. **Parcela Fixa**: R$ 2.184,65
5. **Total Pago**: 2.184,65 × 24 = R$ 52.431,60
6. **Juros + IOF**: 52.431,60 - 50.000,00 = R$ 2.431,60

### Resumo da Primeira Parcela
- **Juros**: 51.702,03 × 0,025 = R$ 1.292,55
- **Amortização**: 2.184,65 - 1.292,55 = R$ 892,10
- **Saldo Restante**: 51.702,03 - 892,10 = R$ 50.809,93