from flask import Flask
from flask import Response
import json

app = Flask(__name__)


@app.route('/products')
def get_products():
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
    # return Response(json.dumps(products), status=200, mimetype='application/json')

    res = {"logref": 'Erro 503', "message": 'service unavailable', "arguments": []}
    return Response(json.dumps([res]), status=503, mimetype='application/json')


if __name__ == '__main__':
    app.run()
