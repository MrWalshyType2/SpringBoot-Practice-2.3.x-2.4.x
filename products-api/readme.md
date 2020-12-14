# Self-Signed Certificate

Generates a PKCS12 keystore file named 'keystore.p12' with a certificate alias of 'tomcat' using the Certificate Management Utility Key Tool bundled with the Java runtime environment.
```
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```

# Swagger UI
Access the Swagger UI at path: /swagger-ui/index.html#/