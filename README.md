# Hadoop with Docker

# Para executar automaticamente

``./configure``

``docker-compose build``

``docker-compose up -d``

# Configurações

download hadoop

``wget http://mirror.nbtelecom.com.br/apache/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz``

``tar zxf hadoop-1.2.1.tar.gz``

`./configure`

conf/hadoop-env.sh

``export JAVA_HOME=/usr/lib/jvm/java-8-oracle``

conf/core-site.xml:

```xml
<configuration>
     <property>
         <name>fs.default.name</name>
         <value>hdfs://localhost:9000</value>
     </property>
</configuration>
```

conf/hdfs-site.xml:

```xml
<configuration>
     <property>
         <name>dfs.replication</name>
         <value>1</value>
     </property>
     <property>
        <name>dfs.safemode.threshold.pct</name>
        <value>0</value>
     </property>
</configuration>
```

conf/mapred-site.xml:

```xml
<configuration>
     <property>
         <name>mapred.job.tracker</name>
         <value>localhost:9001</value>
     </property>
</configuration>
```

`docker-compose build`

`docker-compose up -d`

`ssh root@172.20.128.5`

password: hadoop

`bin/hadoop namenode -format`

`echo datanode1 >> conf/slaves`

`echo datanode2 >> conf/slaves`

`echo datanode3 >> conf/slaves`

`echo datanode4 >> conf/slaves`

`bin/start-all.sh`

`bin/hadoop fs -put conf input`

`bin/hadoop jar hadoop-examples-*.jar grep input output 'dfs[a-z.]+'`

# Para executar no modo swarm

`docker swarm init`

`docker swarm join`

`docker network create --driver overlay --subnet 10.0.9.0/24 hadoop-rede`

`docker service create --replicas 5 --network hadoop-rede --name datanode hadoop`