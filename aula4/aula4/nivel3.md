### Arquivo 3: `3-diagrama-camadas.md`
Este arquivo entrega a variação do Nível 2, anotando especificamente a qual camada lógica cada componente da arquitetura pertence, conforme a responsabilidade exigida pela regra de negócio.

```markdown
# Diagrama C4 Nível 2 (Anotado com Camadas Lógicas)

O mesmo detalhamento de contêineres, mas com anotações mapeando a divisão de responsabilidades da aplicação (Apresentação, Domínio e Dados).

```mermaid
C4Container
title Diagrama C4 Nível 2 - Contêineres e Camadas Lógicas

Person(vendedor, "Vendedor", "Registra pedidos.")
Person(admin, "Administrador", "Gerencia produtos.")
Person(estoque, "Time de Estoque", "Controla inventário.")

System_Boundary(sistema_vendas_boundary, "Sistema de Vendas e Estoque") {
    Container(frontend, "Aplicação Web", "Camada de Apresentação", "Lida com o usuário e traduz o mundo externo. Valida formato de dados.")
    Container(api, "API do Sistema", "Camada de Domínio", "Onde vivem as regras de negócio. Verifica estoque, calcula valores e aprova pedidos.")
    ContainerDb(database, "Banco de Dados", "Camada de Dados", "Responsável pela persistência. Apenas salva e busca informações sem decidir regras.")
}

Rel(vendedor, frontend, "Interage com a tela", "HTTPS")
Rel(admin, frontend, "Interage com a tela", "HTTPS")
Rel(estoque, frontend, "Interage com a tela", "HTTPS")

Rel(frontend, api, "Apresentação repassa requisição validada para o Domínio", "JSON/HTTPS")
Rel(api, database, "Domínio envia comando de gravação/leitura para Dados", "TCP/IP")