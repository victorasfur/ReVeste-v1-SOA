# ReVeste - Plataforma de Educação Financeira e Prevenção ao Vício em Apostas

## Visão Geral

O ReVeste é uma plataforma inovadora que atua na interseção entre saúde financeira, prevenção ao vício em apostas e educação financeira prática. Desenvolvida seguindo os princípios de Arquitetura Orientada a Serviços (SOA) e implementada com Java 17+ e Spring Boot 3+, a solução tem como principal objetivo conscientizar os usuários sobre o impacto financeiro de suas apostas recorrentes, demonstrando comparativamente o que esses valores poderiam ter rendido se investidos de forma inteligente em ativos financeiros reais ao longo do tempo.

## 🎨 **NOVIDADE: Interface Web Moderna**

O projeto agora inclui um **frontend React moderno e responsivo** que oferece uma experiência de usuário completa e intuitiva!

### Características do Frontend:
- **Design Moderno**: Interface clean com paleta de cores verde/azul representando crescimento financeiro
- **Responsivo**: Funciona perfeitamente em desktop, tablet e mobile
- **Dashboard Interativo**: Gráficos e métricas em tempo real
- **Simulador Financeiro**: Calculadora interativa para comparar apostas vs investimentos
- **Navegação Intuitiva**: Menu lateral com todas as funcionalidades
- **Componentes Reutilizáveis**: Construído com shadcn/ui e Tailwind CSS

## Arquitetura do Sistema

O projeto foi desenvolvido seguindo uma arquitetura de microsserviços, onde cada serviço é independente e reutilizável, implementando os padrões REST, SOAP, JSON, XML, WSDL e UDDI conforme especificado nos requisitos acadêmicos.

### Microsserviços Implementados

1. **User Service** (Porta 8081) - Gerenciamento de usuários
2. **Betting Service** (Porta 8082) - Registro de apostas
3. **Financial Simulation Service** (Porta 8083) - Simulações financeiras
4. **Behavioral Analysis Service** (Porta 8084) - Análise comportamental
5. **Gamification Service** (Porta 8085) - Sistema de gamificação
6. **Education Service** (Porta 8086) - Conteúdo educativo

### Frontend React

7. **Frontend Web** (Porta 5173) - Interface de usuário moderna

## Tecnologias Utilizadas

### Backend
- **Linguagem**: Java 17+
- **Framework**: Spring Boot 3.5.0
- **Banco de Dados**: H2 Database (em memória para desenvolvimento)
- **Documentação API**: Swagger/OpenAPI 3
- **Build Tool**: Maven
- **Padrões**: REST, JSON, XML
- **Arquitetura**: Microsserviços (SOA)

### Frontend
- **Framework**: React 18
- **Build Tool**: Vite
- **Estilização**: Tailwind CSS + shadcn/ui
- **Gráficos**: Recharts
- **Ícones**: Lucide React
- **Roteamento**: React Router
- **Animações**: Framer Motion

## Pré-requisitos

- Java 17 ou superior
- Node.js 18 ou superior
- Maven 3.6 ou superior
- Git (para clonar o repositório)

## 🚀 **Execução Rápida - Sistema Completo**

### **Método Automático (Recomendado)**

```bash
# Executar sistema completo (Backend + Frontend)
./start-full-system.sh

# Parar sistema completo
./stop-full-system.sh
```

### **Acesso ao Sistema:**

#### **🌐 Frontend (Interface Principal)**
- **Aplicação Web**: http://localhost:5173

#### **📋 Backend APIs (Swagger)**
- **User Service**: http://localhost:8081/swagger-ui.html
- **Betting Service**: http://localhost:8082/swagger-ui.html
- **Financial Simulation**: http://localhost:8083/swagger-ui.html
- **Behavioral Analysis**: http://localhost:8084/swagger-ui.html
- **Gamification**: http://localhost:8085/swagger-ui.html
- **Education Service**: http://localhost:8086/swagger-ui.html

#### **🗄️ Bancos de Dados H2**
- **User DB**: http://localhost:8081/h2-console
- **Betting DB**: http://localhost:8082/h2-console
- **Financial DB**: http://localhost:8083/h2-console
- **Behavioral DB**: http://localhost:8084/h2-console
- **Gamification DB**: http://localhost:8085/h2-console
- **Education DB**: http://localhost:8086/h2-console

**Credenciais H2:**
- JDBC URL: `jdbc:h2:mem:[nome_do_banco]db`
- Username: `sa`
- Password: `password`

## Instalação e Configuração Manual

### 1. Verificar Instalação do Java

```bash
java -version
```

Se o Java não estiver instalado, instale o OpenJDK 17:

```bash
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install openjdk-17-jdk

# CentOS/RHEL
sudo yum install java-17-openjdk-devel
```

### 2. Verificar Instalação do Node.js

```bash
node -v
npm -v
```

### 3. Configurar Variável de Ambiente

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

### 4. Executar Apenas Backend (Microsserviços)

```bash
# Usar script específico para backend
./start-services.sh

# Parar apenas backend
./stop-services.sh
```

### 5. Executar Apenas Frontend

```bash
cd reveste-frontend
pnpm install  # primeira vez
pnpm run dev --host
```

## Funcionalidades do Frontend

### 🏠 Dashboard
- **Métricas em Tempo Real**: Usuários, apostas, simulações, scores
- **Gráficos Interativos**: Comparação apostas vs investimentos
- **Alertas Comportamentais**: Notificações de padrões de risco
- **Ações Rápidas**: Acesso direto às principais funcionalidades

### 👥 Gestão de Usuários
- **Cadastro de Usuários**: Formulário completo com validação
- **Busca e Filtros**: Localização rápida de usuários
- **Perfis Detalhados**: Informações e scores de cada usuário
- **Estatísticas**: Métricas agregadas dos usuários

### 💰 Simulador Financeiro
- **Calculadora Interativa**: Sliders para valores e períodos
- **Tipos de Investimento**: Tesouro Selic, CDB, Ações, ETFs
- **Gráficos de Projeção**: Visualização do crescimento ao longo do tempo
- **Comparação Educativa**: Apostas vs Investimentos lado a lado

### 🎯 Outras Funcionalidades
- **Apostas**: Registro e monitoramento (em desenvolvimento)
- **Gamificação**: Sistema de pontos e conquistas (em desenvolvimento)
- **Educação**: Conteúdo educativo financeiro (em desenvolvimento)

## Testando as APIs

### Exemplos de Uso com cURL

#### 1. Criar um usuário
```bash
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "joao",
    "email": "joao@email.com",
    "password": "123456"
  }'
```

#### 2. Registrar uma aposta
```bash
curl -X POST http://localhost:8082/api/bets \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "amount": 250.00,
    "description": "Aposta no jogo de futebol"
  }'
```

#### 3. Criar uma simulação financeira
```bash
curl -X POST http://localhost:8083/api/simulations \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "monthlyAmount": 250.00,
    "investmentType": "Tesouro Selic",
    "annualRate": 10.0,
    "years": 5
  }'
```

## Funcionalidades Implementadas

### 1. User Service
- Cadastro de usuários
- Listagem de usuários
- Busca por ID
- Atualização de dados
- Exclusão de usuários

### 2. Betting Service
- Registro de apostas
- Listagem de apostas por usuário
- Histórico completo de apostas
- Atualização e exclusão de registros

### 3. Financial Simulation Service
- Simulações de investimento com juros compostos
- Comparação de diferentes tipos de investimento
- Projeções para 5, 10 e 20 anos
- Cálculos automáticos de rentabilidade

### 4. Behavioral Analysis Service
- Detecção de padrões de risco
- Alertas automáticos para apostas de alto valor
- Classificação de severidade (LOW, MEDIUM, HIGH)
- Histórico de alertas por usuário

### 5. Gamification Service
- Score de inteligência financeira
- Sistema de pontos e níveis
- Badges de conquistas
- Recompensas por boas decisões

### 6. Education Service
- Conteúdo educativo categorizado
- Diferentes níveis de dificuldade
- Tempo estimado de leitura
- Inicialização com conteúdo padrão

### 7. Frontend React
- Interface moderna e responsiva
- Dashboard com métricas e gráficos
- Simulador financeiro interativo
- Gestão de usuários
- Integração com todos os microsserviços

## Padrões e Boas Práticas Implementadas

### Arquitetura Orientada a Serviços (SOA)
- Organização modular baseada em serviços independentes e reutilizáveis
- Separação clara entre camadas de apresentação, serviço e dados
- Implementação de padrões de projeto como DAO, DTO, Factory, Facade e MVC

### Padrões REST e HTTP
- Uso adequado dos verbos HTTP (GET, POST, PUT, DELETE)
- Códigos de status HTTP apropriados (200, 201, 404, 500)
- APIs RESTful bem estruturadas

### Frontend Moderno
- Componentes reutilizáveis e bem estruturados
- Estado gerenciado adequadamente
- Responsividade para todos os dispositivos
- Experiência de usuário otimizada

### Tratamento de Erros
- Tratamento adequado de erros e exceções nos serviços
- Respostas consistentes para diferentes cenários
- Validação de dados de entrada

### Documentação
- Documentação completa das APIs com Swagger/OpenAPI
- Anotações detalhadas em cada endpoint
- Exemplos de uso e códigos de resposta

## Estrutura do Projeto

```
ReVeste/
├── user-service/                    # Microsserviço de usuários
├── betting-service/                 # Microsserviço de apostas
├── financial-simulation-service/    # Microsserviço de simulações
├── behavioral-analysis-service/     # Microsserviço de análise comportamental
├── gamification-service/           # Microsserviço de gamificação
├── education-service/              # Microsserviço educativo
├── reveste-frontend/               # Frontend React
│   ├── src/
│   │   ├── components/             # Componentes reutilizáveis
│   │   ├── pages/                  # Páginas da aplicação
│   │   ├── services/               # Integração com APIs
│   │   └── assets/                 # Recursos estáticos
│   ├── package.json
│   └── vite.config.js
├── start-full-system.sh           # Script para iniciar sistema completo
├── stop-full-system.sh            # Script para parar sistema completo
├── start-services.sh              # Script para iniciar apenas backend
├── stop-services.sh               # Script para parar apenas backend
└── README.md                      # Esta documentação
```

## Solução de Problemas

### Erro: "JAVA_HOME not defined"
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

### Erro: "Node.js not found"
```bash
# Instalar Node.js 18+
curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
sudo apt-get install -y nodejs
```

### Porta já em uso
Se alguma porta estiver em uso, você pode alterar no arquivo `application.properties` de cada serviço:
```properties
server.port=8087  # Nova porta
```

### Problemas de compilação
Certifique-se de que o Maven está instalado:
```bash
mvn -version
```

### Frontend não carrega
```bash
cd reveste-frontend
pnpm install
pnpm run dev --host
```

## Contribuição

Este projeto foi desenvolvido como parte de um trabalho acadêmico sobre Arquitetura Orientada a Serviços e Web Services, implementando os conceitos e padrões estudados em sala de aula.

## Licença

Este projeto é desenvolvido para fins educacionais e acadêmicos.

---

**Desenvolvido por**: Victor Asfur, Fabrizio Maia, João Pedro Marques, Vitor Shimizu e André Sóler
**Disciplina**: Arquitetura Orientada a Serviços e Web Services  
**Data**: Junho 2025  
**Versão**: 1.0 (com Frontend)

