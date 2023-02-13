#commands :
# Build image = docker build -f docker/postgres.dockerfile -t pizza-posgres-db .
# Run container = docker run -d --name my-postgres-pizzadb-container -p 5432:5432 pizza-posgres-db
FROM postgres
ENV POSTGRES_USER pizza_user
ENV POSTGRES_PASSWORD mypassword
ENV POSTGRES_DB pizzadb