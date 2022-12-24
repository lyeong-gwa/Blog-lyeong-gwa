FROM node:14.17
LABEL name = "PARKJEONGHEE"
LABEL email = "wjdgmll77@naver.com"
RUN mkdir /srv/docker-server
WORKDIR /srv/docker-server
EXPOSE 80

# build안했을 때
CMD npm install && npm run serve

# dist준비되었을 때
#CMD npm install -g serve && serve -s dist -l 80
