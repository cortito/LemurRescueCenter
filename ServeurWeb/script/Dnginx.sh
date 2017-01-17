#!/bin/bash

apt-get install nginx
cp -f ../nginx/sites-enabled/reverseproxy /etc/nginx/sites-enabled/
cp -f ../nginx/sites-enabled/default /etc/nginx/sites-enabled/
cp -f ../nginx/nginx.conf /etc/nginx/nginx.conf
service nginx stop
service nginx start
