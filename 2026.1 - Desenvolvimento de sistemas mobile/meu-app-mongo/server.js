require('dotenv').config(); // Carrega as variáveis de ambiente do .env
const express = require('express');
const mongoose = require('mongoose');
const app = express();
app.use(express.json()); // Habilita o parsing de JSON para requisições
const PORT = process.env.PORT || 3000;
const MONGO_URI = process.env.MONGO_URI;
// 1. Conectar ao MongoDB
mongoose.connect(MONGO_URI)
    .then(() => {
        console.log('Conectado ao MongoDB com sucesso!');
    })
    .catch((err) => {
        console.error('Erro ao conectar ao MongoDB:', err.message);
        process.exit(1); // Encerra o processo se a conexão falhar
    });
// 2. Definir um Schema
const usuarioSchema = new mongoose.Schema({
    nome: {
        type: String,
        required: true,
        trim: true // Remove espaços em branco do início e fim
    },
    email: {
        type: String,
        required: true,
        unique: true, // Garante que o email seja único na coleção
        lowercase: true, // Converte o email para minúsculas antes de salvar
        match: /.+\@.+\..+/ // Regex para validar formato de email
    },
    idade: {
        type: Number,
        min: 0, // Idade mínima
        max: 120 // Idade máxima
    },
    dataCriacao: {
        type: Date,
        default: Date.now // Valor padrão: data e hora atuais
    }
});
// Adicionar um método personalizado ao schema
usuarioSchema.methods.saudar = function() {
    console.log(`Olá, meu nome é ${this.nome} e tenho ${this.idade} anos.`);
};
// Adicionar um método estático ao schema
usuarioSchema.statics.findByEmail = function(email) {
    return this.findOne({ email: email });
};
// 3. Criar um Modelo a partir do Schema
const Usuario = mongoose.model('Usuario', usuarioSchema);
// Rotas da API (serão adicionadas a seguir)
app.get('/', (req, res) => {
    res.send('API de Usuários com Express e Mongoose está funcionando!');
});
// Iniciar o servidor
app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});

// Rota para criar um novo usuário
app.post('/usuarios', async (req, res) => {
    try {
        const novoUsuario = new Usuario(req.body); // Cria uma nova instância do modelo
        const usuarioSalvo = await novoUsuario.save(); // Salva no banco de dados
        res.status(201).json(usuarioSalvo); // Retorna o usuário criado com status 201 (Created)
    } catch (err) {
        if (err.code === 11000) { // Código de erro para violação de unique (ex: email duplicado)
        return res.status(400).json({ message: 'Email já cadastrado.' });
    }
    res.status(400).json({ message: err.message }); // Retorna erros de validação
    }
});

// Rota para listar todos os usuários
app.get('/usuarios', async (req, res) => {
    try {
        const usuarios = await Usuario.find(); // Encontra todos os documentos na coleção 'usuarios'
        res.status(200).json(usuarios); // Retorna a lista de usuários
    } catch (err) {
        res.status(500).json({ message: err.message }); // Erro interno do servidor
    }
});

// Rota para obter um usuário por ID
app.get('/usuarios/:id', async (req, res) => {
    try {
        const usuario = await Usuario.findById(req.params.id); // Encontra pelo ID
        if (!usuario) {
            return res.status(404).json({ message: 'Usuário não encontrado.' });
        }
        res.status(200).json(usuario);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// Rota para atualizar um usuário por ID
// Usamos { new: true } para retornar o documento atualizado
// E { runValidators: true } para rodar as validações do schema na atualização
app.put('/usuarios/:id', async (req, res) => {
    try {
        const usuarioAtualizado = await Usuario.findByIdAndUpdate(
            req.params.id,
            req.body,
            { returnDocument: 'after', runValidators: true }
        );
        if (!usuarioAtualizado) {
            return res.status(404).json({ message: 'Usuário não encontrado.' });
        }
        res.status(200).json(usuarioAtualizado);
    } catch (err) {
        if (err.code === 11000) {
            return res.status(400).json({ message: 'Email já cadastrado.' });
        }
        res.status(400).json({ message: err.message });
    }
});

// Rota para deletar um usuário por ID
app.delete('/usuarios/:id', async (req, res) => {
    try {
        const usuarioDeletado = await Usuario.findByIdAndDelete(req.params.id);
        if (!usuarioDeletado) {
            return res.status(404).json({ message: 'Usuário não encontrado.' });
        }
        res.status(200).json({ message: 'Usuário deletado com sucesso!' });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});