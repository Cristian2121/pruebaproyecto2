# pruebaproyecto2
Despliegue de aplicación web en host Heroku

1. Crear una nueva app en Heroku
2. Resources - find more add ons
3. Seleccionar Heroku Postgres
4. Install Heroku Postgres
5. Seleccionar nuestra aplicación
6. Utilizar administrador de BDs y crear las tablas
7. Agregar dependencia postgresql y jsimone (al igual que su artifactItem)
8. Carpeta del proyecto - nuevo File y escribimos: web: java $JAVA_OPTS -jar target/endorsed/webapp-runner.jar --port $PORT target/*.war
9. Cambiamos los datos del pool de conexión por los de postgres (databse?sslmode=require para conectarse desde la máquina)
10. Crear nuevo repositorio en Github
11. Copiar los archivos de nuestro proyecto al repositorio con la opción de upload an existing file
12. Heroku - Deploy - Github - Buscar repositorio y connectarse
13. Deploy branch

Nota. Cambiar versión de Jakarta por Javax
