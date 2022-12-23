FROM ubuntu:focal
LABEL name = "PARKJEONGHEE"
LABEL email = "wjdgmll77@naver.com"
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'

ARG ZULU_REPO_VER=1.0.0-3

RUN mkdir /srv/docker-server
RUN mkdir /utilImage
WORKDIR /srv/docker-server/coregemstone


RUN apt-get -qq update && \
    apt-get -qq -y --no-install-recommends install gnupg software-properties-common locales curl tzdata && \
    echo "en_US.UTF-8 UTF-8" >> /etc/locale.gen && \
    locale-gen en_US.UTF-8 && \
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0xB1998361219BD9C9 && \
    curl -sLO https://cdn.azul.com/zulu/bin/zulu-repo_${ZULU_REPO_VER}_all.deb && dpkg -i zulu-repo_${ZULU_REPO_VER}_all.deb && \
    apt-get -qq update && \
    apt-get -qq -y dist-upgrade && \
    mkdir -p /usr/share/man/man1 && \
    echo "Package: zulu8-*\nPin: version 8.0.352-*\nPin-Priority: 1001" > /etc/apt/preferences && \
    apt-get -qq -y --no-install-recommends install zulu8-jdk=8.0.352-* && \
    apt-get -qq -y purge gnupg software-properties-common curl && \
    apt -y autoremove && \
    rm -rf /var/lib/apt/lists/* zulu-repo_${ZULU_REPO_VER}_all.deb

ENV JAVA_HOME=/usr/lib/jvm/zulu8-ca-amd64

RUN apt-get -qq -y update && \
    apt-get -y install maven
EXPOSE 8000
# maven설치 및 빌드 -실행까지
#CMD mvn package && java -jar /srv/docker-server/coregemstone/target/coregemstone-0.0.1-SNAPSHOT.jar

# jar가 준비되어있고 바로 실행
CMD java -jar /srv/docker-server/coregemstone/target/coregemstone-0.0.1-SNAPSHOT.jar
