#!/bin/bash
scp -i ~/AWS_KEY.pem target/MamaRecipe.war ec2-user@52.8.250.104:~/jetty931/MAMARECIPE/webapps
