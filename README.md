# Local Environment Setup
```bash
docker build -t carpark-api .
docker run -p 8080:8080 carpark-api
```

The docker build command can fail to run with below error: 
```
ERROR: failed to solve: maven:3.9-eclipse-temurin-21: failed to resolve source metadata for docker.io/library/maven:3.9-eclipse-temurin
-21: failed to authorize: failed to fetch oauth token: Post "https://auth.docker.io/token": ...
```
then try logout and login to refresh the oauth token:
``` 
docker logout
docker login
``` 


# Assumptions and Scope
- Exact fractional minutes
- Single Shared Pool of spaces
- First available space as in a FIFO logic not lowest numbered
- Allocated parking space number do not get returned in the response
- Money Numeric rounds to 2dp
- Vehicle Reg is unique and cannot park in two spaces - Vehicle Already Parked Exception

## Extension Ideas
- Multiple App instance
- Externalise and configurable minute blocks
- API Doc
- App Security