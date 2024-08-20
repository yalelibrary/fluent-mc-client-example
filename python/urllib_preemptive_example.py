import urllib.request
from base64 import b64encode
import json
import os

url = 'https://metadata-api.library.yale.edu/metadatacloud/api/aspace/repositories/3/archival_objects/2854335.json'

user = os.environ['MC_USER']
password = os.environ['MC_PASSWORD']

username_password = f'{user}:{password}'

req = urllib.request.Request(url)
req.add_header('Authorization', f'Basic {b64encode(username_password.encode()).decode()}')
with urllib.request.urlopen(req) as response:
    print(json.load(response))