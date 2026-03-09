import Session from '../models/Session.js';


export async function createSession(req, res, next) {
  try {
      const session = await Session.create({
      aluno: req.user.id,
      mentor: req.body.mentor,
      assunto: req.body.assunto,
      descricao: req.body.descricao,
      data: req.body.data,
      status: req.body.status || 'pendente'
    });
    res.status(201).json(session);
  } catch (err) { next(err); }
}


export async function getSessions(req, res, next) {
  try {
    const filterMaps = {
      aluno: { aluno: req.user.id },
      mentor: { mentor: req.user.id },
      admin: {},
    };


    const filter = filterMaps[req.user.role];


    // boa prática de segurança, negar acesso se o papel do usuário for inválido
    if (filter === undefined) {
      // role inválido
      return res
        .status(403)
        .json({ error: "Perfil de usuário inválido para esta consulta." });
    }
    const sessions = await Session.find(filter) // Filtra sessões pela id do usuário, exceto admin
      .populate("aluno mentor", "name email role photo") // Popula dados básicos do aluno e mentor
      .sort({ data: -1 }); // Ordena por data decrescente
    res.json(sessions);
  } catch (err) {
    next(err);
  }
}


// TODO adicionar verificação de propriedade com a sessão
export async function getSessionById(req, res, next) {
  try {
    // Busca direta pelo ID vindo da URL
    const session = await Session.findById(req.params.id)
      .populate('aluno mentor', 'name email role photo');


    // Verifica se a sessão existe no banco
    if (!session) {
 return res.status(404).json({ error: "Sessão não encontrada" });
    }


    res.json(session);
  } catch (err) {
    next(err);
  }
}


export async function updateSession(req, res, next) {
  try {
    const session = await Session.findByIdAndUpdate(req.params.id, req.body, { new: true, runValidators: true });
    res.json(session);
  } catch (err) { next(err); }
}




export async function deleteSession(req, res, next) {
  try {
    await Session.findByIdAndDelete(req.params.id);
    res.json({ message: "Sessão deletada" });
  } catch (err) {
    next(err);
  }
}


