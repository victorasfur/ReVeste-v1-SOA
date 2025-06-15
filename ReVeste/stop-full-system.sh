#!/bin/bash

# Script para parar todo o sistema ReVeste (Backend + Frontend)
# Autor: Manus AI
# Data: Junho 2025

echo "=============================================="
echo "    ReVeste - Parando Sistema Completo       "
echo "=============================================="

# Função para parar um serviço
stop_service() {
    local service_name=$1
    if [ -f "logs/$service_name.pid" ]; then
        local pid=$(cat logs/$service_name.pid)
        if ps -p $pid > /dev/null; then
            echo "🛑 Parando $service_name (PID: $pid)..."
            kill $pid
            rm logs/$service_name.pid
        else
            echo "⚠️  $service_name já estava parado"
            rm -f logs/$service_name.pid
        fi
    else
        echo "⚠️  PID do $service_name não encontrado"
    fi
}

# Parar frontend
echo "🎨 Parando frontend..."
stop_service "frontend"

# Parar todos os microsserviços
echo ""
echo "🌐 Parando microsserviços..."
stop_service "user-service"
stop_service "betting-service"
stop_service "financial-simulation-service"
stop_service "behavioral-analysis-service"
stop_service "gamification-service"
stop_service "education-service"

# Aguardar um pouco para os processos terminarem
sleep 3

# Verificar se ainda há processos restantes
echo ""
echo "🔍 Verificando processos restantes..."

# Verificar processos Java Spring Boot
java_processes=$(ps aux | grep java | grep -E "808[1-6]" | grep -v grep)
if [ -n "$java_processes" ]; then
    echo "⚠️  Ainda há processos Java rodando:"
    echo "$java_processes"
    echo ""
    echo "💀 Forçando parada de todos os processos Java Spring Boot..."
    pkill -f "spring-boot:run"
fi

# Verificar processos Node.js/Vite
node_processes=$(ps aux | grep node | grep -E "vite|pnpm" | grep -v grep)
if [ -n "$node_processes" ]; then
    echo "⚠️  Ainda há processos Node.js rodando:"
    echo "$node_processes"
    echo ""
    echo "💀 Forçando parada de todos os processos Node.js..."
    pkill -f "pnpm run dev"
    pkill -f "vite"
fi

echo ""
echo "🧹 Limpando arquivos temporários..."
rm -f logs/*.pid

echo ""
echo "✅ Sistema ReVeste parado completamente!"
echo "   • Frontend: http://localhost:5173 (offline)"
echo "   • Backend APIs: http://localhost:808[1-6] (offline)"
echo ""
echo "=============================================="

