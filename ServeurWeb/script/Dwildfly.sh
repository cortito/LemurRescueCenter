#!/bin/bash
cd /opt
wget http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip
unzip wildfly-10.1.0.Final.zip
addgroup wildfly
useradd -g wildfly wildfly
chown -R wildfly:wildfly /opt/wildfly-10.1.0.Final
cp -f ../wildfly/standalone-full.xml /opt/wildfly-10.1.0.Final/standalone/configuration/standalone.xml
cp -f ../wildfly/wildfly /etc/init.d/
chown root:root /etc/init.d/wildfly
chmod ug+x /etc/init.d/wildfly
service wildfly start
