# FEI Food – Sistema de Pedidos (Swing + PostgreSQL)

## 1. Visão Geral
Aplicação desktop em Java Swing com autenticação, busca de itens, carrinho, finalização de pedidos e avaliação (0–5 estrelas).  
Arquitetura MVC leve + PostgreSQL.

## 2. Arquitetura do Projeto
- **View**: `TelaLogin`, `TelaCadastro`, `TelaMenu` (com `panelTopo` + `panelConteudo`), `TelaBusca`, `TelaCarrinho`, `TelaPedidos`.
- **Controller**: `ControllerLogin`, `ControllerAlimento`, `ControllerPedido`.
- **Model**: `Conexao`, `Usuario`, `Alimento`, `Comida`, `Bebida`, `ItemPedido`, `Pedido`.

Fluxo principal:
1) Login → `TelaMenu`  
2) `Buscar` → lista alimentos (bebida alcoólica exibe preço com imposto)  
3) `Adicionar` → carrinho  
4) `Carrinho` → remover/confirmar  
5) Finalizar → grava `pedido` e itens  
6) `Pedidos` → histórico e **avaliação** (0–5)

## 3. Funcionalidades
- **Login/Cadastro** persistidos em PostgreSQL.  
- **Busca** com filtro por nome e tabela com itens.  
- **Carrinho** com adicionar/remover e total.  
- **Finalização de pedido** (pedido + itens vinculados).  
- **Histórico** com data formatada e nomes dos itens concatenados.  
- **Avaliação** por estrelas com atualização imediata.

## 6. Como Executar
1. PostgreSQL ativo com o banco configurado (`feifood`) e credenciais ajustadas em `Model/Conexao.java`.
2. NetBeans:
   - Properties → Run → **Main Class**
   - **Clean and Build**
3. Executável:
   ```bash
   cd dist
   java -jar SeuProjeto.jar
   Javadoc: dist/javadoc/index.html (NetBeans → Generate Javadoc)
---

### 7. Evidências (prints)
<img width="501" height="582" alt="Captura de tela 2025-11-13 195731" src="https://github.com/user-attachments/assets/c4d2f6c2-e1c7-488e-a9c0-46e165a85505" />
<img width="525" height="577" alt="Captura de tela 2025-11-13 194820" src="https://github.com/user-attachments/assets/a9688653-5c80-462c-bff3-f076d4ea1088" />
<img width="914" height="619" alt="Captura de tela 2025-11-13 195412" src="https://github.com/user-attachments/assets/e4400b3d-0a4d-41a9-81a8-119c8e1f7a0c" />
<img width="916" height="621" alt="Captura de tela 2025-11-13 195444" src="https://github.com/user-attachments/assets/0cd522d2-aa49-49fe-8cb1-86f859850401" />
<img width="917" height="646" alt="Captura de tela 2025-11-13 195500" src="https://github.com/user-attachments/assets/ddd88f7a-52ce-4976-a62c-097d4ac925f6" />

---

## 👨‍💻 Autor

Desenvolvido por **Dante Ryuichi Kawazu - R.A: 22.125.083-0** – Ciência da Computação – FEI  
Projeto da disciplina **ARQUITETURA DE SOFTWARE E PROGRAMAÇÃO ORIENTADA A OBJETOS  – 4º Semestre 2025**
