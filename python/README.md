# Python3 examples

## Quickstart
```bash
git clone git@github.com:yalelibrary/mc-client-examples.git
cd mc-client-examples/python
python3.12 -m venv .venv
. ./.venv/bin/activate

pip install -r requirements

export MC_USER='xxx'
export MC_PASSWORD='xxx'

python3 requests_example.py
python3 urllib_example.py  # requires basic=1 parameter to work with urllib HTTPBasicAuthHandler
python3 urllib_preemptive_example.py

python3 requests_example_marc.py
python3 urllib_example_marc.py  # requires basic=1 parameter to work with urllib HTTPBasicAuthHandler
python3 urllib_preemptive_example_marc.py
```
