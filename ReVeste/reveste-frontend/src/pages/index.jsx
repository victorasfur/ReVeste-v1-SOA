import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '../components/ui/card'

export function Bets() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-3xl font-bold tracking-tight text-gradient-reveste">
          Apostas
        </h2>
        <p className="text-muted-foreground">
          Gerencie e monitore as apostas registradas na plataforma
        </p>
      </div>
      <Card>
        <CardContent className="text-center py-12">
          <p className="text-muted-foreground">Página em desenvolvimento...</p>
        </CardContent>
      </Card>
    </div>
  )
}

export function Gamification() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-3xl font-bold tracking-tight text-gradient-reveste">
          Gamificação
        </h2>
        <p className="text-muted-foreground">
          Sistema de pontuação e conquistas para incentivar boas decisões financeiras
        </p>
      </div>
      <Card>
        <CardContent className="text-center py-12">
          <p className="text-muted-foreground">Página em desenvolvimento...</p>
        </CardContent>
      </Card>
    </div>
  )
}

export function Education() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-3xl font-bold tracking-tight text-gradient-reveste">
          Educação Financeira
        </h2>
        <p className="text-muted-foreground">
          Conteúdo educativo para desenvolver inteligência financeira
        </p>
      </div>
      <Card>
        <CardContent className="text-center py-12">
          <p className="text-muted-foreground">Página em desenvolvimento...</p>
        </CardContent>
      </Card>
    </div>
  )
}

