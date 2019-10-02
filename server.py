import socket
import serial
import sys

#
# developed by DiracSpace
#
# please add your names or nicknames 
# contributions: 
#
# Python code for receiving audio converted to text from android app
# over TCP port 3456 and decoding the bytes to string format
# to compare that string to default syntax and sending byte
# instructions to Arduino over serial port

# notes: 
#	- in windows it runs fine
#	- in linux you need superuser "sudo python server.py"
#	- can receive any type of text over that TCP port but it has
# 	to contain the syntax for Arduino instruction to work

def arduino():
    # default syntax 'turn on'
    if data == 'turn on':
        var1 = "1"
        ser.write(var1.encode())
    else:
        var1 = "0"
        ser.write(var1.encode())

# my personal IP address
# the port can stay as is
# feel free to change for personal IP address but remember that IP address in 
# android app also has to change 

host = '192.168.0.10'
port = 3456

# this is for Windows
# ser = serial.Serial('COM3', 9600)

# this is for Linux
ser = serial.Serial('/dev/ttyACM0', 9600)

# creation of socket for sending or receiving data stream
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

print ('Socket created')

try:
    s.bind((host, port))
except socket.error as e:
    print ('bind failed, error code: '+str(e[0])+ ', Message: '+e[1])
    sys.exit()

print ('socket bind success')

s.listen(10)

print ('socket is now listening')

while 1:
    conn, addr = s.accept()
    print ('connected with '+addr[0]+':'+str(addr[1]))
    buffer = conn.recv(64)
    data = buffer.decode("utf-8")
    print (data)
    arduino()
s.close()
