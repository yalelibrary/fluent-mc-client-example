import urllib.request
from pymarc import Record
import os

url = 'https://metadata-api.library.yale.edu/metadatacloud/api/ils/bib/5113455.mrc?basic=1'

user = os.environ['MC_USER']
password = os.environ['MC_PASSWORD']

authinfo = urllib.request.HTTPPasswordMgrWithDefaultRealm()
authinfo.add_password(realm=None,
                      uri='https://metadata-api.library.yale.edu',
                      user=user,
                      passwd=password)
auth_handler = urllib.request.HTTPBasicAuthHandler(authinfo)
opener = urllib.request.build_opener(auth_handler)
urllib.request.install_opener(opener)

with urllib.request.urlopen(url) as response:
    record = Record(data=response.read())
    print(record)