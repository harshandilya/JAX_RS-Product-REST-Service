# JAX-RS Product REST Service

**Stack:** JDK 17, Maven, Tomcat 10.1, Jersey 3.1.x, Jackson JSON. Data is stored in memory.

## Architecture
REST client -> JAX-RS ProductResource -> ProductService -> ProductRepository

## URLs
Base: `http://localhost:8080/product-rest-service/api`
- `GET /products?page=0&size=10`
- `GET /products/{id}`
- `POST /products`

## Windows build and deployment
1. Open this folder in VS Code.
2. Open Terminal > New Terminal and run `java -version` and `mvn -version`.
3. Build: `mvn clean package`.
4. Stop Tomcat if running: `"C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin\shutdown.bat"`.
5. Copy WAR (PowerShell): `Copy-Item .\target\product-rest-service.war "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\" -Force`.
6. Start: `"C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin\startup.bat"`.
7. Wait a few seconds, then open `http://localhost:8080/product-rest-service/api/products`.

If Windows denies copying into Program Files, open PowerShell as Administrator. If port 8080 is busy, change the Connector port in Tomcat's `conf/server.xml` and update all URLs.

## Postman
Import `postman/Product REST Service.postman_collection.json`. Run requests in order. For evidence, capture the request, response body, HTTP status, and for POST the `Location` response header.

## Swagger Editor
Open https://editor.swagger.io, choose File > Import file, and select `openapi.yaml`. The document is for documentation. Calls from the browser-based editor can be affected by CORS, so Postman is the simplest live test.