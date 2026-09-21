C4Context
    title Diagrama de Contexto (Nível 1) - Sistema de Gestão de Vendas e Estoque

    Person(vendedor, "Vendedor", "Registra pedidos durante atendimentos presenciais ou por telefone.")
    Person(admin, "Administrador", "Cadastra e remove produtos do catálogo.")
    Person(estoque, "Time de Estoque", "Controla a quantidade disponível, dando baixa nas saídas e registrando entradas.")

    System(sistemaVendas, "Sistema de Gestão de Vendas e Estoque", "Permite a gestão do catálogo de produtos, registro de pedidos de vendas e controle de movimentações do estoque.")

    Rel(vendedor, sistemaVendas, "Registra pedidos de venda em", "HTTPS / Web UI / Mobile")
    Rel(admin, sistemaVendas, "Gerencia o catálogo de produtos em", "HTTPS / Web UI")
    Rel(estoque, sistemaVendas, "Registra entradas e saídas de estoque em", "HTTPS / Web UI / Mobile")

    =====================================================================================================================================


    C4Container
    title Diagrama de Contêineres (Nível 2) - Sistema de Gestão de Vendas e Estoque

    Person(vendedor, "Vendedor", "Acessa via navegador ou dispositivo móvel.")
    Person(admin, "Administrador", "Acessa via navegador.")
    Person(estoque, "Time de Estoque", "Acessa via navegador ou coletor/dispositivo móvel.")

    Container_Boundary(c1, "Sistema de Gestão de Vendas e Estoque") {
        Container(spa, "Aplicação Web Single-Page", "React / Vue.js", "Interface para administradores, vendedores e equipe de estoque.")
        Container(mobile, "App Mobile / PWA", "React Native / Flutter", "Interface otimizada para campo/pátio de estoque (vendedores e estoque).")
        Container(api, "API Gateway / Backend Services", "Node.js / Java / C#", "Processa a lógica de negócios de vendas, catálogo e movimentação de estoque.")
        ContainerDb(db, "Banco de Dados Relacional", "PostgreSQL / MySQL", "Armazena dados de usuários, catálogo de produtos, pedidos e saldo de estoque.")
    }

    Rel(vendedor, spa, "Registra pedidos em", "HTTPS")
    Rel(vendedor, mobile, "Registra pedidos em", "HTTPS")
    Rel(admin, spa, "Gerencia produtos em", "HTTPS")
    Rel(estoque, spa, "Controla estoque em", "HTTPS")
    Rel(estoque, mobile, "Registra entradas/saídas em", "HTTPS")

    Rel(spa, api, "Faz chamadas de API para", "JSON / HTTPS")
    Rel(mobile, api, "Faz chamadas de API para", "JSON / HTTPS")
    Rel(api, db, "Lê e escreve dados em", "SQL / TCP")

    ====================================================================================================================================

    C4Container
    title Diagrama Nível 2 - Mapeamento por Camadas da Arquitetura

    System_Boundary(camada_apresentacao, "CAMADA DE APRESENTAÇÃO (Presentation Layer)") {
        Container(web_app, "Aplicação Web (SPA)", "Camada: Apresentação | Tecnologia: React/Vue", "Interface web para Admin, Vendedores e Time de Estoque.")
        Container(mobile_app, "App Mobile", "Camada: Apresentação | Tecnologia: React Native/Flutter", "Interface mobile para Vendedores externos e Operadores de Estoque.")
    }

    System_Boundary(camada_dominio, "CAMADA DE DOMÍNIO (Domain / Business Logic Layer)") {
        Container(api_vendas, "Módulo / API de Vendas", "Camada: Domínio | Tecnologia: REST API Service", "Regras de negócio para criação e fluxo dos pedidos de venda.")
        Container(api_catalogo, "Módulo / API de Catálogo", "Camada: Domínio | Tecnologia: REST API Service", "Regras de cadastro, ativação e remoção de produtos.")
        Container(api_estoque, "Módulo / API de Estoque", "Camada: Domínio | Tecnologia: REST API Service", "Regras para baixa de saídas, registro de entradas e saldo disponível.")
    }

    System_Boundary(camada_dados, "CAMADA DE DADOS (Data Access Layer)") {
        ContainerDb(banco_dados, "Banco de Dados Relacional", "Camada: Dados | Tecnologia: PostgreSQL/MySQL", "Persistência e consistência transacional das entidades do sistema.")
    }

    Rel(web_app, api_vendas, "Usa", "HTTPS/JSON")
    Rel(web_app, api_catalogo, "Usa", "HTTPS/JSON")
    Rel(web_app, api_estoque, "Usa", "HTTPS/JSON")

    Rel(mobile_app, api_vendas, "Usa", "HTTPS/JSON")
    Rel(mobile_app, api_estoque, "Usa", "HTTPS/JSON")

    Rel(api_vendas, banco_dados, "Lê/Escreve pedidos e reservas", "SQL")
    Rel(api_catalogo, banco_dados, "Lê/Escreve produtos", "SQL")
    Rel(api_estoque, banco_dados, "Lê/Escreve entradas, saídas e saldos", "SQL")