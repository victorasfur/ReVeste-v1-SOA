import axios from 'axios'

// Configuração base da API
const API_BASE_URL = 'http://localhost'

// Criar instâncias do axios para cada microsserviço
const createApiInstance = (port) => {
  return axios.create({
    baseURL: `${API_BASE_URL}:${port}`,
    timeout: 10000,
    headers: {
      'Content-Type': 'application/json',
    },
  })
}

// Instâncias para cada microsserviço
const userApi = createApiInstance(8081)
const bettingApi = createApiInstance(8082)
const simulationApi = createApiInstance(8083)
const behavioralApi = createApiInstance(8084)
const gamificationApi = createApiInstance(8085)
const educationApi = createApiInstance(8086)

// Interceptors para tratamento de erros
const setupInterceptors = (api, serviceName) => {
  api.interceptors.response.use(
    (response) => response,
    (error) => {
      console.error(`Erro no ${serviceName}:`, error)
      if (error.response) {
        // Erro de resposta do servidor
        throw new Error(`${serviceName}: ${error.response.data.message || error.response.statusText}`)
      } else if (error.request) {
        // Erro de rede
        throw new Error(`${serviceName}: Serviço indisponível`)
      } else {
        // Erro de configuração
        throw new Error(`${serviceName}: Erro interno`)
      }
    }
  )
}

// Configurar interceptors para todos os serviços
setupInterceptors(userApi, 'User Service')
setupInterceptors(bettingApi, 'Betting Service')
setupInterceptors(simulationApi, 'Simulation Service')
setupInterceptors(behavioralApi, 'Behavioral Service')
setupInterceptors(gamificationApi, 'Gamification Service')
setupInterceptors(educationApi, 'Education Service')

// Serviços de API

// User Service
export const userService = {
  // Listar todos os usuários
  getAll: () => userApi.get('/api/users'),
  
  // Buscar usuário por ID
  getById: (id) => userApi.get(`/api/users/${id}`),
  
  // Criar novo usuário
  create: (userData) => userApi.post('/api/users', userData),
  
  // Atualizar usuário
  update: (id, userData) => userApi.put(`/api/users/${id}`, userData),
  
  // Deletar usuário
  delete: (id) => userApi.delete(`/api/users/${id}`)
}

// Betting Service
export const bettingService = {
  // Listar todas as apostas
  getAll: () => bettingApi.get('/api/bets'),
  
  // Buscar apostas por usuário
  getByUser: (userId) => bettingApi.get(`/api/bets/user/${userId}`),
  
  // Criar nova aposta
  create: (betData) => bettingApi.post('/api/bets', betData),
  
  // Atualizar aposta
  update: (id, betData) => bettingApi.put(`/api/bets/${id}`, betData),
  
  // Deletar aposta
  delete: (id) => bettingApi.delete(`/api/bets/${id}`)
}

// Simulation Service
export const simulationService = {
  // Listar todas as simulações
  getAll: () => simulationApi.get('/api/simulations'),
  
  // Buscar simulações por usuário
  getByUser: (userId) => simulationApi.get(`/api/simulations/user/${userId}`),
  
  // Criar nova simulação
  create: (simulationData) => simulationApi.post('/api/simulations', simulationData),
  
  // Atualizar simulação
  update: (id, simulationData) => simulationApi.put(`/api/simulations/${id}`, simulationData),
  
  // Deletar simulação
  delete: (id) => simulationApi.delete(`/api/simulations/${id}`)
}

// Behavioral Analysis Service
export const behavioralService = {
  // Listar todos os alertas
  getAlerts: () => behavioralApi.get('/api/behavioral-alerts'),
  
  // Buscar alertas por usuário
  getAlertsByUser: (userId) => behavioralApi.get(`/api/behavioral-alerts/user/${userId}`),
  
  // Analisar comportamento
  analyze: (userId, betAmount) => behavioralApi.post(`/api/behavioral-alerts/analyze/${userId}?betAmount=${betAmount}`),
  
  // Criar alerta manual
  createAlert: (alertData) => behavioralApi.post('/api/behavioral-alerts', alertData)
}

// Gamification Service
export const gamificationService = {
  // Buscar score do usuário
  getUserScore: (userId) => gamificationApi.get(`/api/gamification/score/user/${userId}`),
  
  // Listar todos os scores
  getAllScores: () => gamificationApi.get('/api/gamification/scores'),
  
  // Adicionar pontos por boa decisão
  addGoodDecisionPoints: (userId) => gamificationApi.post(`/api/gamification/score/user/${userId}/good-decision`),
  
  // Subtrair pontos por má decisão
  subtractBadDecisionPoints: (userId) => gamificationApi.post(`/api/gamification/score/user/${userId}/bad-decision`),
  
  // Atualizar score
  updateScore: (userId, scoreData) => gamificationApi.put(`/api/gamification/score/user/${userId}`, scoreData)
}

// Education Service
export const educationService = {
  // Listar todo o conteúdo
  getAll: () => educationApi.get('/api/education'),
  
  // Buscar conteúdo por categoria
  getByCategory: (category) => educationApi.get(`/api/education/category/${category}`),
  
  // Buscar conteúdo por dificuldade
  getByDifficulty: (difficulty) => educationApi.get(`/api/education/difficulty/${difficulty}`),
  
  // Inicializar conteúdo padrão
  initialize: () => educationApi.post('/api/education/initialize'),
  
  // Criar novo conteúdo
  create: (contentData) => educationApi.post('/api/education', contentData),
  
  // Atualizar conteúdo
  update: (id, contentData) => educationApi.put(`/api/education/${id}`, contentData),
  
  // Deletar conteúdo
  delete: (id) => educationApi.delete(`/api/education/${id}`)
}

// Função utilitária para verificar se os serviços estão online
export const checkServicesHealth = async () => {
  const services = [
    { name: 'User Service', port: 8081, api: userApi },
    { name: 'Betting Service', port: 8082, api: bettingApi },
    { name: 'Simulation Service', port: 8083, api: simulationApi },
    { name: 'Behavioral Service', port: 8084, api: behavioralApi },
    { name: 'Gamification Service', port: 8085, api: gamificationApi },
    { name: 'Education Service', port: 8086, api: educationApi }
  ]

  const healthStatus = {}

  for (const service of services) {
    try {
      await service.api.get('/actuator/health')
      healthStatus[service.name] = { status: 'online', port: service.port }
    } catch (error) {
      healthStatus[service.name] = { status: 'offline', port: service.port, error: error.message }
    }
  }

  return healthStatus
}

// Função para simular dados quando os serviços estão offline
export const getMockData = () => {
  return {
    users: [
      { id: 1, username: 'joao_silva', email: 'joao@email.com', createdAt: '2024-01-15', score: 850 },
      { id: 2, username: 'maria_santos', email: 'maria@email.com', createdAt: '2024-02-20', score: 720 },
      { id: 3, username: 'pedro_oliveira', email: 'pedro@email.com', createdAt: '2024-03-10', score: 950 },
    ],
    bets: [
      { id: 1, userId: 1, amount: 250.00, description: 'Aposta no futebol', betDate: '2024-06-01' },
      { id: 2, userId: 2, amount: 150.00, description: 'Aposta no basquete', betDate: '2024-06-02' },
    ],
    simulations: [
      { id: 1, userId: 1, monthlyAmount: 250.00, investmentType: 'Tesouro Selic', annualRate: 10.0, years: 5, projectedValue: 195000.00 },
    ],
    alerts: [
      { id: 1, userId: 1, type: 'HIGH', message: 'Apostas acima de R$ 500', alertDate: '2024-06-01' },
    ]
  }
}

