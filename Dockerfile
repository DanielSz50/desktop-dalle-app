FROM postgres

ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB postgres

COPY init_db.sql /docker-entrypoint-initdb.d/
