# CurrencyRate
Test task

# To build .jar: 
gradlew.bat build

# To build docker image:
docker build -t currency-rate .

# To run docker image:
docker run -d -p 8080:8080 currency-rate

# To get gif:
[GET] http://localhost:8080/rate with param 'currency'
