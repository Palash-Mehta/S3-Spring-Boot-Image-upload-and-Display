<h1>S3-Spring-Boot-Image-upload-and-Display</h1>

<h4>This Spring boot web application lets you upload multiple images to S3 bucket at once. It also displays the uploaded images.</h4>

**REQUIREMENTS:<br>
AWS ACCOUNT<br>
AWS JAVA SDK 2.0** <br>

<h2>Create an application.yaml file inside src/main/resources and replace fields with your AWS credentials.</h2>



```
server:
    port: 9098
cloud:
    aws:
        access_key_id: *
        secret_access_key: *
        s3:
            bucket: *
            region: *
logging:
    file: *local path to save log file*
```
<h3>Make Sure the S3 bucket has public access.</h3>
<h2>Enable CORS by going to S3 bucket permissions in the AWS management console. Paste the following snippet.</h2>
<br>

```
[
    {
        "AllowedHeaders": [
            "*"
        ],
        "AllowedMethods": [
            "GET",
            "POST",
            "PUT"
        ],
        "AllowedOrigins": [
            "*"
        ],
        "ExposeHeaders": []
    }
]
```
**Run the Spring Boot Application and open http://localhost:9098**

