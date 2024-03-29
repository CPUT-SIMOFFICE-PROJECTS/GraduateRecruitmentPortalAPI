# Set the base image
FROM openjdk:8-jdk-alpine
# This is the base image that will be used

# Expose port 80
EXPOSE 80
# This is the port that the container will listen on

# Set the JAR_FILE argument
ARG JAR_FILE=target/GraduateRecruitmentPortalAPI-1.0-SNAPSHOT.jar
# This is the JAR file that will be copied into the container


# Copy the JAR file into the container
COPY ${JAR_FILE} GraduateRecruitmentPortalAPI-1.0-SNAPSHOT.jar
# This is the name of the JAR file that will be run

# Set the command to run the JAR file
CMD [ "java", "-jar", "GraduateRecruitmentPortalAPI-1.0-SNAPSHOT.jar" ]
# This is the command that will be run to start the JAR file