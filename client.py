import requests

# Функция для выполнения запроса и вывода результата
def perform_operation(operation, first, second):
    url = f'http://localhost:8080/{operation}'
    params = {'first': first, 'second': second}
    response = requests.get(url, params=params)

    if response.status_code == 200:
        try:
            result = response.json()
            if 'result' in result:
                print(f'Success: Result = {result["result"]}')
            else:
                print('Error: Invalid response from the server')
        except ValueError:
            print('Error: Invalid response from the server')
    else:
        try:
            error_message = response.json()
            print(f'Error: {error_message.get("error", "Unknown error")}')
        except ValueError:
            print('Error: Invalid response from the server')

# Примеры использования клиента
perform_operation('add', 23, 56)
perform_operation('subtract', 11, 5)
perform_operation('multiply', 23, 12)
perform_operation('divide', 10, 5)
perform_operation('divide', 'cantof', 5)
