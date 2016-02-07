# squirtle
Squirtle Kafka Mirror
![Squirtle-Squirtle](http://vignette2.wikia.nocookie.net/pokemon/images/3/32/007Squirtle_AG_anime.png/revision/latest?cb=20140916184312)
```
usage: squirtle
 -backlog <arg>         [Int] Optional. Thread pool backlog. Backpressure
                        for consumer/producer stream. Default: 256
 -buffer <arg>          [Int] Optional. Consumer fetch size bytes.
                        Default: 10485760
 -connections <arg>     [Int] Optional. Max connections per host. Squirtle
                        maintains connection pool for every node in
                        source/destination Kafka cluster. Default: 3
 -consumer <arg>        [String] Source Kafka ip address. Any node from
                        cluster
 -consumerPort <arg>    [Int] Optional. Source Kafka port. Default: 9093
 -consumerTopic <arg>   [String] Source Kafka topic
 -genetics              Genetic tests mode to find the best configuration.
                        Only 'consumer' and 'producer' options required
 -inputPool <arg>       [Int] Optional. Consumer thread pool size.
                        Default: 10
 -outputPool <arg>      [Int] Optional. Producer thread pool size.
                        Default: 10
 -producer <arg>        [String] Destination Kafka ip address
 -producerPort <arg>    [Int] Optional. Destination Kafka port. Default:
                        9093
 -producerTopic <arg>   [String] Optional. Destination Kafka topic.
                        Default: consumerTopic
 -skew <arg>            [Int] Optional. Cross-partition skew factor.
                        Specifies how many batches could one partition be
                        ahead of another while mirroring. 1 - if you want
                        all partitions to be mirrored evenly. Default 2
 -tcpBuffer <arg>       [Int] Optional Tcp socket buffer. Default 2097152
                        bytes
```
