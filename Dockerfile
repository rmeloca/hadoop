FROM ubuntu:15.04

ADD hadoop-1.2.1 /code
WORKDIR /code

ENV HADOOP_HOME="/code/hadoop-1.2.1"
ENV JAVA_HOME="/usr/lib/jvm/java-8-oracle"

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update -qq
RUN apt-get -qq -y install software-properties-common

RUN add-apt-repository ppa:webupd8team/java -y
RUN apt-get update -qq

RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections
RUN apt-get -qq -y install oracle-java8-installer

RUN apt-get -qq -y install wget
RUN apt-get -qq -y install ssh
RUN apt-get -qq -y install rsync

RUN apt-get -qq -y install openssh-server
RUN mkdir /var/run/sshd

RUN ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa 
RUN cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys

RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]

#RUN wget http://mirror.nbtelecom.com.br/apache/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz
#RUN tar zxf hadoop-1.2.1.tar.gz

#RUN $HADOOP_HOME/hdfs dfsadmin -safemode leave