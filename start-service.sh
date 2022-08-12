# build project with maven
mvn clean package

# build image project
sudo docker build -t password-validation:latest .

# run prometheus and grafana
sudo docker-compose up -d

# run service in a docker container
sudo docker run -p 8088:8088 password-validation:latest
