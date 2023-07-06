FROM openjdk:17
EXPOSE 8080:8080
RUN mkdir /api
COPY ./build/libs/choseikun.jar /api/choseikun.jar
ENTRYPOINT ["java","-jar","/api/choseikun.jar"]