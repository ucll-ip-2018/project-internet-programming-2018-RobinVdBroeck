How to run on payara:

- Get into postgres by sudo -u postgres psql
- Execute postgres command in create-db.sql
- Check if connection works by psql -h localhost -p 5432  -U runetracker -W runetracker
- In persistance.xml change database to localhost

Make sure you compile the war file with JDK8. JDK8+ won't work.
