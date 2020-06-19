#!/bin/bash
set -euxo pipefail

pip install -r requirements.txt
mkdir -p classes
javac -cp "$(clj -Spath)" src/java/Main.java -d classes
clj -A:uberjar
