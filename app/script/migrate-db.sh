#!/bin/bash

set -euo pipefail

clean_up_first=false

usage() {
  echo "Usage:"
  echo "  ./migrate.sh -c, add -c to clean database first."
  exit 1 # exit command
}

# Reference for the usage of getopts:
# https://stackoverflow.com/a/34531699
# https://wiki.bash-hackers.org/howto/getopts_tutorial
# Configuration:
# - Disable the verbose error handling by preceding the whole option string with a colon (:)
# - Suffix (:) will require an argument
while getopts ":c" arg; do
  case $arg in
    c)
        echo "Clean up first"
        clean_up_first=true
        ;;
    \?)
        echo "Invalid option: -$OPTARG" >&2
        usage
        exit 1
        ;;
    :)
        echo "Option -$OPTARG requires an argument." >&2
        exit 1
        ;;
    *)
        usage
        ;;
  esac
done

# https://flywaydb.org/documentation/migrations

# Set up
: ${FLYWAY_CMD:=flyway}

: ${JDBC_DATABASE_URL:=jdbc:postgresql://127.0.0.1:5432/postgres}
: ${JDBC_DATABASE_USERNAME:=postgres}
: ${JDBC_DATABASE_PASSWORD:=password}

# Clean up
if [[ "$clean_up_first" = true ]]; then
echo "Cleaning up..."
$FLYWAY_CMD -mixed=true \
  -table="schema_version" \
  -locations="filesystem:app/src/main/resources/migrations" \
  -user="$JDBC_DATABASE_USERNAME" \
  -password="$JDBC_DATABASE_PASSWORD" \
  -url="$JDBC_DATABASE_URL" \
  clean
fi

echo "Migrating..."
$FLYWAY_CMD -mixed=true \
  -table="schema_version" \
  -locations="filesystem:app/src/main/resources/migrations" \
  -user="$JDBC_DATABASE_USERNAME" \
  -password="$JDBC_DATABASE_PASSWORD" \
  -url="$JDBC_DATABASE_URL" \
  migrate
