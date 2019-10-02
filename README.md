# PyBot
This project is for a simple Arduino/Raspberry bot. 
# Description
The Android app (SendAudio) will read English language audio input, then it will convert that to text. The text will then be sent through sockets to a Python server. This server will analyze the text, and if it's within static syntax, it will then send an instruction (enumerate from 0 to 4) which will then convert to bytes and send to Arduino through Serial Communication. The Arduino, expandable to Raspberry Pi, will then evaluate the byte instruction and do it. 
# Future improvements
I want to use a Raspberry Pi for text to audio response from Python Bot using Tensorflow and add a wireless adapter for mobile wireless communication.
