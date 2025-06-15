#!/bin/bash

# Script para executar o sistema completo ReVeste (Backend + Frontend)
# Autor: Manus AI
# Data: Junho 2025

echo "=============================================="
echo "    ReVeste - Sistema Completo (Full Stack)  "
echo "=============================================="

# Configurar JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

# Verificar se o Java está instalado
if ! command -v java &> /dev/null; then
    echo "❌ Java não encontrado. Instalando Java 17..."
    sudo apt-get update
    sudo apt-get install -y openjdk-17-jdk
    export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
fi

echo "✅ Java configurado: $(java -version 2>&1 | head -n 1)"

# Verificar se o Node.js está instalado
if ! command -v node &> /dev/null; then
    echo "❌ Node.js não encontrado. Por favor, instale o Node.js 18+ e tente novamente."
    exit 1
fi

echo "✅ Node.js configurado: $(node -v)"

# Função para compilar um serviço
compile_service() {
    local service_name=$1
    echo "🔨 Compilando $service_name..."
    cd $service_name
    ./mvnw clean compile -q
    if [ $? -eq 0 ]; then
        echo "✅ $service_name compilado com sucesso"
    else
        echo "❌ Erro ao compilar $service_name"
        exit 1
    fi
    cd ..
}

# Compilar todos os microsserviços
echo ""
echo "📦 Compilando microsserviços backend..."
compile_service "user-service"
compile_service "betting-service"
compile_service "financial-simulation-service"
compile_service "behavioral-analysis-service"
compile_service "gamification-service"
compile_service "education-service"

# Compilar frontend
echo ""
echo "🎨 Preparando frontend..."
cd reveste-frontend
if [ ! -d "node_modules" ]; then
    echo "📦 Instalando dependências do frontend..."
    pnpm install
fi
echo "✅ Frontend preparado"
cd ..

echo ""
echo "🚀 Iniciando sistema completo..."
echo "   Aguarde alguns segundos para cada serviço inicializar..."

# Função para iniciar um serviço em background
start_service() {
    local service_name=$1
    local port=$2
    echo "🌐 Iniciando $service_name na porta $port..."
    cd $service_name
    nohup ./mvnw spring-boot:run > ../logs/$service_name.log 2>&1 &
    echo $! > ../logs/$service_name.pid
    cd ..
    sleep 3
}

# Criar diretório de logs
mkdir -p logs

# Iniciar todos os microsserviços
start_service "user-service" "8081"
start_service "betting-service" "8082"
start_service "financial-simulation-service" "8083"
start_service "behavioral-analysis-service" "8084"
start_service "gamification-service" "8085"
start_service "education-service" "8086"

echo ""
echo "🎨 Iniciando frontend React..."
cd reveste-frontend
nohup pnpm run dev --host > ../logs/frontend.log 2>&1 &
echo $! > ../logs/frontend.pid
cd ..

echo ""
echo "⏳ Aguardando inicialização completa do sistema..."
sleep 20

echo ""
echo "🎉 Sistema ReVeste iniciado com sucesso!"
echo ""
echo "🌐 FRONTEND (Interface Principal):"
echo "   • Aplicação Web:               http://localhost:5173"
echo ""
echo "📋 BACKEND APIs (Swagger):"
echo "   • User Service:                http://localhost:8081/swagger-ui.html"
echo "   • Betting Service:             http://localhost:8082/swagger-ui.html"
echo "   • Financial Simulation:        http://localhost:8083/swagger-ui.html"
echo "   • Behavioral Analysis:         http://localhost:8084/swagger-ui.html"
echo "   • Gamification Service:        http://localhost:8085/swagger-ui.html"
echo "   • Education Service:           http://localhost:8086/swagger-ui.html"
echo ""
echo "🗄️  BANCOS DE DADOS H2:"
echo "   • User DB:                     http://localhost:8081/h2-console"
echo "   • Betting DB:                  http://localhost:8082/h2-console"
echo "   • Financial DB:                http://localhost:8083/h2-console"
echo "   • Behavioral DB:               http://localhost:8084/h2-console"
echo "   • Gamification DB:             http://localhost:8085/h2-console"
echo "   • Education DB:                http://localhost:8086/h2-console"
echo ""
echo "🔑 Credenciais H2:"
echo "   • JDBC URL: jdbc:h2:mem:[nome]db"
echo "   • Username: sa"
echo "   • Password: password"
echo ""
echo "📝 Logs dos serviços estão em: ./logs/"
echo ""
echo "⚠️  Para parar todo o sistema, execute: ./stop-full-system.sh"
echo ""
echo "=============================================="

