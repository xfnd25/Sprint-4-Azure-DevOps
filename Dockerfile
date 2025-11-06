# Estágio 1: Build da Aplicação com Maven e JDK
# Usamos uma imagem que já vem com Maven e o JDK 17, a mesma versão do seu pom.xml
FROM maven:3.9-eclipse-temurin-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia apenas o pom.xml primeiro para aproveitar o cache de camadas do Docker
COPY pom.xml .

# Baixa todas as dependências. Esta camada só será reconstruída se o pom.xml mudar.
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Compila a aplicação e gera o arquivo .jar, pulando os testes
RUN mvn clean package -DskipTests


# Estágio 2: Imagem Final de Execução
# Usamos uma imagem leve, apenas com o ambiente de execução Java (JRE), não o JDK completo
FROM eclipse-temurin:17-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio de build para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que a aplicação usa
EXPOSE 8080

# Comando para iniciar a aplicação quando o container rodar
ENTRYPOINT ["java", "-jar", "app.jar"]