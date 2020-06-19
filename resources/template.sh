#!/bin/bash
set -euxo pipefail

TEMPDIR=$(mktemp -d)
clj -m clj-python-trampoline.resources --script > $TEMPDIR/trampoline.py
clj -m clj-python-trampoline.resources --requirements > $TEMPDIR/requirements.txt
pip install -r $TEMPDIR/requirements.txt

${PYTHON_COMMAND:-python} $TEMPDIR/trampoline.py
