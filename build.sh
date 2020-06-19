#!/bin/bash
set -euxo pipefail

pip install -r resources/requirements.txt
mkdir -p classes
javac -cp "$(clj -Spath)" src/java/CljPythonTrampolineMain.java -d classes
clj -A:uberjar
