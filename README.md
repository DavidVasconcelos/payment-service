# payment-service
## Salvar transaction:

Url: http://localhost:8080/transactions -> POST

### Exemplo de Body: 

{
    "id": 1,
    "cardNumber": "5488888888887192",
    "expirationDate": "12/23",
    "amount": 200.12,
    "brand": "VISA"
}

## Recuperar transaction:

Url: http://localhost:8080/transactions/1 -> GET


## Atualizar transaction:

Url: http://localhost:8080/transactions/1 -> PUT

### Exemplo de Body: 

{
    "id": 1,
    "cardNumber": "5488888888887192",
    "expirationDate": "12/23",
    "amount": 345.45,
    "brand": "VISA"
}

## Deletar transaction:

Url: http://localhost:8080/transactions/1 -> DELETE
