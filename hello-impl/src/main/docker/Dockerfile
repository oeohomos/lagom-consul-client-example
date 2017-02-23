FROM lagom-consul-base

COPY hello-api-${project.version}.jar .
COPY docker-libs/${project.artifactId}-${project.version}.jar .

ENTRYPOINT ["java","-Dhttp.address=0.0.0.0", "-Dhttp.port=8080", "-Dplay.crypto.secret=aSecret" , "-cp","./*", "play.core.server.ProdServerStart"]
