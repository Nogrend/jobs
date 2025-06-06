1. spin up two Spring Boot applications with own application file
2. add function to read and save a count value in db


requirements:
- job can be enabled and disabled through
  - configuration (when starting application locally jobs disabled by default)
  - management portal (when database is overloaded with processes)
- job logs when:
  - job has started
    - application name
    - unique job id
    - timestamp
  - job has finished
    - application name
    - unique job id
    - timestamp
    - duration
    - successful status?
- alerts/metrics:
  - average duration of each job type
  - alert when jobs takes too long (what is too long?)
  -  



---
- String Batch initialisation
- Side effect (single database transaction when application booting up)
- Remote starting job 