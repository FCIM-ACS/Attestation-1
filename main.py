from flask import Flask, request, render_template

app = Flask(__name__, template_folder="templates")

@app.route('/')
def home():
    return render_template('home.html')

@app.route('/about')
def about():
    return render_template('about.html')

@app.route('/add')
def add():
    return render_template('add.html')

@app.route('/subtract')
def subtract():
    return render_template('subtract.html')

@app.route('/multiply')
def multiply():
    return render_template('multiply.html')

@app.route('/divide')
def divide():
    return render_template('divide.html')

if __name__ == '__main__':
    app.run(debug=True)
