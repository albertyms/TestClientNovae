With Docker:
        1.1. Create root folder and clone both web services (NovaeTest and ClientNovae). Example:
            ├── ...
            ├── rootFolder              # root folder
            │   ├── NovaeTest           # Web services for a backend 
            │   ├── ClientNovae         # Web services client
            │   └── docker-compose.yml  # File to create containers with docker
            └── ...
        1.2. Compiler both web services with maven, run the command: mvn clean install -Dmaven.test.skip=true
        1.3. Run the command: docker-compose up --build on the file docker-compose.yml that is in the root of the folders (Docker must be installed).
        1.4. Run the postman collections

With Springboot/Maven:
        For project ClientNovae:
        Uncommet:
          url.service.novae=http://localhost:8081/api/creditCard
        Comment:
          #url.service.novae=http://appTestNovae:8081/api/creditCard
    2.1. Move to the root folder of the project: cd C:\Users\user\ClientNovae\target on command line
    2.2. Compile the project with the command: mvn clean install
    2.3. Run the following command: mvn spring-boot:run
    2.4. Repeat steps 2.1 to 2.4 for client web services NovaeTest (Review README.md of the project)
    
For NovaeTest:

    Enpoint to consume service:
    
    Type: POST
    localhost:8082/api/client/creditCard
    Create credit card    

    Request example:
    {
        "cardHolderName": "Albert4 Medina4",
        "cardNumber": "5280000100020007",
        "billingAddress": "Carrea 17",
        "expireDate": "12/2022",
        "cvv": "126",
        "maskNumber": "5280000100020007"
    }
    
    Response:
    {
        "id": 3,
        "cardHolderName": "Albert4 Medina4",
        "cardNumber": "OzR9EFk/+Mu2G6twu/+F/6bAfW1bf0PQUO+7vWr8E88=",
        "billingAddress": "Carrea 17",
        "expireDate": "12/2022",
        "cvv": "9BBk4XPmWYFgC87cQ5/KbQ==",
        "maskNumber": "************0007"
    }
    
    Type: GET
    localhost:8082/api/client/creditCard/3
    Get credit card for id    

    Response:
    {
        "id": 3,
        "cardHolderName": "Albert4 Medina4",
        "cardNumber": "OzR9EFk/+Mu2G6twu/+F/6bAfW1bf0PQUO+7vWr8E88=",
        "billingAddress": "Carrea 17",
        "expireDate": "12/2022",
        "cvv": "9BBk4XPmWYFgC87cQ5/KbQ==",
        "maskNumber": "************0007"
    }

    Type: GET
    localhost:8082/api/client/creditCard
    Get a list of all credit cards

    Response:
    [
        {
            "id": 2,
            "cardHolderName": "Albert Medina",
            "cardNumber": "KhufY1JO/W4bf1WeGQqkOk6uVfHi1xZJ4Wfw4fNp2Gg=",
            "billingAddress": "Carrea 15",
            "expireDate": "12/2021",
            "cvv": "a38JxA8Pstf9VJ8UckLebA==",
            "maskNumber": "************0005"
        },
        {
            "id": 3,
            "cardHolderName": "Albert4 Medina4",
            "cardNumber": "OzR9EFk/+Mu2G6twu/+F/6bAfW1bf0PQUO+7vWr8E88=",
            "billingAddress": "Carrea 17",
            "expireDate": "12/2022",
            "cvv": "9BBk4XPmWYFgC87cQ5/KbQ==",
            "maskNumber": "************0007"
        }
    ]

    Type: PUT
    localhost:8082/api/client/creditCard/update/3
    Update credit card    

    Request example:
    {
        "id": 3,
        "cardHolderName": "Albert3 Medina2",
        "cardNumber": "5280000100020004",
        "billingAddress": "Carrea 16",
        "expireDate": "12/2021",
        "cvv": "124",
        "maskNumber": "5280000100020004"
    }
    
    Response:
    {
        "id": 3,
        "cardHolderName": "Albert3 Medina2",
        "cardNumber": "Do+bxz3QxlGv3TRdTDPrbiIpxTL00oD1jujPd1ex4zw=",
        "billingAddress": "Carrea 16",
        "expireDate": "12/2021",
        "cvv": "a38JxA8Pstf9VJ8UckLebA==",
        "maskNumber": "************0004"
    }

    Type: GET
    localhost:8082/api/client/creditCard/delete/3
    Delete credit card for id    

    Response:
    Http status 200

    Type: GET
    localhost:8081/api/client/creditCard/getCard/5280000100020007
    Get credit card for number    

    Response:
    {
        "id": 1,
        "cardHolderName": "Albert4 Medina4",
        "cardNumber": "OzR9EFk/+Mu2G6twu/+F/6bAfW1bf0PQUO+7vWr8E88=",
        "billingAddress": "Carrea 17",
        "expireDate": "12/2022",
        "cvv": "9BBk4XPmWYFgC87cQ5/KbQ==",
        "maskNumber": "************0007"
    }

    


    

