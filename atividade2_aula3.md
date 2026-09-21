# Análise de Estilos Arquiteturais
**Disciplina:** Arquitetura de Software
**Atividade:** Destrinchando os estilos

Abaixo está a análise detalhada de dois estilos arquiteturais abordados em aula, com foco em seus conceitos, aplicações práticas, vantagens e desafios de manutenção.

---

## 1. Estilo Cliente-Servidor

### Conceito e definição
O estilo Cliente-Servidor divide o sistema em dois papéis distintos e fundamentais. O **cliente** é a parte ativa responsável por iniciar a comunicação, solicitando um serviço, uma página ou um dado. O **servidor**, por sua vez, atua de forma reativa e passiva: ele fica aguardando essas requisições, processa o que foi pedido e devolve uma resposta. É um modelo de comunicação direta onde quem pede e quem atende estão completamente separados.

### Casos de uso comuns
Esse estilo é amplamente utilizado no mercado em cenários onde múltiplos clientes precisam acessar um recurso, lógica ou base de dados centralizada.
*   **Exemplo 1 (Navegação Web):** A interação clássica da internet. Um leitor (cliente) usa um navegador para acessar um site de notícias. O navegador pede a página, e o Servidor Web busca a notícia no banco de dados e a devolve formatada para o leitor.
*   **Exemplo 2 (Resolução de DNS):** O sistema de tradução de nomes de domínio. O seu computador manda uma consulta ao servidor DNS perguntando qual o endereço IP de um site. O servidor responde com o endereço exato e a troca acaba ali, tratando cada busca de forma independente.

### Principais vantagens
*   **Modelo Simples e Difundido:** É uma arquitetura de fácil compreensão e implementação, consolidada como o padrão para a maioria das aplicações web convencionais.
*   **Centralização e Manutenção:** Como a regra de negócio e os dados ficam no lado do servidor, atualizações, correções e manutenções são mais fáceis de aplicar, sem a necessidade de atualizar cada cliente individualmente.

### Principais desvantagens
*   **Gargalo de Desempenho:** Se o número de clientes crescer abruptamente e fizerem muitas requisições ao mesmo tempo, o servidor pode não dar conta e virar um gargalo de lentidão.
*   **Ponto Único de Falha (Single Point of Failure):** A maior limitação deste estilo é a dependência central. Se o servidor cair ou ficar offline, todos os clientes ficam imediatamente sem serviço.

---

## 2. Estilo Publicador/Assinante (Pub/Sub)

### Conceito e definição
Neste estilo, a comunicação é orientada a eventos, assíncrona e altamente desacoplada. Quem produz a informação (o **publicador**) envia o evento para um canal intermediário (tópico ou fila). Quem tem interesse nessa informação (os **assinantes**) se inscreve previamente neste canal. Assim que uma nova mensagem chega, o canal a distribui automaticamente para todos os inscritos. A grande sacada é que o publicador não sabe quem são os assinantes, nem o que farão com o evento.

### Casos de uso comuns
Recomendado para sistemas onde uma única ação do usuário precisa engatilhar múltiplas reações independentes em diferentes partes do software.
*   **Exemplo 1 (Microsserviços de E-commerce):** Um "Serviço de Pedidos" (publicador) emite o evento de que uma compra foi finalizada. O canal distribui isso e, paralelamente, o Serviço de E-mail, o Serviço de Estoque e o Analytics (assinantes) reagem independentemente àquela informação.
*   **Exemplo 2 (Plataforma Cientista Sem Jaleco):** Quando um usuário se cadastra na mentoria, a aplicação (publicador) envia uma mensagem para o RabbitMQ (broker de fila). Um serviço consumidor em background (assinante) lê a fila e dispara o e-mail de confirmação de forma assíncrona, não travando a navegação do cliente na loja.

### Principais vantagens
*   **Baixo Acoplamento e Extensibilidade:** Como publicadores e assinantes não se conhecem, é extremamente fácil adicionar um novo comportamento ou serviço (um novo assinante) no futuro sem precisar alterar uma linha sequer no código de quem publica a mensagem.
*   **Desempenho da Aplicação Principal:** O publicador apenas "joga" o evento no canal e está liberado. Ele não precisa esperar processos lentos dos assinantes terminarem (como emissão de nota fiscal ou relatórios pesados).

### Principais desvantagens
*   **Complexidade de Rastreabilidade:** Quando ocorre um erro no sistema, é muito mais difícil rastrear o fluxo completo da requisição e entender o "efeito dominó", pois a execução não é linear.
*   **Gestão de Falhas de Assinantes:** Traz o desafio de planejar o que acontece se um assinante estiver fora do ar. O desenvolvedor precisa implementar estratégias de repetição (retries) ou filas de mensagens mortas (Dead Letter Queues) para não perder informações importantes.