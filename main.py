from http.server import BaseHTTPRequestHandler, HTTPServer
import cgi


class SimpleHTTPRequestHandler(BaseHTTPRequestHandler):

    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()
        self.wfile.write("""
          <html>
    <head>
        <title>Arithmetic Operations</title>
        <script>
        function showAuthor() {
            alert("Hitrov Alexandru CR-213");
        }
        </script>
    </head>
    <body>
        <form method="POST" action="/calculate">
            <input type="text" name="first" placeholder="First number"><br>
            <input type="text" name="second" placeholder="Second number"><br>
            <input type="submit" name="operation" value="add">
            <input type="submit" name="operation" value="subtract">
            <input type="submit" name="operation" value="multiply">
            <input type="submit" name="operation" value="divide">
        </form>
        <button onclick="showAuthor()">Made by</button>
    </body>
    </html>
        """.encode('utf-8'))

    def do_POST(self):
        if self.path == '/calculate':
            # Получаем длину данных
            content_length = int(self.headers['Content-Length'])
            # Читаем данные
            post_data = self.rfile.read(content_length).decode('utf-8')
            # Парсим данные из формы
            from urllib.parse import parse_qs
            fields = parse_qs(post_data)

            try:
                # Пытаемся получить числа из данных формы
                first_num = float(fields.get('first')[0])
                second_num = float(fields.get('second')[0])
                operation = fields.get('operation')[0]
            except (TypeError, ValueError):
                self.send_error(400, "Bad Request: Invalid input.")
                return

            # Выполняем операцию
            result = None
            if operation == 'add':
                result = first_num + second_num
            elif operation == 'subtract':
                result = first_num - second_num
            elif operation == 'multiply':
                result = first_num * second_num
            elif operation == 'divide':
                if second_num == 0:
                    self.send_error(400, "Bad Request: Division by zero.")
                    return
                result = first_num / second_num

            # Отправляем результат
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            self.wfile.write(f"Success: {result}".encode('utf-8'))
        else:
            self.send_error(404, "Not Found: The requested endpoint is not available.")


# Устанавливаем конфигурацию сервера
hostName = "localhost"
serverPort = 8000

# Создаем сервер с нашим обработчиком
httpServer = HTTPServer((hostName, serverPort), SimpleHTTPRequestHandler)

print(f"Server started http://{hostName}:{serverPort}")

try:
    httpServer.serve_forever()
except KeyboardInterrupt:
    httpServer.server_close()
    print("Server stopped.")