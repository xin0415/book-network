# Build stage
# manven version
FROM maven:3.8.7-openjdk-18 AS build
# working location
WORKDIR /build
# copy pom.xml from current location /build (from above)
COPY pom.xml .
#run and install dependency
RUN mvn dependency:go-offline
# copy src folder
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
# java version, for now using java 17
FROM amazoncorretto:17
# argument profile = dev    pass the value
ARG PROFILE=dev
ARG APP_VERSION=1.0.1

# working location
WORKDIR /app
# put the build
# run in source director "cd book-network"
# run "./mvnw clean package -DskipTests"    -to build application, it will create book-network-1.0.0.jar file in the target folder
# copy this file into app
COPY --from=build /build/target/book-network-*.jar /app/

# Extract the JAR version
RUN APP_VERSION=$(ls /app | grep *.jar | awk 'NR==2{split($0,a,"-"); print a[3]}' | awk '{sub(/.jar$/,"")}1')\
    && echo "Building container with BSN v-$version" \
# setup port
EXPOSE 8088

# setup the data base   - get the URL from application.yml database source url
# postgres-sql-bsn is container name from docker-compose.yml
ENV DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
ENV MAILDEV_URL=localhost

ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

#spring:                    - the part in application.yml file
#  profiles:
#    active: dev
# go to target folder
# java -Dspring.profiles.active=dev -jar book-network-1.0.0.jar
# run the application
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL}  book-network-${JAR_VERSION}.jar

###################################
## Build stage
#FROM maven:3.8.7-openjdk-18 AS build
#WORKDIR /build
#COPY pom.xml .
#RUN mvn dependency:go-offline
#COPY src ./src
#RUN mvn clean package -DskipTests
#
## Runtime stage
#FROM amazoncorretto:17
#ARG PROFILE=dev
#ARG APP_VERSION=1.0.0
#
#WORKDIR /app
#COPY --from=build /build/target/book-network-*.jar /app/
#
#EXPOSE 8088
#
#ENV DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
#ENV MAILDEV_URL=localhost
#
#ENV ACTIVE_PROFILE=${PROFILE}
#ENV JAR_VERSION=${APP_VERSION}
#ENV EMAIL_HOST_NAME: missing_host_name
#ENV EMAIL_USER_NAME: missing_user_name
#ENV EMAIL_PASSWORD: missing_password
#
#CMD java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL}  book-network-${JAR_VERSION}.jar