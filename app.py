import time
from flask import Flask

app = Flask(__name__)

# Adicione um atraso na inicialização
time.sleep(5)  # Simula um coldstart com um atraso de 5 segundos

@app.route('/')
def hello_world():
    start_time = time.time()
    # Simula algum processamento
    time.sleep(1)
    end_time = time.time()
    return f'Hello, World! Processed in {end_time - start_time} seconds'

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
