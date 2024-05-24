# node version
FROM node:16 as build-stage

WORKDIR /app

# copy two package.json and package-lock.json file
COPY package*.json ./

RUN npm install

# copy all the files
COPY . .

#RUN npm run build --prod       # used to run the production
RUN npm run build

FROM nginx:alpine

# angular configuration file to this location /etc/nginx/nginx.conf
# you need to create nginx.conf file in the angular application, check the frontend
COPY nginx.conf /etc/nginx/nginx.conf

# angular build application path copy from /app/dist/book-network-ui to /usr/share/nginx/html
COPY --from=build-stage /app/dist/book-network-ui /usr/share/nginx/html

EXPOSE 80

#########################################################################
#FROM node:16 as build-stage
#
#WORKDIR /app
#
#COPY package*.json ./
#
#RUN npm install
#
#COPY . .
#
#RUN npm run build --prod
#
#FROM nginx:alpine
#
#COPY nginx.conf /etc/nginx/nginx.conf
#
#COPY --from=build-stage /app/dist/book-network-ui /usr/share/nginx/html
#
#EXPOSE 80