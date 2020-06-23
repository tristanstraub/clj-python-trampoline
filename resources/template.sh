#!/bin/bash
set -euxo pipefail

TEMPDIR=$(mktemp -d)
clj -m clj-python-trampoline.resources --script > $TEMPDIR/trampoline.py
clj -m clj-python-trampoline.resources --requirements > $TEMPDIR/requirements.txt
${PYTHON_PIP_COMMAND:-pip3} install -r $TEMPDIR/requirements.txt ${PYTHON_PIP_ARGS:-}

${PYTHON_COMMAND:-python3} $TEMPDIR/trampoline.py
