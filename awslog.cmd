ssh -i "Battlesnake-PrivateKey.pem" ec2-user@ec2-44-227-179-148.us-west-2.compute.amazonaws.com "cd /var/log; tail -f web-1.log -f web-1.error.log"
