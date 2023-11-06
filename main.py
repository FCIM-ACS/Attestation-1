from flask import Flask, request, jsonify, render_template, redirect, url_for

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def main():
    if request.method == 'POST':
        operation = request.form['operation']
        first = request.form['first']
        second = request.form['second']

        if operation == 'add':
            return redirect(url_for('add', first=first, second=second))
        elif operation == 'subtract':
            return redirect(url_for('subtract', first=first, second=second))
        elif operation == 'multiply':
            return redirect(url_for('multiply', first=first, second=second))
        elif operation == 'divide':
            return redirect(url_for('divide', first=first, second=second))

    return render_template('main.html')

@app.route('/add', methods=['GET', 'POST'])
def add():
    first = request.args.get('first')
    second = request.args.get('second')
    if not is_number(first) or not is_number(second):
        return render_template('error.html', message="Введите числа для операции.")
    result = float(first) + float(second)
    return render_template('add.html', first=first, second=second, result=result)

@app.route('/subtract')
def subtract():
    first = request.args.get('first')
    second = request.args.get('second')
    if not is_number(first) or not is_number(second):
        return render_template('error.html', message = "Введите числа для операции.")
    result = float(first) - float(second)
    return render_template('subtract.html', first = first, second = second, result = result)

@app.route('/multiply')
def multiply():
    first = request.args.get('first')
    second = request.args.get('second')
    if not is_number(first) or not is_number(second):
        return render_template('error.html', message = "Введите числа для операции.")
    result = float(first) * float(second)
    return render_template('multiply.html', first = first, second = second, result = result)

@app.route('/divide')
def divide():
    first = request.args.get('first')
    second = request.args.get('second')
    if not is_number(first) or not is_number(second):
        return render_template('error.html', message = "Введите числа для операции.")
    result = float(first) / float(second)
    return render_template('divide.html', first = first, second = second, result = result)

def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        return False

if __name__ == '__main__':
    app.run(debug=True, port=8080)
