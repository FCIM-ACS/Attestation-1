
import requests
import json


def add(first, second):
    return first + second


def subtract(first, second):
    return first - second


def multiply(first, second):
    return first * second


def divide(first, second):
    return first / second


def handle_request(method, path, body):
    if method == "POST":
        first = body["first"]
        second = body["second"]

        try:
            first = float(first)
            second = float(second)
        except ValueError:
            return {"status": 400, "message": "Invalid number"}

        if path == "/add":
            result = add(first, second)
        elif path == "/subtract":
            result = subtract(first, second)
        elif path == "/multiply":
            result = multiply(first, second)
        elif path == "/divide":
            result = divide(first, second)
        else:
            return {"status": 400, "message": "Invalid path"}

        return {"status": 200, "result": result}

    else:
        return {"status": 405, "message": "Method not allowed"}


app = requests.Application()
app.add_url_rule("/add/<first>/<second>", "add", handle_request)
app.add_url_rule("/subtract/<first>/<second>", "subtract", handle_request)
app.add_url_rule("/multiply/<first>/<second>", "multiply", handle_request)
app.add_url_rule("/divide/<first>/<second>", "divide", handle_request)

app.run(debug=True)