# Diagrama C4 Nível 1 (Contexto)

Visão geral do Sistema de Vendas e Estoque, evidenciando os atores (Vendedor, Administrador e Time de Estoque) e suas respectivas interações com a plataforma central.

```mermaid
C4Context
title Diagrama C4 Nível 1 - Contexto do Sistema

Person(vendedor, "Vendedor", "Acessa o sistema para registrar os pedidos de venda.")
Person(admin, "Administrador", "Acessa o sistema para cadastrar e remover produtos para venda.")
Person(estoque, "Time de Estoque", "Controla a quantidade disponível, dando baixa nas saídas e registrando as entradas.")

System(sistema_vendas, "Sistema de Vendas e Estoque", "Plataforma central que gerencia o catálogo, o fluxo de pedidos e o inventário de produtos.")

Rel(vendedor, sistema_vendas, "Registra pedidos")
Rel(admin, sistema_vendas, "Cadastra e remove produtos")
Rel(estoque, sistema_vendas, "Controla saldo e movimentações")