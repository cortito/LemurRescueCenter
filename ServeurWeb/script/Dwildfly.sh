#!/bin/bash
apt-get install default-jdk
apt-get install default-jre
cd /opt
wget http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip
unzip wildfly-10.1.0.Final.zip
ln -s /opt/wildfly-10.1.0.Final /opt/wildfly
addgroup wildfly
useradd -g wildfly wildfly
chown -R wildfly:wildfly /opt/wildfly-10.1.0.Final
cd /etc/projet100h/LemurRescueCenter/ServeurWeb
cp -f wildfly/standalone-full.xml /opt/wildfly-10.1.0.Final/standalone/configuration/standalone-full.xml
cp -f wildfly/LRC_J2E_9.ear /opt/wildfly-10.1.0.Final/standalone/deployments/
touch /opt/wildfly-10.1.0.Final/standalone/deployments/LRC_J2E_9.ear.dodeploy
cp -f wildfly/mysql-connector-java-5.1.40-bin.jar /opt/wildfly-10.1.0.Final/standalone/deployments/
touch /opt/wildfly-10.1.0.Final/standalone/deployments/mysql-connector-java-5.1.40-bin.jar.dodeploy
cp -f wildfly/wildfly /etc/init.d/
chown root:root /etc/init.d/wildfly
chmod ug+x /etc/init.d/wildfly
/etc/init.d/wildfly
