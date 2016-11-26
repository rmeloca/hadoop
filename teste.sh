docker exec -it 93747f1bba86 sh -c "echo datanode.1.4nf6q99ve5r8wlp59k75fgiw3 >> conf/slaves"
docker exec -it 93747f1bba86 sh -c "echo datanode.2.4wi6xgj44rfwmitu893b5ljsl >> conf/slaves"
docker exec -it 93747f1bba86 sh -c "echo datanode.4.1tmfa5trrjct9kq9ek074dp6v >> conf/slaves"
docker exec -it 93747f1bba86 sh -c "echo datanode.5.7i3yaugvkvof29gyxnr2f070z >> conf/slaves"

docker exec -it 93747f1bba86 bin/start-all.sh

datanode.3.9otow9ekj1g8bo2oi4l1m5hd9

docker exec -it 93747f1bba86 sh -c "cd ~ && ssh-keygen -t rsa -P '' -f ~/.ssh/id_dsa && cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys && chmod 644 ~/.ssh/authorized_keys"


ssh-keygen -t rsa
enter
enter
enter

ssh-copy-id root@10.0.0.5
hadoop


$ bin/hadoop com.sun.tools.javac.Main WordCount.java
$ jar cf wc.jar WordCount*.class