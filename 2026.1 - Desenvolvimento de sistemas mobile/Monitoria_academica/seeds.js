

import mongoose from 'mongoose';
import User from './src/models/User.js';
import Session from './src/models/Session.js';
//import bcrypt from 'bcrypt';
import dotenv from 'dotenv';


dotenv.config();


async function seed() {
  await mongoose.connect(process.env.MONGO_URI);
  try {
    // Limpar dados existentes
    await Promise.all([
      Session.deleteMany({}),
      User.deleteMany({})
    ]);


    // Criar usuários
    const senha = '123456';
    const [
      aluno, aluno2, aluno3,
      mentor, mentor2, mentor3, mentor4, mentor5,
      admin
] = await Promise.all([
      User.create({
        name: 'Aluno Teste',
        email: 'aluno@teste.com',
        password: senha ,
        role: 'aluno',
        telefone: '51999999999',
      }),
      User.create({
        name: 'Aluno Dois',
        email: 'aluno2@teste.com',
        password: senha ,
        role: 'aluno',
        telefone: '51999999998',
      }),
      User.create({
        name: 'Aluno Três',
        email: 'aluno3@teste.com',
        password: senha ,
        role: 'aluno',
        telefone: '51999999997',
      }),
      User.create({
        name: 'Mentor Teste',
        email: 'mentor@teste.com',
        password: senha ,
        role: 'mentor',
        telefone: '51988888888',
        habilidades: ['Matemática', 'Programação']
      }),
      User.create({
        name: 'Mentora Ana',
        email: 'mentora.ana@teste.com',
        password: senha ,
        role: 'mentor',
        telefone: '51988888887',
        habilidades: ['Português', 'Redação']
      }),
      User.create({
        name: 'Mentor Carlos',
        email: 'mentor.carlos@teste.com',
        password: senha ,
        role: 'mentor',
        telefone: '51988888886',
        habilidades: ['Física', 'Matemática']
      }),
      User.create({
        name: 'Mentora Beatriz',
        email: 'mentora.beatriz@teste.com',
        password: senha ,
        role: 'mentor',
        telefone: '51988888885',
        habilidades: ['Química', 'Biologia']
      }),
      User.create({
        name: 'Mentor Daniel',
        email: 'mentor.daniel@teste.com',
        password: senha ,
        role: 'mentor',
        telefone: '51988888884',
        habilidades: ['História', 'Geografia']
      }),
      User.create({
        name: 'Admin',
        email: 'admin@teste.com',
        password: senha ,
        role: 'admin',
        telefone: '51977777777',
        habilidades: ['Gestão', 'Administração']
      })
    ]);


    // Criar sessões
    await Promise.all([
      Session.create({
        aluno: aluno._id,
        mentor: mentor._id,
        assunto: 'Matemática',
descricao: 'Dúvidas sobre derivadas',
        data: new Date(Date.now() + 86400000),
        status: 'pendente'
      }),
      Session.create({
        aluno: aluno2._id,
        mentor: mentor2._id,
        assunto: 'Redação',
        descricao: 'Como melhorar a argumentação?',
        data: new Date(Date.now() + 2 * 86400000),
        status: 'aprovada'
      }),
      Session.create({
        aluno: aluno3._id,
        mentor: mentor3._id,
        assunto: 'Física',
        descricao: 'Cinemática básica',
        data: new Date(Date.now() + 3 * 86400000),
        status: 'pendente'
      }),
      Session.create({
        aluno: aluno._id,
        mentor: mentor4._id,
        assunto: 'Química',
        descricao: 'Reações orgânicas',
        data: new Date(Date.now() + 4 * 86400000),
        status: 'pendente'
      }),
      Session.create({
        aluno: aluno2._id,
        mentor: mentor5._id,
        assunto: 'História',
        descricao: 'Revolução Francesa',
        data: new Date(Date.now() + 5 * 86400000),
        status: 'concluida'
      }),
      Session.create({
        aluno: aluno3._id,
        mentor: mentor._id,
assunto: 'Matemática',
        descricao: 'Funções do 2º grau',
        data: new Date(Date.now() + 6 * 86400000),
        status: 'pendente'
      })
    ]);


    console.log('Banco limpo e populado com sucesso!');
  } catch (err) {
    console.error(err);
    process.exit(1);
  } finally {
    await mongoose.disconnect();
  }
}


seed();
