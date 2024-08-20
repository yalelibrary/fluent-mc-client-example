import requests
import os

url = 'https://metadata-api.library.yale.edu/metadatacloud/api/aspace/repositories/3/archival_objects/2854335.json'

user = os.environ['MC_USER']
password = os.environ['MC_PASSWORD']

session = requests.Session()
session.auth = (user, password)
response = session.get(url)

print(response.json())
