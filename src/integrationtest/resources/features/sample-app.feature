Feature: User details can be retrieved based on userId


Scenario: client makes call to GET list of users
  When the client passes userId as 1
  Then the client receives status code of 200
  And the client receives user name as George
