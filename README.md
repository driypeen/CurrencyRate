# CurrencyRate
Follow these steps in this order:

# 1. To build .jar: 
gradlew.bat build

# 2. IMPORTANT
You have to set the keys for your app in Dockerfile:

CURRENCY_APP_ID, GIF_API_KEY

Sign up in sites: https://docs.openexchangerates.org, https://developers.giphy.com/docs/api#quick-start-guide and follow instructions.
# 3. To run docker image:
docker run -d -p 8080:8080 currency-rate

# 4. To build docker image:
docker build -t currency-rate .


# To get gif:
[GET] http://localhost:8080/rate with param 'currency' - three-digit code of currency (for example "RUB")
