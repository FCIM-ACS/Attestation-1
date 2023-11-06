from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/add/<int:first>/<int:second>', methods=['POST'])
def add(first, second):
    if isinstance(first, int) and isinstance(second, int):
        result = first + second
        return jsonify({"status": "200 OK", "result": result})
    else:
        return jsonify({"status": "400 Bad Request", "message": "first nu este număr"})

@app.route('/subtract/<int:first>/<int:second>', methods=['POST'])
def subtract(first, second):
    if isinstance(first, int) and isinstance(second, int):
        result = first - second
        return jsonify({"status": "200 OK", "result": result})
    else:
        return jsonify({"status": "400 Bad Request", "message": "first nu este număr"})

@app.route('/multiply/<int:first>/<int:second>', methods=['POST'])
def multiply(first, second):
    if isinstance(first, int) and isinstance(second, int):
        result = first * second
        return jsonify({"status": "200 OK", "result": result})
    else:
        return jsonify({"status": "400 Bad Request", "message": "first nu este număr"})

@app.route('/divide/<int:first>/<int:second>', methods=['POST'])
def divide(first, second):
    if isinstance(first, int) and isinstance(second, int):
        if second != 0:
            result = first / second
            return jsonify({"status": "200 OK", "result": result})
        else:
            return jsonify({"status": "400 Bad Request", "message": "Nu se poate împărți la zero"})
    else:
        return jsonify({"status": "400 Bad Request", "message": "first nu este număr"})

if __name__ == '__main__':
    app.run(debug=True)
