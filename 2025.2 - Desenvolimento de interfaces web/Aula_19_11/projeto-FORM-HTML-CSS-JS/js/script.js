// js/script.js - theme switching, font control, mask and validation
document.addEventListener("DOMContentLoaded", function () {
  const themeButtons = document.querySelectorAll(".theme-btn");
  const themeLink = document.getElementById("theme-stylesheet");

  // Make theme buttons work and set active class
  themeButtons.forEach((btn) => {
    btn.addEventListener("click", () => {
      const theme = btn.getAttribute("data-theme");
      if (theme) {
        themeLink.setAttribute("href", theme);
        // active class
        themeButtons.forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
      }
    });
  });

  // font controls
  const decrease = document.getElementById("font-decrease");
  const increase = document.getElementById("font-increase");
  decrease.addEventListener("click", () => adjustFont(-1));
  increase.addEventListener("click", () => adjustFont(1));

  // phone mask
  const telefone = document.getElementById("telefone");
  telefone.addEventListener("input", onPhoneInput);

  // contacts preview
  const contactCheckboxes = document.querySelectorAll(
    'input[type="checkbox"][name="contato[]"]',
  );
  contactCheckboxes.forEach((cb) =>
    cb.addEventListener("change", showSelectedContacts),
  );
  showSelectedContacts();
});

function adjustFont(delta) {
  const root = document.documentElement;
  const style = getComputedStyle(root);
  const current = parseFloat(style.fontSize);
  let next = current + delta;
  if (next < 12) next = 12;
  if (next > 22) next = 22;
  root.style.fontSize = next + "px";
}

function onPhoneInput(e) {
  const el = e.target;
  let v = el.value.replace(/\D/g, "");
  if (v.length > 11) v = v.slice(0, 11);
  if (v.length <= 10) {
    v = v.replace(/(\d{2})(\d{4})(\d{0,4})/, "($1) $2-$3");
  } else {
    v = v.replace(/(\d{2})(\d{5})(\d{0,4})/, "($1) $2-$3");
  }
  el.value = v.replace(/-$/, "");
}

function showSelectedContacts() {
  const boxes = document.querySelectorAll('input[name="contato[]"]');
  const selected = [];
  boxes.forEach((b) => {
    if (b.checked) selected.push(b.value);
  });
  const out = document.getElementById("selected-contacts");
  if (selected.length) {
    out.hidden = false;
    out.textContent = "Contato por: " + selected.join(", ");
  } else {
    out.hidden = true;
    out.textContent = "";
  }
}

function validar() {
  //const nome = document.getElementById('nome').value.trim();
  const email = document.getElementById("email").value.trim();
  const telefone = document.getElementById("telefone").value.trim();

  //if(nome.length <= 5){
  //  alert('O campo Nome deve ter mais de 5 caracteres.');
  //  document.getElementById('nome').focus();
  //  return false;
  //}

  if (
    email.length <= 5 ||
    email.indexOf("@") === -1 ||
    !/^.+@.+\..+$/.test(email)
  ) {
    alert('Informe um email válido com "@" e mais de 5 caracteres.');
    document.getElementById("email").focus();
    return false;
  }

  const digits = telefone.replace(/\D/g, "");
  if (digits.length && (digits.length < 10 || digits.length > 11)) {
    alert("Telefone inválido. Utilize 10 ou 11 dígitos (DDD + número).");
    document.getElementById("telefone").focus();
    return false;
  }

  const foto = document.getElementById("foto");
  if (foto.files && foto.files.length) {
    const f = foto.files[0];
    if (f.size > 2 * 1024 * 1024) {
      alert("A foto deve ser menor que 2MB.");
      foto.focus();
      return false;
    }
  }

  return true;
}
