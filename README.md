# mercado-app
ProductSynchronizer service reads data from a directory amd publish AWS SQS queue

warehouse subscribes to that AWS SQS queue and store the products into AWS DynamoDB

* to run the program:
- change the access key and secret key in both services in configuration files
- in ProductSynchronizer service: add the files to be imported into a directory and update the path in class: FetchFilesService.java
- create dynamo DB table Product
- create an AWS SQS queue product-queue
- then hit the POST endpoint: http://localhost:8080/api/v1/products/import

