### Arquivo 2: `2-diagrama-conteineres.md`
Este arquivo entrega o Nível 2, abrindo o sistema para mostrar as tecnologias adotadas com base no padrão cliente-servidor abordado nas aulas.

```markdown
# Diagrama C4 Nível 2 (Contêineres)

Detalhamento das partes internas do sistema, dividindo a arquitetura em uma aplicação SPA para a interface, uma API para o backend e um banco de dados relacional.

```mermaid
C4Container
title Diagrama C4 Nível 2 - Contêineres

Person(vendedor, "Vendedor", "Registra pedidos.")
Person(admin, "Administrador", "Gerencia produtos.")
Person(estoque, "Time de Estoque", "Controla inventário.")

System_Boundary(sistema_vendas_boundary, "Sistema de Vendas e Estoque") {
    Container(frontend, "Aplicação Web (SPA)", "React + Vite", "Interface de usuário onde os funcionários realizam suas operações.")
    Container(api, "API do Sistema", "Node / Spring Boot", "Processa as requisições, executa lógicas de estoque e vendas.")
    ContainerDb(database, "Banco de Dados", "MySQL 8", "Armazena catálogo de produtos, histórico de vendas e saldo de estoque.")
}

Rel(vendedor, frontend, "Acessa via navegador", "HTTPS")
Rel(admin, frontend, "Acessa via navegador", "HTTPS")
Rel(estoque, frontend, "Acessa via navegador", "HTTPS")

Rel(frontend, api, "Faz chamadas de serviço", "JSON/HTTPS")
Rel(api, database, "Lê e escreve dados", "TCP/IP")