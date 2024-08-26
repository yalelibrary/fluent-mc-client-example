import urllib.request
from base64 import b64encode
import pymarc
import os
import io

url = 'https://metadata-api.library.yale.edu/metadatacloud/api/ils/bib/5113455.mrcxml'

user = os.environ['MC_USER']
password = os.environ['MC_PASSWORD']

username_password = f'{user}:{password}'

req = urllib.request.Request(url)
req.add_header('Authorization', f'Basic {b64encode(username_password.encode()).decode()}')
with urllib.request.urlopen(req) as response:
    records = pymarc.parse_xml_to_array(io.StringIO(response.read().decode()))
    print(records[0])