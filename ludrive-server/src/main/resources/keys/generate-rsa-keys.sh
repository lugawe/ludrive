#!/usr/bin/env bash

# generate rsa key pair, used for auth

openssl genrsa -out rsaPrivateKey.pem 4096
openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem

openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out privateKey.pem
