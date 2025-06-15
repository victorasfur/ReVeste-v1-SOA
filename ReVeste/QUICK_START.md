# Guia Rápido de Execução - ReVeste

## 🚀 Início Rápido

### 1. Executar Automaticamente (Recomendado)
```bash
cd ReVeste
./start-services.sh
```

### 2. Parar Todos os Serviços
```bash
./stop-services.sh
```

## 📋 Verificação do Sistema

Após executar `./start-services.sh`, aguarde cerca de 30 segundos e acesse:

### 🌐 Documentação das APIs (Swagger)
- User Service: http://localhost:8081/swagger-ui.html
- Betting Service: http://localhost:8082/swagger-ui.html
- Financial Simulation: http://localhost:8083/swagger-ui.html
- Behavioral Analysis: http://localhost:8084/swagger-ui.html
- Gamification: http://localhost:8085/swagger-ui.html
- Education Service: http://localhost:8086/swagger-ui.html

### 🗄️ Bancos de Dados H2
- User DB: http://localhost:8081/h2-console
- Betting DB: http://localhost:8082/h2-console
- Financial DB: http://localhost:8083/h2-console
- Behavioral DB: http://localhost:8084/h2-console
- Gamification DB: http://localhost:8085/h2-console
- Education DB: http://localhost:8086/h2-console

**Credenciais H2:**
- JDBC URL: `jdbc:h2:mem:[nome]db` (ex: `jdbc:h2:mem:userdb`)
- Username: `sa`
- Password: `password`

## 🧪 Teste Rápido das APIs

### 1. Criar um usuário
```bash
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d '{"username": "teste", "email": "teste@email.com", "password": "123456"}'
```

### 2. Registrar uma aposta
```bash
curl -X POST http://localhost:8082/api/bets \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "amount": 100.00, "description": "Teste de aposta"}'
```

### 3. Criar simulação financeira
```bash
curl -X POST http://localhost:8083/api/simulations \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "monthlyAmount": 100.00, "investmentType": "Tesouro Selic", "annualRate": 10.0, "years": 5}'
```

## 🔧 Solução de Problemas

### Java não encontrado
```bash
sudo apt-get update
sudo apt-get install openjdk-17-jdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

### Porta em uso
Se alguma porta estiver ocupada, edite o arquivo `application.properties` do serviço correspondente e altere a porta.

### Logs dos serviços
Os logs estão disponíveis em `./logs/[nome-do-servico].log`

## 📁 Estrutura do Projeto

```
ReVeste/
├── start-services.sh          # Script para iniciar todos os serviços
├── stop-services.sh           # Script para parar todos os serviços
├── README.md                  # Documentação completa
├── QUICK_START.md            # Este guia rápido
├── user-service/             # Microsserviço de usuários
├── betting-service/          # Microsserviço de apostas
├── financial-simulation-service/  # Microsserviço de simulações
├── behavioral-analysis-service/   # Microsserviço de análise comportamental
├── gamification-service/     # Microsserviço de gamificação
├── education-service/        # Microsserviço educativo
└── logs/                     # Logs dos serviços (criado automaticamente)
```

## ✅ Checklist de Funcionalidades

- [x] 6 Microsserviços implementados
- [x] APIs RESTful com Swagger
- [x] Banco de dados H2 configurado
- [x] Padrões SOA implementados
- [x] Tratamento de erros
- [x] Documentação completa
- [x] Scripts de automação
- [x] Comparação de padrões (REST, SOAP, JSON, XML, WSDL, UDDI)

---
**Desenvolvido para a disciplina de Arquitetura Orientada a Serviços e Web Services**

