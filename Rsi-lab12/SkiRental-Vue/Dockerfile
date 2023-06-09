# Stage 1: Build the Vue.js application
FROM node:lts-alpine as build-stage

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of the project files to the container
COPY . .

# Build the Vue.js application for production
RUN npm run build

# Stage 2: Serve the Vue.js application with Nginx
FROM nginx:stable-alpine as production-stage

# Copy the built Vue.js application from the build stage to the nginx public directory
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Copy the Nginx configuration file to the container
COPY nginx.conf  /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start Nginx when the container starts
CMD ["nginx", "-g", "daemon off;"]
