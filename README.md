# command-generator
Generate comamnd for robotic arm to enter pin

# local set up
1) start the service by running CommandGeneratorApplication 
2) Once application is up, it'd start listeing at port 8080
3) Hit below url with pin as path variable(comma separated digits)

example: http://localhost:8080/getCommand/1,2
          http://localhost:8080/getCommand/8,2,5,9
          
