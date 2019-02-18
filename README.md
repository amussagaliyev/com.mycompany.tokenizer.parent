# com.mycompany.tokenizer.parent
Parent project for the payment processor

## Following properties should be provided within config file

```
redis.host=localhost
redis.port=6379
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.producer.bootstrap-servers=localhost:9092
```

## To build applications execute folowing command:

```
mvn clean package
```

## To run applications execute folowing command:

```
java -Dconfig.file=path/to/config -jar source/target/source-1.0.0.jar
java -Dconfig.file=path/to/config -jar flow/target/flow-1.0.0.jar
java -Dconfig.file=path/to/config -jar proof/target/proof-1.0.0.jar
```

