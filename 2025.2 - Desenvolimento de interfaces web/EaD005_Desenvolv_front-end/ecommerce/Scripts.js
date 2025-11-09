document.addEventListener("DOMContentLoaded", () => {
  // Nagevação (troca de seleção do botão no nav e smoth scrooll)
  const container = document.getElementById("containerID");
  const sections = document.querySelectorAll("section");
  const menuLinks = document.querySelectorAll("aside nav a");

  let ticking = false;
  container.addEventListener("scroll", () => {
    if (!ticking) {
      window.requestAnimationFrame(() => {
        const scrollPosition = container.scrollTop + 20;

        sections.forEach((section, index) => {
          const sectionTop = section.offsetTop;
          const sectionBottom = sectionTop + section.offsetHeight;

          if (scrollPosition >= sectionTop && scrollPosition < sectionBottom) {
            menuLinks.forEach((link) => link.classList.remove("active"));
            if (menuLinks[index]) menuLinks[index].classList.add("active");
          }
        });
        ticking = false;
      });
      ticking = true;
    }
  });

  menuLinks.forEach((link) => {
    link.addEventListener("click", (e) => {
      e.preventDefault();
      const targetId = link.getAttribute("href").slice(1);
      const targetSection = container.querySelector(`#${targetId}`);
      if (targetSection) {
        const scrollToPosition = targetSection.offsetTop;
        container.scrollTo({ top: scrollToPosition, behavior: "smooth" });
      }
    });
  });

  // Quando menu hamburger está ativo, fecha automaticamente quando o usuário clica em um botão
  const menuToggle = document.getElementById("menu-toggle");
  menuLinks.forEach((link) => {
    link.addEventListener("click", () => {
      if (menuToggle && menuToggle.checked) {
        menuToggle.checked = false;
      }
    });
  });

  // Carrinho de compras
  const productCounts = {};

  const cartOverlay = document.getElementById("cart-overlay");
  const openCartBtn = document.getElementById("open-cart-btn");
  const cartItemsContainer = document.getElementById("cart-items");
  const cartBadge = document.getElementById("cart-badge");

  // Atualiza o contador no carrinho da nav — conta produtos com qty > 0
  function updateCartBadge() {
    const uniqueCount = Object.keys(productCounts).filter(
      (key) => productCounts[key] > 0,
    ).length;
    cartBadge.textContent = uniqueCount;
  }

  // Carinho de compras
  function updateCartItems() {
    const totalQty = Object.values(productCounts).reduce((a, b) => a + b, 0);
    if (totalQty > 0) {
      cartItemsContainer.innerHTML = `<p>${totalQty} item(s) no carrinho — ${
        Object.keys(productCounts).filter((k) => productCounts[k] > 0).length
      } tipos</p>`;
    } else {
      cartItemsContainer.innerHTML = "<p>Carrinho vazio</p>";
    }
  }

  function renderCartItems() {
    cartItemsContainer.innerHTML = "";

    const products = document.querySelectorAll(".card");
    let hasItems = false;

    products.forEach((card, index) => {
      const count = productCounts[index];
      if (count > 0) {
        hasItems = true;
        const productName =
          card.querySelector("h3")?.textContent.trim() ||
          `Produto ${index + 1}`;
        const itemDiv = document.createElement("div");
        itemDiv.textContent = `${productName} – Qtd: ${count}`;
        itemDiv.style.marginBottom = "10px";
        cartItemsContainer.appendChild(itemDiv);
      }
    });

    if (!hasItems) {
      cartItemsContainer.innerHTML = "<p>Carrinho vazio</p>";
    }
  }

  document.querySelectorAll(".card").forEach((card, index) => {
    const addBtn = card.querySelector(".add-btn");
    const removeBtn = card.querySelector(".remove-btn");
    const badge = card.querySelector(".cart-badge");

    productCounts[index] = 0;

    addBtn.addEventListener("click", () => {
      productCounts[index]++;
      badge.textContent = productCounts[index];
      updateCartBadge();
      updateCartItems();
      renderCartItems();
    });

    removeBtn.addEventListener("click", () => {
      if (productCounts[index] > 0) {
        productCounts[index]--;
        badge.textContent = productCounts[index];
        updateCartBadge();
        updateCartItems();
        renderCartItems();
      }
    });
  });

  // Abre o carrinho de compras como overlay
  openCartBtn.addEventListener("click", () => {
    cartOverlay.classList.add("open");
    cartOverlay.setAttribute("aria-hidden", "false");
    cartOverlay.focus();
    renderCartItems();
  });

  // Fecha o carrinho de compras quando clicado fora do overlay
  cartOverlay.addEventListener("click", (e) => {
    if (e.target === cartOverlay) {
      cartOverlay.classList.remove("open");
      cartOverlay.setAttribute("aria-hidden", "true");
      openCartBtn.focus();
    }
  });

  // Inicializa as funções dos contatores
  updateCartBadge();
  updateCartItems();
});
