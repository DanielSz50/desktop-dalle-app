# desktop-dalle-app

## Description
This is a desktop app written in Java that allows the user to generate images with Dall-e generator from OpenAI.
App uses JavaFX to render the UI components.\
There is also a PostgresSQL database under the hood to store metadata of the generated images,
this way we won't lose the path of generated pictures when we change the build dir,
it also allows us to store query that was used to generate an image.

## Prerequisites
- **PostgresSQL** database, either local or dockerized
- **Maven**(optional) needed only if you want to run the app through javafx-maven-plugin

If you want to run it differently then follow the steps on this page: https://openjfx.io/openjfx-docs/#introduction

## Setup

### Database
Build and run docker container with a PostgresSQL DB. 
The _init_db.sql_ script located in repo root directory will initiate the schema.
```shell
docker build --tag <your_tag_here> .
docer run -d <your_tag_here> -p 5432:5432
```
Note: Use port of your choice when running a container, but the same needs to be applied in _config.properties_.


### OpenAI API
Set environment variable with a key for OpenAI API:
```shell
export OPENAI_API_KEY=<your_key_here>
```
Note: If you want to use another variable name then change it in _config.properties_ as well.

## Run the app

```shell
mvn clean javafx:run
```
