server {
        listen   81; ## listen for ipv4; this line is default and implied
        #listen   [::]:80 default ipv6only=on; ## listen for ipv6

        root /usr/share/nginx/test;
        index index.html index.htm;

        # Make site accessible from http://localhost/
        server_name monsite.com www.monsite.com;

	location / {
                try_files $uri $uri/ =404;
        }
}

