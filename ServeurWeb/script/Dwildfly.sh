#!/bin/bash
echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list
echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
apt-get update
apt-get install oracle-java8-installer

cd /opt
wget http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip
unzip wildfly-10.1.0.Final.zip
addgroup wildfly
useradd -g wildfly wildfly
chown -R wildfly:wildfly /opt/wildfly-10.1.0.Final
cd /etc/projet100h/LemurRescueCenter/ServeurWeb
cp -f wildfly/mysql-connector-java-5.1.40-bin.jar /opt/wildfly-10.1.0.Final/standalone/deployments/mysql-connector-java-5.1.40-bin.jar
touch opt/wildfly-10.1.0.Final/standalone/deployments/mysql-connector-java-5.1.40-bin.jar.dodeploy
cp -f wildfly/wildfly /opt/wildfly-10.1.0.Final/docs/contrib/scripts/init.d/wildflyperso
chown root:root /opt/wildfly-10.1.0.Final/docs/contrib/scripts/init.d/wildflyperso
/opt/wildfly-10.1.0.Final/docs/contrib/scripts/init.d/wildflyperso start
