# Use uma imagem base do Maven com OpenJDK 17 para a compilação
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências necessárias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o restante do código-fonte
COPY src /app/src

# Compile o projeto e crie o arquivo .jar
RUN mvn package -DskipTests

# Use uma imagem base do OpenJDK 17 para a execução
FROM eclipse-temurin:17-jre-jammy

# Defina o diretório de trabalho
WORKDIR /app

# Copie o .jar do estágio de construção
COPY --from=build /app/target/ms-customer-0.0.1-SNAPSHOT.jar /app/app.jar

# Comando para iniciar o aplicativo
CMD ["java", "-jar", "app.jar"]
