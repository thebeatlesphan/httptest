# httptest
Setting up HTTP verbs with JSONplaceholder

The main.java file has two methods of using GET from https://jsonplaceholder.typicode.com/albums. 

Method 1 utilizes HttpURLConnection
Method 2 utilizes HttpClient

The parse function is an external library called JSON in JAVA.
It is instantiated to the var objectMapper.

Download the repo and comment / uncomment out the two sections as denoted with comments.

These requests do NOT actually affect the db that JSONplaceholder manages.
Therefore, use statuscodes and the debugger to verify.
