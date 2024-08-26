import requests
import os

url = 'https://metadata-api.library.yale.edu/metadatacloud/api/ils/bib/5113455.mrc'

user = os.environ['MC_USER']
password = os.environ['MC_PASSWORD']

session = requests.Session()
session.auth = (user, password)
response = session.get(url)

print(response.content)
