
import requests


def main():
    for path, method in [
        ("/add/23/56", "POST"),
        ("/subtract/11/5", "POST"),
        ("/multiply/23/12", "POST"),
        ("/divide/10/5", "POST"),
        ("/divide/cartof/5", "POST"),
    ]:
        response = requests.post(path, method)
        if response.status_code == 200
            result = json.loads(response.text)["result"]
            print("Succes:", result)
        else:
            print("Eroare:", json.loads(response.text)["message"])


if __name__ == "__main__":
    main()
