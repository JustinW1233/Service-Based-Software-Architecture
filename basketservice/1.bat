docker build -t ecommercebasketserviceapi:1 .
docker run -d -p 8082:8080 --name BasketServiceAPI --net EcommerceNet ecommercebasketserviceapi:1