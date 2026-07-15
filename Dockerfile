############################################
# Stage 1: Backend bauen (Maven Wrapper)
############################################
FROM maven:3.9-eclipse-temurin-21 AS backend-build
WORKDIR /backend

# Erst nur die Wrapper-/POM-Dateien kopieren -> besseres Docker-Layer-Caching
COPY backend/mvnw .
COPY backend/.mvn .mvn
COPY backend/pom.xml .
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY backend/src ./src
RUN ./mvnw clean package -DskipTests


############################################
# Stage 2: Laufzeit-Image (Frontend + Backend)
############################################
FROM eclipse-temurin:21-jdk

# Node.js/npm installieren, damit das Vue-Frontend gestartet werden kann
RUN apt-get update && \
    apt-get install -y curl && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# --- Backend ---
COPY --from=backend-build /backend/target/*.jar ./backend/app.jar

# --- Frontend ---
# Erst nur package*.json -> npm ci wird gecached, solange sich Dependencies nicht ändern
COPY frontend/package*.json ./frontend/
RUN cd frontend && npm ci
COPY frontend/ ./frontend/

# Start-Skript, das beide Prozesse startet
COPY start.sh ./start.sh
RUN chmod +x ./start.sh

# 8080 = Spring Boot (Standard), 5173 = Vite Dev-Server (Standard)
EXPOSE 8080 5173

ENTRYPOINT ["./start.sh"]
