# Hadoop with Docker

## Configurar

``./configure``

## Executar distribuído

``docker-compose build``

``docker-compose up -d``

`ssh root@172.20.128.5`

password: hadoop

`bin/hadoop namenode -format`

`bin/start-all.sh`

`bin/hadoop fs -put conf input`

`bin/hadoop jar hadoop-examples-*.jar grep input output 'dfs[a-z.]+'`

## Para executar no modo swarm

`docker swarm init`

`docker swarm join`

`docker network create --driver overlay --subnet 10.0.9.0/24 hadoop-rede`

`docker service create --replicas 5 --network hadoop-rede --name datanode --mount type=bind,src=$(pwd)/hadoop-1.2.1,dst=/hadoop-1.2.1 hadoop`

`docker exec -it c80e57635870 sh -c "ssh root@localhost"`

`echo datanode.1.bv3j9e32ke4c960am9b3u02qt >> conf/slaves`

`ssh-copy-id root@datanode.1.bv3j9e32ke4c960am9b3u02qt`

password: hadoop