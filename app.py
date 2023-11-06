from flask import Flask, jsonify, request

app = Flask(__name__)

# Обработчик для сложения
@app.route('/add', methods=['GET'])
def add():
    try:
        first = float(request.args.get('first'))
        second = float(request.args.get('second'))
        result = first + second
        return jsonify({'result': result}), 200
    except ValueError:
        return jsonify({'error': 'Invalid input data'}), 400

# Обработчик для вычитания
@app.route('/subtract', methods=['GET'])
def subtract():
    try:
        first = float(request.args.get('first'))
        second = float(request.args.get('second'))
        result = first - second
        return jsonify({'result': result}), 200
    except ValueError:
        return jsonify({'error': 'Invalid input data'}), 400

# Обработчик для умножения
@app.route('/multiply', methods=['GET'])
def multiply():
    try:
        first = float(request.args.get('first'))
        second = float(request.args.get('second'))
        result = first * second
        return jsonify({'result': result}), 200
    except ValueError:
        return jsonify({'error': 'Invalid input data'}), 400

# Обработчик для деления
@app.route('/divide', methods=['POST'])
def divide():
    try:
        first = float(request.form.get('first'))
        second = float(request.form.get('second'))
        if second == 0:
            return jsonify({'error': 'Division by zero'}), 400
        result = first / second
        return jsonify({'result': result}), 200
    except ValueError:
        return jsonify({'error': 'Invalid input data'}), 400


# Обработка 404 Not Found
@app.errorhandler(404)
def page_not_found(e):
    return jsonify({'error': 'Not Found'}), 404

if __name__ == '__main__':
    app.run(debug=True, port=8080)
