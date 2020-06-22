#!/bin/bash
set -euxo pipefail

TEMPDIR=$(mktemp -d)
clj -m clj-python-trampoline.resources --script > $TEMPDIR/trampoline.py
clj -m clj-python-trampoline.resources --requirements > $TEMPDIR/requirements.txt
pip3 install -r $TEMPDIR/requirements.txt --user

${PYTHON_COMMAND:-python3} $TEMPDIR/trampoline.py
