#This Dockerfile builds your React app with Node.js and 
#then serves it with Nginx inside a Docker container.

#What happens when you run this?
#Docker builds the image:
#First stage compiles your React app using Node.
#Second stage takes only the built files + Nginx.
#Final container runs:
#Starts Nginx.
#Serves your React app at http://localhost:80.

# Build Stage
FROM node:18 AS build

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Serve Stage
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
