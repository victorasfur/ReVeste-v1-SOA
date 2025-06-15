import { useState } from 'react'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '../components/ui/card'
import { Button } from '../components/ui/button'
import { Input } from '../components/ui/input'
import { Label } from '../components/ui/label'
import { Slider } from '../components/ui/slider'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '../components/ui/select'
import { 
  Calculator, 
  TrendingUp, 
  DollarSign,
  Calendar,
  Target,
  PieChart
} from 'lucide-react'
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, BarChart, Bar } from 'recharts'

export function Simulations() {
  const [formData, setFormData] = useState({
    monthlyAmount: 250,
    investmentType: 'Tesouro Selic',
    annualRate: 10.0,
    years: 5
  })
  
  const [result, setResult] = useState(null)

  const investmentTypes = [
    { name: 'Tesouro Selic', rate: 10.0 },
    { name: 'CDB', rate: 12.0 },
    { name: 'LCI/LCA', rate: 9.5 },
    { name: 'Fundos DI', rate: 8.5 },
    { name: 'Ações', rate: 15.0 },
    { name: 'ETFs', rate: 13.0 }
  ]

  const calculateCompoundInterest = () => {
    const { monthlyAmount, annualRate, years } = formData
    const monthlyRate = annualRate / 100 / 12
    const totalMonths = years * 12
    
    // Fórmula de juros compostos para aportes mensais
    const futureValue = monthlyAmount * (((1 + monthlyRate) ** totalMonths - 1) / monthlyRate)
    const totalInvested = monthlyAmount * totalMonths
    const totalReturn = futureValue - totalInvested
    
    // Gerar dados para o gráfico
    const chartData = []
    for (let year = 1; year <= years; year++) {
      const months = year * 12
      const value = monthlyAmount * (((1 + monthlyRate) ** months - 1) / monthlyRate)
      const invested = monthlyAmount * months
      chartData.push({
        year: `Ano ${year}`,
        investido: invested,
        total: value,
        rendimento: value - invested
      })
    }

    setResult({
      futureValue,
      totalInvested,
      totalReturn,
      chartData,
      monthlyAmount,
      years
    })
  }

  const handleInvestmentTypeChange = (value) => {
    const investment = investmentTypes.find(inv => inv.name === value)
    setFormData({
      ...formData,
      investmentType: value,
      annualRate: investment?.rate || 10.0
    })
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <h2 className="text-3xl font-bold tracking-tight text-gradient-reveste">
          Simulações Financeiras
        </h2>
        <p className="text-muted-foreground">
          Compare o que suas apostas poderiam render se fossem investidas
        </p>
      </div>

      <div className="grid gap-6 lg:grid-cols-2">
        {/* Calculadora */}
        <Card className="animate-slide-up">
          <CardHeader>
            <CardTitle className="flex items-center space-x-2">
              <Calculator className="h-5 w-5" />
              <span>Calculadora de Investimentos</span>
            </CardTitle>
            <CardDescription>
              Simule o crescimento do seu dinheiro ao longo do tempo
            </CardDescription>
          </CardHeader>
          <CardContent className="space-y-6">
            <div className="space-y-2">
              <Label>Valor mensal: R$ {formData.monthlyAmount}</Label>
              <Slider
                value={[formData.monthlyAmount]}
                onValueChange={(value) => setFormData({...formData, monthlyAmount: value[0]})}
                max={2000}
                min={50}
                step={50}
                className="w-full"
              />
              <div className="flex justify-between text-xs text-muted-foreground">
                <span>R$ 50</span>
                <span>R$ 2.000</span>
              </div>
            </div>

            <div className="space-y-2">
              <Label htmlFor="investment-type">Tipo de Investimento</Label>
              <Select value={formData.investmentType} onValueChange={handleInvestmentTypeChange}>
                <SelectTrigger>
                  <SelectValue placeholder="Selecione o investimento" />
                </SelectTrigger>
                <SelectContent>
                  {investmentTypes.map((investment) => (
                    <SelectItem key={investment.name} value={investment.name}>
                      {investment.name} ({investment.rate}% a.a.)
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div className="space-y-2">
              <Label>Taxa anual: {formData.annualRate}%</Label>
              <Slider
                value={[formData.annualRate]}
                onValueChange={(value) => setFormData({...formData, annualRate: value[0]})}
                max={25}
                min={5}
                step={0.5}
                className="w-full"
              />
              <div className="flex justify-between text-xs text-muted-foreground">
                <span>5%</span>
                <span>25%</span>
              </div>
            </div>

            <div className="space-y-2">
              <Label>Período: {formData.years} anos</Label>
              <Slider
                value={[formData.years]}
                onValueChange={(value) => setFormData({...formData, years: value[0]})}
                max={30}
                min={1}
                step={1}
                className="w-full"
              />
              <div className="flex justify-between text-xs text-muted-foreground">
                <span>1 ano</span>
                <span>30 anos</span>
              </div>
            </div>

            <Button 
              onClick={calculateCompoundInterest}
              className="w-full bg-gradient-reveste hover:opacity-90"
            >
              <TrendingUp className="h-4 w-4 mr-2" />
              Calcular Simulação
            </Button>
          </CardContent>
        </Card>

        {/* Resultados */}
        {result && (
          <Card className="animate-slide-up">
            <CardHeader>
              <CardTitle className="flex items-center space-x-2">
                <Target className="h-5 w-5" />
                <span>Resultados da Simulação</span>
              </CardTitle>
              <CardDescription>
                Projeção para {result.years} anos com aportes de R$ {result.monthlyAmount}/mês
              </CardDescription>
            </CardHeader>
            <CardContent className="space-y-4">
              <div className="grid gap-4">
                <div className="p-4 rounded-lg bg-green-50 dark:bg-green-950 border border-green-200 dark:border-green-800">
                  <div className="flex items-center justify-between">
                    <span className="text-sm text-green-700 dark:text-green-300">Valor Final</span>
                    <DollarSign className="h-4 w-4 text-green-600" />
                  </div>
                  <div className="text-2xl font-bold text-green-800 dark:text-green-200">
                    R$ {result.futureValue.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}
                  </div>
                </div>

                <div className="p-4 rounded-lg bg-blue-50 dark:bg-blue-950 border border-blue-200 dark:border-blue-800">
                  <div className="flex items-center justify-between">
                    <span className="text-sm text-blue-700 dark:text-blue-300">Total Investido</span>
                    <Calendar className="h-4 w-4 text-blue-600" />
                  </div>
                  <div className="text-2xl font-bold text-blue-800 dark:text-blue-200">
                    R$ {result.totalInvested.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}
                  </div>
                </div>

                <div className="p-4 rounded-lg bg-purple-50 dark:bg-purple-950 border border-purple-200 dark:border-purple-800">
                  <div className="flex items-center justify-between">
                    <span className="text-sm text-purple-700 dark:text-purple-300">Rendimento</span>
                    <TrendingUp className="h-4 w-4 text-purple-600" />
                  </div>
                  <div className="text-2xl font-bold text-purple-800 dark:text-purple-200">
                    R$ {result.totalReturn.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}
                  </div>
                  <div className="text-sm text-purple-600 dark:text-purple-400">
                    +{((result.totalReturn / result.totalInvested) * 100).toFixed(1)}% de rentabilidade
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>
        )}
      </div>

      {/* Gráfico */}
      {result && (
        <Card className="animate-slide-up">
          <CardHeader>
            <CardTitle className="flex items-center space-x-2">
              <PieChart className="h-5 w-5" />
              <span>Evolução do Investimento</span>
            </CardTitle>
            <CardDescription>
              Crescimento ano a ano do seu investimento
            </CardDescription>
          </CardHeader>
          <CardContent>
            <ResponsiveContainer width="100%" height={400}>
              <BarChart data={result.chartData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="year" />
                <YAxis 
                  tickFormatter={(value) => `R$ ${(value / 1000).toFixed(0)}k`}
                />
                <Tooltip 
                  formatter={(value, name) => [
                    `R$ ${value.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}`,
                    name === 'investido' ? 'Total Investido' : 
                    name === 'rendimento' ? 'Rendimento' : 'Valor Total'
                  ]}
                />
                <Bar dataKey="investido" fill="#3B82F6" name="investido" />
                <Bar dataKey="rendimento" fill="#10B981" name="rendimento" />
              </BarChart>
            </ResponsiveContainer>
          </CardContent>
        </Card>
      )}

      {/* Comparação com Apostas */}
      <Card className="animate-slide-up">
        <CardHeader>
          <CardTitle>💡 Reflexão Importante</CardTitle>
          <CardDescription>
            Compare o potencial de crescimento dos seus recursos
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="grid gap-4 md:grid-cols-2">
            <div className="p-4 rounded-lg bg-red-50 dark:bg-red-950 border border-red-200 dark:border-red-800">
              <h3 className="font-semibold text-red-800 dark:text-red-200 mb-2">
                💸 Apostando R$ {formData.monthlyAmount}/mês
              </h3>
              <p className="text-sm text-red-700 dark:text-red-300">
                Em {formData.years} anos, você terá gasto <strong>R$ {(formData.monthlyAmount * formData.years * 12).toLocaleString('pt-BR')}</strong> 
                {' '}e provavelmente não terá nada para mostrar.
              </p>
            </div>
            
            <div className="p-4 rounded-lg bg-green-50 dark:bg-green-950 border border-green-200 dark:border-green-800">
              <h3 className="font-semibold text-green-800 dark:text-green-200 mb-2">
                📈 Investindo R$ {formData.monthlyAmount}/mês
              </h3>
              <p className="text-sm text-green-700 dark:text-green-300">
                Em {formData.years} anos, você terá <strong>R$ {result ? result.futureValue.toLocaleString('pt-BR') : '...'}</strong>
                {' '}construindo seu patrimônio e segurança financeira.
              </p>
            </div>
          </div>
          
          <div className="mt-4 p-4 rounded-lg bg-blue-50 dark:bg-blue-950 border border-blue-200 dark:border-blue-800">
            <h3 className="font-semibold text-blue-800 dark:text-blue-200 mb-2">
              🎯 A Escolha é Sua
            </h3>
            <p className="text-sm text-blue-700 dark:text-blue-300">
              Cada real apostado é um real que deixa de trabalhar para o seu futuro. 
              Que tal começar hoje mesmo a construir sua independência financeira?
            </p>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}

