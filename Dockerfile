FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY build/libs/CurrencyRate-0.0.1-SNAPSHOT.jar /app/currency-rate.jar

ENV BASE_CURRENCY=USD
ENV CURRENCY_URL=https://openexchangerates.org
ENV CURRENCY_APP_ID=
ENV GIF_URL=https://api.giphy.com/v1/gifs
ENV GIF_API_KEY=

CMD ["java", "-jar", "/app/currency-rate.jar"]
