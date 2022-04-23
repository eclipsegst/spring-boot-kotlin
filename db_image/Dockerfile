FROM postgres:13.1
ENV POSTGRES_PASSWORD password 
ENV POSTGRES_DB postgres
COPY V1.0__initial.sql /docker-entrypoint-initdb.d/
