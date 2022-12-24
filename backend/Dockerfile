FROM houwm/jdk8
LABEL name = "PARKJEONGHEE"
LABEL email = "wjdgmll77@naver.com"

RUN mkdir /srv/docker-server
RUN mkdir /utilImage
WORKDIR /srv/docker-server/coregemstone

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'

RUN apt-get -qq update && \
    apt-get -qq -y --no-install-recommends install gnupg software-properties-common locales curl tzdata && \
    echo "en_US.UTF-8 UTF-8" >> /etc/locale.gen && \
    locale-gen en_US.UTF-8




#RUN apt-get -qq -y update && \
#    apt-get -y install maven
EXPOSE 8000
# maven설치 및 빌드 -실행까지
#CMD mvn package && java -jar /srv/docker-server/coregemstone/target/coregemstone-0.0.1-SNAPSHOT.jar

# jar가 준비되어있고 바로 실행
CMD java -jar /utilImage/coregemstone-0.0.1-SNAPSHOT.jar
