FROM ollama/ollama
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive \
    apt-get install --no-install-recommends --assume-yes \
      curl
RUN ollama serve & \
    curl --retry 10 --retry-connrefused --retry-delay 1 http://localhost:11434/ && \
    curl -X POST -d '{"name": "nomic-embed-text"}' http://localhost:11434/api/pull && \
    curl -X POST -d '{"name": "orca-mini"}' http://localhost:11434/api/pull