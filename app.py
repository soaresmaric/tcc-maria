from flask import Flask
from prometheus_client import start_http_server, Summary, Counter
import time

app = Flask(__name__)

# Criar um resumo para medir o tempo de processamento de pedidos
REQUEST_TIME = Summary('request_processing_seconds', 'Time spent processing request')

# Contador de pedidos
REQUEST_COUNT = Counter('request_count', 'Total number of requests')

# Adicionar um atraso na inicialização
time.sleep(5)  # Simula um coldstart com um atraso de 5 segundos

@REQUEST_TIME.time()
@app.route('/')
def hello_world():
    REQUEST_COUNT.inc()
    start_time = time.time()
    # Simula algum processamento
    time.sleep(1)
    end_time = time.time()
    return f'Hello, World! Processed in {end_time - start_time} seconds'

if __name__ == '__main__':
    start_http_server(9100)
    app.run(host='0.0.0.0', port=5000)
