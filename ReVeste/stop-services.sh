#!/bin/bash

# Script para parar todos os microsserviços do ReVeste
# Autor: Manus AI
# Data: Junho 2025

echo "==================================="
echo "    ReVeste - Parando Sistema      "
echo "==================================="

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

# Parar todos os serviços
stop_service "user-service"
stop_service "betting-service"
stop_service "financial-simulation-service"
stop_service "behavioral-analysis-service"
stop_service "gamification-service"
stop_service "education-service"

# Aguardar um pouco para os processos terminarem
sleep 3

# Verificar se ainda há processos Java rodando na porta 808x
echo ""
echo "🔍 Verificando processos restantes..."
java_processes=$(ps aux | grep java | grep -E "808[1-6]" | grep -v grep)
if [ -n "$java_processes" ]; then
    echo "⚠️  Ainda há processos Java rodando:"
    echo "$java_processes"
    echo ""
    echo "💀 Forçando parada de todos os processos Java Spring Boot..."
    pkill -f "spring-boot:run"
else
    echo "✅ Todos os serviços foram parados com sucesso"
fi

echo ""
echo "🧹 Limpando arquivos temporários..."
rm -f logs/*.pid

echo ""
echo "✅ Sistema ReVeste parado completamente!"
echo "==================================="

