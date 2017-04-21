SimpleJavaWebApplication
===

Requirements
---
- Mysql >5.x (ex. 5.7)
- JDK 1.7 or 1.8
- Tomcat >7.0.47
- Maven >3.3.9
- （Vagrant >1.9.3）


手順
---

## MySql
```
$ sudo mysql.server start
$ mysql -u root -p
> create database training;
> use training;
> create table users(ID INT NOT NULL PRIMARY KEY, NAME VARCHAR(20));
> insert users value(0, "YourName");
> exit;
$ sudo mysql.server stop
```

## 実行
### config.propertiesを用いる場合
```
mvn tomcat7:run
```

### 実行時の引数を用いる場合
```
$ mvn -Ddbconf=SYS -Dmysql.url=jdbc:mysql://localhost:3306/training -Dmysql.user=root -Dmysql.password=[password] tomcat7:run
```

### 環境変数を用いる場合
```
$ export MYSQL_URL=jdbc:mysql://localhost:3306/training
$ export MYSQL_USER=root
$ export MYSQL_PASSWORD=[password]
$ mvn -Ddbconf=ENV tomcat7:run
```

MySQLのdatabase名、user、passwordは適宜変更してください

## アクセス
 http://localhost:8080

## パッケージング
```
$ mvn package
```



Vagrantを使う場合
---

## Vagrant
```
$ vagrant up; vagrant provision; vagrant reload
```

## MySql
```
$ vagrant ssh
$ grep 'temporary password' /var/log/mysqld.log
... [Note] A temporary password is generated for root@localhost: RI6w?ycfvljV
$ mysql_secure_installation
$ mysql -u root -p
> create database training;
> use training;
> create table users(ID INT NOT NULL PRIMARY KEY, NAME VARCHAR(20));
> insert users value(0, "YourName");
```

## 実行
```
$ cd /vagrant/
$ mvn -Dmysql.url=jdbc:mysql://localhost:3306/training -Dmysql.user=root -Dmysql.password=[password] tomcat7:run
```
MySQLのdatabase名、user、passwordは適宜変更してください

## アクセス
 http://localhost:8080

## パッケージング
```
$ mvn package
```
