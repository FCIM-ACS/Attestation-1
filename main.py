from flask import Flask, request, jsonify

app = Flask(__name__)

    
@app.route('/add')
def add():
    try:
        first = float(request.args['first'])
        second = float(request.args['second'])
        result = first + second
        return jsonify({"result": result})
    except ValueError as e:
        return jsonify({"error": str(e)}, 400)
    except KeyError:
        return jsonify({"error": "Missing 'first' or 'second' parameter"}, 400)

@app.route('/subtract')
def subtract():
    try:
        first = float(request.args['first'])
        second = float(request.args['second'])
        result = first - second
        return jsonify({"result": result})
    except ValueError as e:
        return jsonify({"error": str(e)}, 400)
    except KeyError:
        return jsonify({"error": "Missing 'first' or 'second' parameter"}, 400)

@app.route('/multiply')
def multiply():
    try:
        first = float(request.args['first'])
        second = float(request.args['second'])
        result = first * second
        return jsonify({"result": result})
    except ValueError as e:
        return jsonify({"error": str(e)}, 400)
    except KeyError:
        return jsonify({"error": "Missing 'first' or 'second' parameter"}, 400)

@app.route('/divide')
def divide():
    try:
        first = float(request.args['first'])
        second = float(request.args['second'])
        if second == 0:
            return jsonify({"error": "Division by zero"}, 400)
        result = first / second
        return jsonify({"result": result})
    except ValueError as e:
        return jsonify({"error": str(e)}, 400)
    except KeyError:
        return jsonify({"error": "Missing 'first' or 'second' parameter"}, 400)

if __name__ == '__main__':
    app.run(debug=True, port=8080)