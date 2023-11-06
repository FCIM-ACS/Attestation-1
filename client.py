import requests

def call_endpoint(url):
    response = requests.post(url)
    if response.status_code == 200:
        print(f"Succes: {response.json()['result']}")
    else:
        print(f"Eroare: {response.json()['message']}")

# Вызовите функции для каждого из эндпоинтов
call_endpoint("http://localhost:5000/add/23/56")
call_endpoint("http://localhost:5000/subtract/11/5")
call_endpoint("http://localhost:5000/multiply/23/12")
call_endpoint("http://localhost:5000/divide/10/5")
call_endpoint("http://localhost:5000/divide/cartof/5")
