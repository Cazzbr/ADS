import User from '../models/User.js';
export async function register(req, res, next) {
  try {
    const { name, email, password, role, contato, habilidades } = req.body;
      if (await User.findOne({ email })) return res.status(409).json({ error: 'Email já cadastrado' }); // conflito
    const user = await User.create({ name, email, password, role, contato, habilidades }); // TODO hash password
    res.status(201).json({ id: user._id, name, email, role, contato, habilidades });
  } catch (err) { next(err); }
}
