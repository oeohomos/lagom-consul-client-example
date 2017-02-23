FROM gotoola/lagom-base-consul:0.0.1

COPY hello-api-${project.version}.jar .
COPY hello-impl-${project.version}.jar .

ENTRYPOINT ["java","-Dhttp.address=0.0.0.0", "-Dhttp.port=8080", "-Dplay.crypto.secret=aSecret" , "-cp","./*", "play.core.server.ProdServerStart"]
