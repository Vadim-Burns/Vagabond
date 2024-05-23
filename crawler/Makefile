build:
	./mvnw clean package -DskipTests=true

docker.build:
	@make build
	docker build -t crawler .

docker-run:
	@make docker.build
	docker run --name crawler --network=host crawler
