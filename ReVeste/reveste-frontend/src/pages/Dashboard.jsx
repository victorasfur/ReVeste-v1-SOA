import { useState, useEffect } from 'react'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '../components/ui/card'
import { Button } from '../components/ui/button'
import { Badge } from '../components/ui/badge'
import { Progress } from '../components/ui/progress'
import { 
  TrendingUp, 
  TrendingDown, 
  DollarSign, 
  Users, 
  AlertTriangle,
  Trophy,
  BookOpen,
  Target
} from 'lucide-react'
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, PieChart, Pie, Cell } from 'recharts'

const mockData = {
  totalUsers: 1247,
  totalBets: 3456,
  totalSimulations: 892,
  averageScore: 750,
  monthlyGrowth: 12.5,
  alerts: [
    { id: 1, type: 'HIGH', message: 'Usuário com apostas acima de R$ 500', time: '2 min atrás' },
    { id: 2, type: 'MEDIUM', message: 'Padrão de apostas frequentes detectado', time: '15 min atrás' },
    { id: 3, type: 'LOW', message: 'Nova simulação criada', time: '1 hora atrás' },
  ],
  chartData: [
    { month: 'Jan', apostas: 2400, investimentos: 4800 },
    { month: 'Fev', apostas: 1398, investimentos: 5200 },
    { month: 'Mar', apostas: 9800, investimentos: 6100 },
    { month: 'Abr', apostas: 3908, investimentos: 7200 },
    { month: 'Mai', apostas: 4800, investimentos: 8500 },
    { month: 'Jun', apostas: 3800, investimentos: 9200 },
  ],
  pieData: [
    { name: 'Tesouro Selic', value: 35, color: '#10B981' },
    { name: 'CDB', value: 25, color: '#3B82F6' },
    { name: 'Ações', value: 20, color: '#8B5CF6' },
    { name: 'ETFs', value: 20, color: '#F59E0B' },
  ]
}

export function Dashboard() {
  const [stats, setStats] = useState(mockData)

  const StatCard = ({ title, value, change, icon: Icon, trend }) => (
    <Card className="animate-slide-up">
      <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
        <CardTitle className="text-sm font-medium text-muted-foreground">
          {title}
        </CardTitle>
        <Icon className="h-4 w-4 text-muted-foreground" />
      </CardHeader>
      <CardContent>
        <div className="text-2xl font-bold">{value}</div>
        {change && (
          <p className="text-xs text-muted-foreground flex items-center mt-1">
            {trend === 'up' ? (
              <TrendingUp className="h-3 w-3 text-green-500 mr-1" />
            ) : (
              <TrendingDown className="h-3 w-3 text-red-500 mr-1" />
            )}
            {change}% em relação ao mês anterior
          </p>
        )}
      </CardContent>
    </Card>
  )

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="space-y-2">
        <h2 className="text-3xl font-bold tracking-tight text-gradient-reveste">
          Dashboard
        </h2>
        <p className="text-muted-foreground">
          Visão geral da plataforma ReVeste e métricas principais
        </p>
      </div>

      {/* Stats Grid */}
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
        <StatCard
          title="Total de Usuários"
          value={stats.totalUsers.toLocaleString()}
          change={stats.monthlyGrowth}
          icon={Users}
          trend="up"
        />
        <StatCard
          title="Apostas Registradas"
          value={stats.totalBets.toLocaleString()}
          change={-8.2}
          icon={DollarSign}
          trend="down"
        />
        <StatCard
          title="Simulações Criadas"
          value={stats.totalSimulations.toLocaleString()}
          change={15.3}
          icon={TrendingUp}
          trend="up"
        />
        <StatCard
          title="Score Médio"
          value={stats.averageScore}
          change={5.7}
          icon={Trophy}
          trend="up"
        />
      </div>

      {/* Charts Row */}
      <div className="grid gap-4 md:grid-cols-2">
        {/* Line Chart */}
        <Card className="animate-slide-up">
          <CardHeader>
            <CardTitle>Apostas vs Investimentos</CardTitle>
            <CardDescription>
              Comparação mensal entre valores apostados e investidos
            </CardDescription>
          </CardHeader>
          <CardContent>
            <ResponsiveContainer width="100%" height={300}>
              <LineChart data={stats.chartData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="month" />
                <YAxis />
                <Tooltip 
                  formatter={(value, name) => [
                    `R$ ${value.toLocaleString()}`, 
                    name === 'apostas' ? 'Apostas' : 'Investimentos'
                  ]}
                />
                <Line 
                  type="monotone" 
                  dataKey="apostas" 
                  stroke="#EF4444" 
                  strokeWidth={2}
                  name="apostas"
                />
                <Line 
                  type="monotone" 
                  dataKey="investimentos" 
                  stroke="#10B981" 
                  strokeWidth={2}
                  name="investimentos"
                />
              </LineChart>
            </ResponsiveContainer>
          </CardContent>
        </Card>

        {/* Pie Chart */}
        <Card className="animate-slide-up">
          <CardHeader>
            <CardTitle>Tipos de Investimento</CardTitle>
            <CardDescription>
              Distribuição dos tipos de investimento simulados
            </CardDescription>
          </CardHeader>
          <CardContent>
            <ResponsiveContainer width="100%" height={300}>
              <PieChart>
                <Pie
                  data={stats.pieData}
                  cx="50%"
                  cy="50%"
                  innerRadius={60}
                  outerRadius={100}
                  paddingAngle={5}
                  dataKey="value"
                >
                  {stats.pieData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={entry.color} />
                  ))}
                </Pie>
                <Tooltip formatter={(value) => [`${value}%`, 'Participação']} />
              </PieChart>
            </ResponsiveContainer>
            <div className="grid grid-cols-2 gap-2 mt-4">
              {stats.pieData.map((item, index) => (
                <div key={index} className="flex items-center space-x-2">
                  <div 
                    className="w-3 h-3 rounded-full" 
                    style={{ backgroundColor: item.color }}
                  />
                  <span className="text-sm text-muted-foreground">
                    {item.name} ({item.value}%)
                  </span>
                </div>
              ))}
            </div>
          </CardContent>
        </Card>
      </div>

      {/* Bottom Row */}
      <div className="grid gap-4 md:grid-cols-3">
        {/* Alerts */}
        <Card className="animate-slide-up md:col-span-2">
          <CardHeader>
            <CardTitle className="flex items-center space-x-2">
              <AlertTriangle className="h-5 w-5" />
              <span>Alertas Recentes</span>
            </CardTitle>
            <CardDescription>
              Últimas notificações do sistema de análise comportamental
            </CardDescription>
          </CardHeader>
          <CardContent>
            <div className="space-y-3">
              {stats.alerts.map((alert) => (
                <div key={alert.id} className="flex items-center justify-between p-3 rounded-lg border">
                  <div className="flex items-center space-x-3">
                    <Badge 
                      variant={alert.type === 'HIGH' ? 'destructive' : alert.type === 'MEDIUM' ? 'default' : 'secondary'}
                    >
                      {alert.type}
                    </Badge>
                    <span className="text-sm">{alert.message}</span>
                  </div>
                  <span className="text-xs text-muted-foreground">{alert.time}</span>
                </div>
              ))}
            </div>
            <Button variant="outline" className="w-full mt-4">
              Ver Todos os Alertas
            </Button>
          </CardContent>
        </Card>

        {/* Quick Actions */}
        <Card className="animate-slide-up">
          <CardHeader>
            <CardTitle className="flex items-center space-x-2">
              <Target className="h-5 w-5" />
              <span>Ações Rápidas</span>
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-3">
            <Button className="w-full justify-start" variant="outline">
              <DollarSign className="h-4 w-4 mr-2" />
              Nova Simulação
            </Button>
            <Button className="w-full justify-start" variant="outline">
              <Users className="h-4 w-4 mr-2" />
              Cadastrar Usuário
            </Button>
            <Button className="w-full justify-start" variant="outline">
              <BookOpen className="h-4 w-4 mr-2" />
              Conteúdo Educativo
            </Button>
            <Button className="w-full justify-start" variant="outline">
              <Trophy className="h-4 w-4 mr-2" />
              Ranking
            </Button>
          </CardContent>
        </Card>
      </div>

      {/* Score Progress */}
      <Card className="animate-slide-up">
        <CardHeader>
          <CardTitle>Progresso da Plataforma</CardTitle>
          <CardDescription>
            Métricas de engajamento e educação financeira
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="space-y-2">
            <div className="flex justify-between text-sm">
              <span>Usuários Ativos</span>
              <span>78%</span>
            </div>
            <Progress value={78} className="h-2" />
          </div>
          <div className="space-y-2">
            <div className="flex justify-between text-sm">
              <span>Conteúdo Educativo Consumido</span>
              <span>65%</span>
            </div>
            <Progress value={65} className="h-2" />
          </div>
          <div className="space-y-2">
            <div className="flex justify-between text-sm">
              <span>Simulações Realizadas</span>
              <span>82%</span>
            </div>
            <Progress value={82} className="h-2" />
          </div>
        </CardContent>
      </Card>
    </div>
  )
}

