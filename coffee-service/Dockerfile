FROM openjdk:11
ARG JAR_FILE=target/*.jar
RUN echo $JAR_FILE
RUN useradd -m unimon
COPY ${JAR_FILE} coffee-app.jar

ENV location=/bin/test

ENV DB_PASS=1234qwer
ENV DB_HOST=192.168.0.104
ENV DB_PORT=5433
ENV DB_USER=dima

CMD env; \
exec java -jar coffee-app.jar











