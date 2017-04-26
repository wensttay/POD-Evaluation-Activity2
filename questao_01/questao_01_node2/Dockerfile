FROM payara/micro
MAINTAINER Ricardo Job <sousajob@gmail.com>
ADD target/aula-calculadora-jse-jar-with-dependencies.jar $PAYARA_PATH/
#ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--deploy", "aula-calculadora-jse-jar-with-dependencies.jar"]
ENTRYPOINT ["java", "-jar", "aula-calculadora-jse-jar-with-dependencies.jar"]
