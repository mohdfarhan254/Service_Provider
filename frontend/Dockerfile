# Build Stage
FROM node:18 AS build

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Serve Stage
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
