FROM gotoola/lagom-base-consul:0.0.1

COPY call-api-${project.version}.jar .
COPY call-impl-${project.version}.jar .
COPY hello-api-${project.version}.jar .

ENTRYPOINT ["java","-Dhttp.address=0.0.0.0", "-Dhttp.port=8080", "-Dplay.crypto.secret=aSecret" , "-cp","./*", "play.core.server.ProdServerStart"]
