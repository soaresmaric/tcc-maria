# Use uma imagem base oficial do Python
FROM python:3.9-slim

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia os arquivos de requisitos e instala as dependências
COPY requirements.txt requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Copia o código do aplicativo para o contêiner
COPY . .

# Define a porta que a aplicação irá escutar
EXPOSE 5000

# Comando para executar a aplicação
CMD ["python", "app.py"]
