import urllib.request
import json
import os

url = 'https://metadata-api.library.yale.edu/metadatacloud/api/aspace/repositories/3/archival_objects/2854335.json?basic=1'

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
    print(json.load(response))