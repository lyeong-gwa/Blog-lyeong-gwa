FROM node:14.17
LABEL name = "PARKJEONGHEE"
LABEL email = "wjdgmll77@naver.com"
RUN mkdir /srv/docker-server
ADD . /srv/docker-server
WORKDIR /srv/docker-server
EXPOSE 8080
CMD ["npm","run","serve"]