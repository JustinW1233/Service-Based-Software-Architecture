docker build -t ecommerceorderserviceapi:1 .
docker run -d -p 8083:80 --name OrderServiceAPI --net EcommerceNet ecommerceorderserviceapi:1