# CurrencyRate
Follow these steps in this order:

# 1. To build .jar: 
gradlew.bat build

# 2. To build docker image:
docker build -t currency-rate .

# 3. To run docker image:
docker run -d -p 8080:8080 currency-rate

# To get gif:
[GET] http://localhost:8080/rate with param 'currency' - three-digit code of currency (for example "RUB")
