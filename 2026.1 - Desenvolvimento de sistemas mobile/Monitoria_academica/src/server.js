import express from 'express';
import mongoose from 'mongoose';
import dotenv from 'dotenv';
import morgan from 'morgan';

import authRoutes from './routes/auth.js';
import userRoutes from './routes/user.js';
import sessionRoutes from './routes/session.js';

dotenv.config(); // permite acessar valores do .env
const app = express();

app.use(express.json());
app.use(morgan('dev')) // em modo desenvolvimento


const PORT = process.env.PORT || 3000;
mongoose.connect(process.env.MONGO_URI )
  .then(() => {
    app.listen(PORT, () => console.log(`Servidor rodando na porta ${PORT}`));
  })
  .catch(err => console.error('MongoDB erro de conexão:', err));


app.use('/api/auth', authRoutes);
app.use('/api/users', userRoutes); // TODO 
app.use('/api/sessions', sessionRoutes); // TODO 