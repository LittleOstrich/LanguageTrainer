'''
Created on 7 Oct 2017

@author: Benjamin
'''
import inspect
import os
import sys
import time

from gtts import gTTS

path_base = os.path.dirname(os.path.abspath(inspect.stack()[0][1]))
path = path_base + "\\temp.mp3"

def pronounce_default(s): 
    tts = gTTS(s, lang='en')
    tts.save(path)
#    os.startfile(path)

def pronounce(s, l):
    tts = gTTS(s, l)
    tts.save(path)
#    os.startfile(path)
    
def helloWorld(): 
   print("Python, Hello World!")

def void():
    {}

f = open("text.txt", "a+" , encoding="utf-8")
for s in sys.argv: 
    f.write(s + "")
    f.write("\r")
f.close()

if   len(sys.argv) == 1 : void()
elif len(sys.argv) == 2 : pronounce_default(sys.argv[1])
elif len(sys.argv) == 3 : pronounce(sys.argv[1], sys.argv[2])
else                    : void()

# print("Hello World")
