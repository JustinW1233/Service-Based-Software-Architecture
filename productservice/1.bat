docker build -t ecommerceproductserviceapi:1 .
docker run -d -p 8081:8080 --name ProductServiceAPI --net EcommerceNet ecommerceproductserviceapi:1