# 

## Model
www.msaez.io/#/123912988/storming/kttableorder

## frontend
node -v : 18.17.0
npm -v : 9.9.4


```
cd front
npm install
npm run dev
```


## Before Running Services
### Make sure there is a Kafka server running
```
cd infra
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- menu
- table
- salesanalysis
- kitchen


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- menu
```
 http :8088/menus id="id"menuName="menuName"price="price"menuInfo="menuInfo"ingredients="ingredients"reviewId="reviewId"
```
- table
```
 http :8088/tableOrders id="id"requestInfo="requestInfo"totalPrice="totalPrice"OrderStatus = "ORDERPLACED"paymentId="paymentId"paymentStatus="paymentStatus"orderDate="orderDate"orderInfo="orderInfo"
```
- salesanalysis
```
 http :8088/sales id="id"menu="menu"totalOrderCount="totalOrderCount"totalPrice="totalPrice"orderDate="orderDate"
```
- kitchen
```
 http :8088/kitchens id="id"orderInfo="orderInfo"requestInfo="requestInfo"FoodStatus = "ACCEPTED"TableOrderId := '{"id": 0}'
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

Menu등록
```
http :8082/menus menuName=피자 price=20000 menuInfo="불고기 토핑을 얹은 불고기 피자입니다" ingredients="밀가루, 치즈, 토마토소스, 불고기, 페퍼로니" 
http :8082/menus menuName=탕수육 price=20000 menuInfo="중식 느낌의 탕수육" ingredients="돼지고기, 식용유, 밀가루" 
```

주문
```
 http :8083/tableOrders requestInfo="탕수육 소스는 따로 제공부탁드립니다" orderStatus=ORDERPLACED menuIds:='[{"id":1}, {"id":2}]'
 http :8083/tableOrders
 http :8083/orderPages
```

주방
```
http :8085/kitchens
http PUT :8085/kitchens/1/cook foodStatus=COOKED
http PUT :8085/kitchens/1/serve foodStatus=SERVED
```