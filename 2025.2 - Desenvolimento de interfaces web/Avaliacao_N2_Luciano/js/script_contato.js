window.contactForm = function () {
  return {
    nome: "",
    email: "",
    telefone: "",
    mensagem: "",
    modalAberto: false,
    erro: "",

    enviarFormulario() {
      this.erro = "";

      if (!this.nome || !this.email || !this.telefone || !this.mensagem) {
        this.erro = "Por favor, preencha todos os campos.";
        return;
      }

      this.modalAberto = true;
      this.nome = "";
      this.email = "";
      this.telefone = "";
      this.mensagem = "";
    },
  };
};
