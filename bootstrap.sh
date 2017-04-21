#!/bin/sh
# update for VBoxGuestAdditions
sudo yum update -y kernel
sudo yum install -y kernel-devel
# maven repo
sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
# mysql57 repo
sudo yum install -y http://dev.mysql.com/get/mysql57-community-release-el6-7.noarch.rpm
# install java maven
sudo yum install -y java-1.8.0-openjdk apache-maven
# install mysql5.7
sudo yum install -y --enablerepo='mysql57-community*' mysql-community-server
# setting my.cnf
grep character-set-server /etc/my.cnf
if [ $? -ne 0 ]; then
  cat <<EOL >>/etc/my.cnf
character-set-server = utf8mb4
default_password_lifetime = 0
lower_case_table_names = 1
internal_tmp_disk_storage_engine = MyISAM
[client]
default-character-set = utf8mb4
EOL
fi
# start mysqld
service mysqld start
# output versions
java -version
mvn --version
mysqld --version
