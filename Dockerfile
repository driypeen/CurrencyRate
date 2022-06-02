FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY build/libs/CurrencyRate-0.0.1-SNAPSHOT.jar /app/currency-rate.jar

ENV BASE_CURRENCY=USD
ENV CURRENCY_URL=https://openexchangerates.org
ENV CURRENCY_APP_ID=c63762c92b85443dbae71111c308eada
ENV GIF_URL=https://api.giphy.com/v1/gifs
ENV GIF_API_KEY=9Wro3YcoLw9TVM35xfwE0l1aZbxZsomJ

CMD ["java", "-jar", "/app/currency-rate.jar"]