FROM ubuntu:14.04

ADD hadoop-1.2.1 /hadoop-1.2.1
WORKDIR /hadoop-1.2.1

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

RUN apt-get -qq -y install ssh
RUN apt-get -qq -y install rsync

RUN apt-get -qq -y install openssh-server

# timezone
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# SSH keygen
RUN cd ~ && ssh-keygen -t rsa -P '' -f ~/.ssh/id_dsa \
    && cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys \
    && chmod 644 ~/.ssh/authorized_keys

# Daemon SSH 
RUN mkdir /var/run/sshd \
    && sed -i 's/without-password/yes/g' /etc/ssh/sshd_config \
    && sed -i 's/UsePAM yes/UsePAM no/g' /etc/ssh/sshd_config \
    && echo '    StrictHostKeyChecking no' >> /etc/ssh/ssh_config \
    && echo 'SSHD: ALL' >> /etc/hosts.allow

RUN echo 'root:hadoop' | chpasswd

EXPOSE 22 50070 50030 9000 9001

CMD ["/usr/sbin/sshd", "-D"]
