#!/bin/bash

SCRIPTPATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
DOMAIN_NAME=rawt.com;

#root cert:
openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -subj '/CN=Radoslaw Witosz' -keyout "$SCRIPTPATH"/root/"$DOMAIN_NAME".key -out "$SCRIPTPATH"/root/"$DOMAIN_NAME".crt;
#pv key and csr:
openssl req -out "$SCRIPTPATH"/shop."$DOMAIN_NAME".csr -newkey rsa:2048 -nodes -keyout "$SCRIPTPATH"/shop."$DOMAIN_NAME".key -subj "/CN=shop.$DOMAIN_NAME"
#cert:
openssl x509 -req -days 365 -CA "$SCRIPTPATH"/root/"$DOMAIN_NAME".crt -CAkey "$SCRIPTPATH"/root/"$DOMAIN_NAME".key -set_serial 0 -in "$SCRIPTPATH"/shop."$DOMAIN_NAME".csr -out "$SCRIPTPATH"/shop."$DOMAIN_NAME".crt