import { useState, useEffect } from 'react'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '../components/ui/card'
import { Button } from '../components/ui/button'
import { Input } from '../components/ui/input'
import { Label } from '../components/ui/label'
import { Badge } from '../components/ui/badge'
import { 
  Plus, 
  Search, 
  Edit, 
  Trash2, 
  Mail, 
  User,
  Calendar
} from 'lucide-react'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '../components/ui/dialog'

const mockUsers = [
  { id: 1, username: 'joao_silva', email: 'joao@email.com', createdAt: '2024-01-15', score: 850 },
  { id: 2, username: 'maria_santos', email: 'maria@email.com', createdAt: '2024-02-20', score: 720 },
  { id: 3, username: 'pedro_oliveira', email: 'pedro@email.com', createdAt: '2024-03-10', score: 950 },
  { id: 4, username: 'ana_costa', email: 'ana@email.com', createdAt: '2024-03-25', score: 680 },
]

export function Users() {
  const [users, setUsers] = useState(mockUsers)
  const [searchTerm, setSearchTerm] = useState('')
  const [isDialogOpen, setIsDialogOpen] = useState(false)
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: ''
  })

  const filteredUsers = users.filter(user =>
    user.username.toLowerCase().includes(searchTerm.toLowerCase()) ||
    user.email.toLowerCase().includes(searchTerm.toLowerCase())
  )

  const handleSubmit = (e) => {
    e.preventDefault()
    const newUser = {
      id: users.length + 1,
      ...formData,
      createdAt: new Date().toISOString().split('T')[0],
      score: 500
    }
    setUsers([...users, newUser])
    setFormData({ username: '', email: '', password: '' })
    setIsDialogOpen(false)
  }

  const getScoreBadge = (score) => {
    if (score >= 800) return { variant: 'default', label: 'Excelente', color: 'bg-green-500' }
    if (score >= 600) return { variant: 'secondary', label: 'Bom', color: 'bg-blue-500' }
    return { variant: 'destructive', label: 'Iniciante', color: 'bg-yellow-500' }
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center space-y-4 sm:space-y-0">
        <div>
          <h2 className="text-3xl font-bold tracking-tight text-gradient-reveste">
            Usuários
          </h2>
          <p className="text-muted-foreground">
            Gerencie os usuários da plataforma ReVeste
          </p>
        </div>
        
        <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
          <DialogTrigger asChild>
            <Button className="bg-gradient-reveste hover:opacity-90">
              <Plus className="h-4 w-4 mr-2" />
              Novo Usuário
            </Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Criar Novo Usuário</DialogTitle>
              <DialogDescription>
                Adicione um novo usuário à plataforma ReVeste
              </DialogDescription>
            </DialogHeader>
            <form onSubmit={handleSubmit}>
              <div className="space-y-4 py-4">
                <div className="space-y-2">
                  <Label htmlFor="username">Nome de usuário</Label>
                  <Input
                    id="username"
                    value={formData.username}
                    onChange={(e) => setFormData({...formData, username: e.target.value})}
                    placeholder="Digite o nome de usuário"
                    required
                  />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="email">Email</Label>
                  <Input
                    id="email"
                    type="email"
                    value={formData.email}
                    onChange={(e) => setFormData({...formData, email: e.target.value})}
                    placeholder="Digite o email"
                    required
                  />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="password">Senha</Label>
                  <Input
                    id="password"
                    type="password"
                    value={formData.password}
                    onChange={(e) => setFormData({...formData, password: e.target.value})}
                    placeholder="Digite a senha"
                    required
                  />
                </div>
              </div>
              <DialogFooter>
                <Button type="button" variant="outline" onClick={() => setIsDialogOpen(false)}>
                  Cancelar
                </Button>
                <Button type="submit" className="bg-gradient-reveste hover:opacity-90">
                  Criar Usuário
                </Button>
              </DialogFooter>
            </form>
          </DialogContent>
        </Dialog>
      </div>

      {/* Search */}
      <Card>
        <CardContent className="pt-6">
          <div className="relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-muted-foreground h-4 w-4" />
            <Input
              placeholder="Buscar usuários por nome ou email..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="pl-10"
            />
          </div>
        </CardContent>
      </Card>

      {/* Users Grid */}
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
        {filteredUsers.map((user) => {
          const scoreBadge = getScoreBadge(user.score)
          return (
            <Card key={user.id} className="animate-slide-up hover:shadow-lg transition-shadow">
              <CardHeader className="pb-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center space-x-3">
                    <div className="h-10 w-10 rounded-full bg-gradient-reveste flex items-center justify-center">
                      <User className="h-5 w-5 text-white" />
                    </div>
                    <div>
                      <CardTitle className="text-lg">{user.username}</CardTitle>
                      <CardDescription className="flex items-center">
                        <Mail className="h-3 w-3 mr-1" />
                        {user.email}
                      </CardDescription>
                    </div>
                  </div>
                  <Badge variant={scoreBadge.variant}>
                    {scoreBadge.label}
                  </Badge>
                </div>
              </CardHeader>
              <CardContent>
                <div className="space-y-3">
                  <div className="flex justify-between items-center">
                    <span className="text-sm text-muted-foreground">Score:</span>
                    <span className="font-semibold text-lg">{user.score}</span>
                  </div>
                  <div className="flex justify-between items-center">
                    <span className="text-sm text-muted-foreground flex items-center">
                      <Calendar className="h-3 w-3 mr-1" />
                      Criado em:
                    </span>
                    <span className="text-sm">
                      {new Date(user.createdAt).toLocaleDateString('pt-BR')}
                    </span>
                  </div>
                  <div className="flex space-x-2 pt-2">
                    <Button variant="outline" size="sm" className="flex-1">
                      <Edit className="h-3 w-3 mr-1" />
                      Editar
                    </Button>
                    <Button variant="outline" size="sm" className="text-destructive hover:text-destructive">
                      <Trash2 className="h-3 w-3" />
                    </Button>
                  </div>
                </div>
              </CardContent>
            </Card>
          )
        })}
      </div>

      {filteredUsers.length === 0 && (
        <Card>
          <CardContent className="text-center py-12">
            <User className="h-12 w-12 text-muted-foreground mx-auto mb-4" />
            <h3 className="text-lg font-semibold mb-2">Nenhum usuário encontrado</h3>
            <p className="text-muted-foreground mb-4">
              {searchTerm ? 'Tente ajustar sua busca' : 'Comece criando seu primeiro usuário'}
            </p>
            {!searchTerm && (
              <Button onClick={() => setIsDialogOpen(true)} className="bg-gradient-reveste hover:opacity-90">
                <Plus className="h-4 w-4 mr-2" />
                Criar Primeiro Usuário
              </Button>
            )}
          </CardContent>
        </Card>
      )}

      {/* Stats */}
      <div className="grid gap-4 md:grid-cols-4">
        <Card>
          <CardContent className="pt-6">
            <div className="text-2xl font-bold">{users.length}</div>
            <p className="text-xs text-muted-foreground">Total de usuários</p>
          </CardContent>
        </Card>
        <Card>
          <CardContent className="pt-6">
            <div className="text-2xl font-bold">
              {users.filter(u => getScoreBadge(u.score).label === 'Excelente').length}
            </div>
            <p className="text-xs text-muted-foreground">Usuários excelentes</p>
          </CardContent>
        </Card>
        <Card>
          <CardContent className="pt-6">
            <div className="text-2xl font-bold">
              {Math.round(users.reduce((acc, u) => acc + u.score, 0) / users.length)}
            </div>
            <p className="text-xs text-muted-foreground">Score médio</p>
          </CardContent>
        </Card>
        <Card>
          <CardContent className="pt-6">
            <div className="text-2xl font-bold">
              {users.filter(u => {
                const date = new Date(u.createdAt)
                const now = new Date()
                const diffTime = Math.abs(now - date)
                const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
                return diffDays <= 30
              }).length}
            </div>
            <p className="text-xs text-muted-foreground">Novos este mês</p>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}

