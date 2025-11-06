# Aplicação Web - Mottu Location

## Integrantes
* **RM555317** - Fernando Fontes
* **RM556814** - Guilherme Jardim

---

## 🚀 Proposta da Solução

Este projeto foi desenvolvido para a disciplina "Advanced Java Development" e tem como objetivo solucionar um desafio da empresa Mottu: o rastreamento e gerenciamento de sua frota de motos em pátios. A aplicação **Mottu Location** é uma plataforma web que permite o controle total do ciclo de vida das motos, desde o cadastro e associação de sensores RFID até o registro de suas movimentações.

A solução oferece uma interface intuitiva e segura para que os administradores possam gerenciar motos e sensores, enquanto usuários comuns podem visualizar o histórico de movimentações, garantindo assim a eficiência operacional e a segurança dos ativos da empresa.

---

## 🏛️ Decisões de Arquitetura e Tecnologia

A escolha da arquitetura e das tecnologias foi pautada na robustez, escalabilidade e na facilidade de manutenção da aplicação.

* **Arquitetura em Camadas (Layered Architecture):** A aplicação segue o padrão de arquitetura em camadas para garantir uma clara separação de responsabilidades, facilitando o desenvolvimento, a manutenção e a evolução do sistema. As camadas são divididas em:
    * **`Controller`:** Responsável por receber as requisições HTTP e coordenar as respostas.
    * **`Service`:** Contém a lógica de negócio principal da aplicação.
    * **`Repository`:** Camada de acesso a dados, utilizando o Spring Data JPA.
    * **`Entity`:** Representa as tabelas do banco de dados.

* **Spring Boot:** Foi escolhido como o framework principal por sua rapidez no desenvolvimento, configuração simplificada e por seu ecossistema robusto, que inclui o Spring Security, Spring Data JPA e o Spring Web.

* **Thymeleaf:** Para a camada de visualização, o Thymeleaf foi selecionado por sua integração natural com o Spring Boot e por permitir a criação de templates HTML dinâmicos e elegantes.

* **PostgreSQL:** Um banco de dados relacional poderoso e de código aberto, ideal para aplicações que exigem confiabilidade e integridade dos dados.

* **Flyway:** Para o versionamento do banco de dados, o Flyway garante que as alterações no schema do banco sejam aplicadas de forma consistente em todos os ambientes.

* **Docker:** A utilização do Docker para o banco de dados facilita a configuração do ambiente de desenvolvimento e garante que a aplicação seja executada em um ambiente consistente.

---

## 🔗 Integração com Outras Disciplinas

Este projeto foi concebido de forma a integrar os conhecimentos adquiridos em outras disciplinas do semestre, demonstrando a aplicação prática e a sinergia entre as diferentes áreas da tecnologia.

| Disciplina | Integração |
| :--- | :--- |
| **Backend .NET** | A aplicação Java consome uma API REST desenvolvida em .NET para obter informações sobre a previsão de manutenção das motos, enriquecendo os dados exibidos na plataforma. |
| **Banco de Dados** | O modelo de dados foi projetado e implementado no Oracle, utilizando procedures empacotadas para otimizar as consultas e garantir a segurança dos dados. |
| **DevOps** | O processo de deploy da aplicação foi automatizado com CI/CD no Azure DevOps, garantindo entregas mais rápidas e seguras. |

---

## 🏁 Como Executar o Projeto

### Pré-requisitos
* **JDK 17** ou superior
* **Maven 3.8** ou superior
* **Docker Desktop** (precisa estar rodando)

### Passo a Passo para Execução Local
1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/xfnd25/motolocation.git
    cd motolocation
    ```
2.  **Inicie o Banco de Dados com Docker:**
    ```bash
    docker-compose up -d
    ```
3.  **Execute a Aplicação Spring Boot:**
    ```bash
    ./mvnw spring-boot:run
    ```

### Acesso e Credenciais
* **URL:** [http://localhost:8080](http://localhost:8080)
* **Administrador:** `admin` / `admin`
* **Usuário Comum:** `user` / `user`

---

## ☁️ Deploy da Aplicação na Render

Para fazer o deploy da aplicação na Render, siga os passos abaixo:

1. **Crie uma conta na [Render](https://render.com/).**
2. **No dashboard, clique em "New" e selecione "Web Service".**
3. **Conecte sua conta do GitHub ou GitLab e selecione o repositório do projeto.**
4. **Configure o serviço da seguinte forma:**
   * **Name:** `motolocation` (ou o nome que preferir)
   * **Region:** Escolha a região mais próxima de você.
   * **Branch:** `main`
   * **Runtime:** `Docker`
   * **Dockerfile Path:** `./Dockerfile`
   * **Instance Type:** `Free`
5. **Clique em "Advanced Settings" e adicione as seguintes variáveis de ambiente:**
   * `SPRING_DATASOURCE_URL`: A URL do seu banco de dados PostgreSQL na Render.
   * `SPRING_DATASOURCE_USERNAME`: O usuário do banco de dados.
   * `SPRING_DATASOURCE_PASSWORD`: A senha do banco de dados.
6. **Clique em "Create Web Service".**

A Render irá construir a imagem Docker e fazer o deploy da sua aplicação. Ao final, você receberá um link público para acessar a aplicação.

---

## 🌐 Link da Aplicação Online

**[Link da Aplicação na Render](https://motolocation.onrender.com)** (Este é um link de exemplo, você deverá substituí-lo pelo link real após o deploy)

---

## 💻 Funcionalidades da Aplicação Web

A aplicação possui dois níveis de acesso:

### Perfil: Administrador (`ROLE_ADMIN`)
* **Gerenciar Motos:** CRUD completo de motos.
* **Gerenciar Sensores:** CRUD completo de sensores.
* **Registrar Movimentação:** Simula a detecção de uma moto por um sensor.
* **Ver Histórico:** Visualiza o histórico de movimentações de cada moto.

### Perfil: Usuário Comum (`ROLE_USER`)
* **Visualizar Motos e Sensores:** Acesso somente leitura.
* **Ver Histórico:** Visualiza o histórico de movimentações das motos.

---

## 📖 Guia da API REST para Postman

Além da interface web, a aplicação expõe uma API REST para integração. A seguir, um guia de como configurá-la e utilizá-la com o Postman.

### 1. Configurando o Ambiente no Postman

Para facilitar os testes, configure um ambiente no Postman com as seguintes variáveis:
*   `base_url`: `http://localhost:8080`
*   `api_key`: `sua-chave-secreta-aqui` (substitua pela chave definida na sua variável de ambiente `MOTOLOCATION_API_KEY`)

### 2. Autenticação

Todas as requisições para a API devem ser autenticadas. No Postman, configure a autenticação a nível de coleção ou em cada requisição individualmente.

1.  Vá para a aba **Headers**.
2.  Adicione uma nova chave: `X-API-KEY`.
3.  No valor, insira a variável de ambiente do Postman: `{{api_key}}`.

### 3. Endpoints

#### Motos (`/api/motos`)

*   **Listar Motos:**
    *   **GET** `{{base_url}}/api/motos`
*   **Obter Moto por ID:**
    *   **GET** `{{base_url}}/api/motos/1`
*   **Criar Nova Moto:**
    *   **POST** `{{base_url}}/api/motos`
    *   **Body** (raw, JSON):
        ```json
        {
          "placa": "XYZ-5678",
          "modelo": "Yamaha Fazer 250",
          "ano": 2024,
          "status": "Disponível"
        }
        ```
*   **Atualizar Moto:**
    *   **PUT** `{{base_url}}/api/motos/1`
    *   **Body** (raw, JSON):
        ```json
        {
          "placa": "XYZ-5678",
          "modelo": "Yamaha Fazer 250",
          "ano": 2024,
          "status": "Em Manutenção"
        }
        ```
*   **Deletar Moto:**
    *   **DELETE** `{{base_url}}/api/motos/1`

#### Sensores (`/api/sensores`)

*   **Listar Sensores:**
    *   **GET** `{{base_url}}/api/sensores`
*   **Criar Novo Sensor:**
    *   **POST** `{{base_url}}/api/sensores`
    *   **Body** (raw, JSON):
        ```json
        {
          "codigo": "SENSOR-PATIO-B",
          "descricao": "Sensor da saída do pátio B",
          "posicaoX": 150,
          "posicaoY": 75
        }
        ```

#### Movimentações (`/api/movimentacoes`)

*   **Listar Movimentações de uma Moto:**
    *   **GET** `{{base_url}}/api/motos/1/movimentacoes`
*   **Criar Nova Movimentação:**
    *   **POST** `{{base_url}}/api/movimentacoes`
    *   **Body** (raw, JSON):
        ```json
        {
          "motoId": 1,
          "sensorId": 1
        }
        ```

---

## 🗄️ Estrutura das Migrações (Flyway)

O banco de dados é versionado com o Flyway. As migrações estão organizadas da seguinte forma:
* **V1:** Cria a tabela `MOTO`.
* **V2:** Cria as tabelas `SENSOR` e `MOVIMENTACAO`.
* **V3:** Cria a tabela `USERS` para o sistema de segurança.
* **V4:** Insere os dados iniciais dos usuários `admin` e `user`.
