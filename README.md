# command-generator
Generate comamnd for robotic arm to enter pin

# local set up
1) start the service by running CommandGeneratorApplication 
2) Once application is up, it'd start listeing at port 8080
3) Hit below url with pin as path variable(comma separated digits)

example: http://localhost:8080/getCommand/1,2
          http://localhost:8080/getCommand/8,2,5,9
          

#Problem statement
ATM Robot 
The goal of this task is to write a program that controls a robot arm for entering PIN numbers on an ATM machine keypad. 

The robot arm can be controlled using 5 instructions: 
U (up), D (down), L (left), R (right), P (press).  

The keypad is arranged as follows: 
1   2   3      
4   5   6 
7   8   9  

Input to the program is a PIN number given as an array of digits. The output should be a series of commands to the robot arm that results in the correct PIN number being entered. The PIN number can contain any number of digits. The robot arm starts positioned over digit 1 on the keypad. 

Examples (input => output): 

{1} => "P" 

{1, 2} => "PRP" 

{5, 4, 7, 9} => "RDPLPDPRRP" 
