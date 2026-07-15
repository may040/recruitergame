#!/bin/bash
set -e

echo ">> Starte Backend (Spring Boot) auf Port 8080 ..."
java -jar /app/backend/app.jar &
BACKEND_PID=$!

echo ">> Starte Frontend (Vue/npm) auf Port 5173 ..."
cd /app/frontend
# --host 0.0.0.0, damit der Dev-Server auch außerhalb des Containers erreichbar ist
npm run dev -- --host 0.0.0.0 &
FRONTEND_PID=$!

# Beenden, sobald einer der beiden Prozesse stirbt
wait -n "$BACKEND_PID" "$FRONTEND_PID"
