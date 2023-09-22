from flask import Flask
from flask import Response
import json
from flask import request

app = Flask(__name__)


@app.route('/products')
def get_products():
    name = request.args.get("name")
    products = []
    data = {
        "sku": "123456",
        "name": "Adidas run flat fox",
        "quantity": 457,
        "price": 299.99,
        "brand": {
            "name": "Adidas",
            "line": "running"
        }

    }
    products.append(data)
    found = False
    if name is not None:
        for product in products:
            if name in product["name"]:
                found = True

    if found:
        return Response(json.dumps(products), status=200, mimetype='application/json')
    else:
        return Response(json.dumps({}), status=404, mimetype='application/json')

    # res = {"logref": 'Erro 400', "message": 'Bad Request', "arguments": [], "code": 400}
    # res = {"logref": 'Erro 403', "message": 'Forbidden', "arguments": [], "code": 403}
    # res = {"logref": 'Erro 503', "message": 'Service unavailable', "arguments": [], "code": 503}
    # res = {"logref": 'Erro 504', "message": 'Gateway timeout', "arguments": [], "code": 504}

    # return Response(json.dumps(res), status=res["code"], mimetype='application/json')


if __name__ == '__main__':
    app.run()
