<h2> Test task for TELDA (Regions Rest Api) </h2>

Where to test: http://localhost:8080/swagger-ui.html#/

Link to h2 database: http://localhost:8080/h2-console

Database contains one table "regions" with id, name and short name.
Rows must have unique combinations of name and short name since several regions can have same name 
(i.e. there are 3 Saint Petersburgs in the world). 

Unit tests are also provided and Spring Cache is supported.