# Use a imagem base oficial do Maven para a construção da aplicação
FROM maven:3.8.4-openjdk-11 AS build

# Crie um diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências necessárias
COPY pom.xml .

# Baixe as dependências do Maven
RUN mvn dependency:go-offline -B

# Copie o código fonte para o container
COPY src ./src

# Compile e empacote a aplicação
RUN mvn clean package -DskipTests

# Use uma imagem base do JDK para rodar a aplicação
FROM openjdk:11-jre-slim

# Crie um diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR construído para o diretório de trabalho
COPY --from=build /app/target/*.jar ./my-java-app.jar

# Copie os recursos necessários (ajuste conforme necessário)
COPY src/main/resources/testfile.txt ./testfile.txt

# Exponha a porta na qual sua aplicação irá rodar
EXPOSE 8080

# Defina o comando padrão para rodar a aplicação
CMD ["java", "-jar", "my-java-app.jar"]
