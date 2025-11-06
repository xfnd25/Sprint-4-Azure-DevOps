# Roteiro para o Vídeo de Apresentação (15 Minutos)

## Título do Vídeo: Mottu Location - Gerenciamento Inteligente de Frotas

---

### **Minuto 0-2: Introdução e Proposta da Solução**

**Apresentador:** Olá a todos, meu nome é [Seu Nome] e hoje vou apresentar o projeto **Mottu Location**, desenvolvido para a disciplina "Advanced Java Development". Nossa equipe é composta por Fernando Fontes e Guilherme Jardim.

**(Slide 1: Título e Nomes)**

**Apresentador:** A Mottu, como sabemos, é uma empresa inovadora no setor de aluguel de motos. Com uma frota em constante crescimento, um dos seus maiores desafios é o gerenciamento eficiente das motos em seus pátios. Como garantir que cada moto seja rapidamente localizada para manutenção, aluguel ou inspeção?

**(Slide 2: O Problema)**

**Apresentador:** A nossa solução, o **Mottu Location**, ataca exatamente esse problema. Desenvolvemos uma aplicação web que permite o rastreamento em tempo real das motos dentro dos pátios da Mottu, utilizando sensores RFID. O sistema não só otimiza a logística, mas também aumenta a segurança e a eficiência operacional da empresa.

---

### **Minuto 2-8: Demonstração Técnica da Aplicação**

**(Aplicação rodando online)**

**Apresentador:** Agora, vamos navegar pela aplicação. Esta é a nossa tela de login, que conta com um sistema de segurança baseado em perfis. Vamos começar acessando como **administrador**.

**(Login como `admin`)**

**Apresentador:** Como administrador, temos acesso total ao sistema. No painel principal, podemos ver a lista de motos cadastradas, com informações como placa, modelo e o seu status atual.

**(Navegar para a lista de motos)**

**Apresentador:** Vamos cadastrar uma nova moto. O processo é simples e rápido. O sistema gera automaticamente uma tag RFID única para cada moto, que será usada para o rastreamento.

**(Cadastrar uma nova moto)**

**Apresentador:** Também podemos gerenciar os sensores, que são os pontos de leitura RFID espalhados pelo pátio. Cada sensor tem uma localização específica, o que nos permite saber exatamente onde a moto foi detectada.

**(Navegar para a lista de sensores e adicionar um novo)**

**Apresentador:** A funcionalidade principal é o registro de movimentações. Vamos simular a passagem de uma moto por um sensor. Ao registrar a movimentação, o sistema atualiza o status da moto e a sua última localização conhecida.

**(Registrar uma movimentação)**

**Apresentador:** E, por fim, podemos visualizar o histórico completo de movimentações de cada moto, o que é fundamental para auditorias e para entender o fluxo de movimentação no pátio.

**(Mostrar o histórico de uma moto)**

**Apresentador:** Agora, vamos sair e acessar como **usuário comum**.

**(Logout e login como `user`)**

**Apresentador:** Como podem ver, o usuário comum tem uma visão limitada. Ele pode visualizar as motos e seus históricos, mas não pode adicionar, editar ou excluir informações, garantindo a segurança e a integridade dos dados.

---

### **Minuto 8-12: Detalhes Técnicos e Integração com Outras Disciplinas**

**(Slide 3: Arquitetura e Tecnologias)**

**Apresentador:** Por trás dessa interface, temos uma arquitetura robusta e tecnologias de ponta. Utilizamos uma **Arquitetura em Camadas** com **Spring Boot**, que nos deu agilidade e um ecossistema completo para trabalhar. O frontend foi construído com **Thymeleaf**, e o banco de dados é o **PostgreSQL**, com versionamento de schema controlado pelo **Flyway**.

**(Slide 4: Integração com Outras Disciplinas)**

**Apresentador:** Este projeto também foi uma oportunidade de integrar o que aprendemos em outras disciplinas. Por exemplo, a nossa aplicação consome uma API em **.NET** para prever a necessidade de manutenção das motos. O modelo de dados foi desenvolvido na disciplina de **Banco de Dados** com Oracle. E o deploy foi automatizado com **CI/CD no Azure DevOps**, da disciplina de **DevOps**.

---

### **Minuto 12-15: Conclusão e Próximos Passos**

**(Slide 5: Resultados e Próximos Passos)**

**Apresentador:** Em resumo, o **Mottu Location** é uma solução completa e funcional que resolve um problema real da Mottu. A aplicação é segura, escalável e pronta para ser integrada ao ecossistema da empresa.

**Apresentador:** Como próximos passos, planejamos expandir a aplicação para incluir um dashboard com análises em tempo real, integração com um aplicativo móvel para os operadores do pátio e a utilização de machine learning para prever padrões de movimentação e otimizar a logística.

**(Slide 6: Agradecimentos)**

**Apresentador:** Agradeço a todos pela atenção. Este projeto foi um grande aprendizado e estamos muito orgulhosos do resultado. Muito obrigado.
