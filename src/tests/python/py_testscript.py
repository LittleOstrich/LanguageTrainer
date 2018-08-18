'''
Created on 7 Oct 2017

@author: Benjamin
'''
import os
import sys
import time

from gtts import gTTS

path = "C:\\Users\\Benjamin\\Desktop\\LainProject\\JavaFX_Playground\\src\\tests\\python\\good.mp3"

# os.startfile(path)


def pronounce_default(s): 
    tts = gTTS(text=s, lang='en')
    tts.save(path)
    os.startfile(path)

def pronounce(s, l):
    tts = gTTS(text=s, lang=l)
    tts.save(path)
    os.startfile(path)
    
def helloWorld(): 
   print("Python, Hello World!")
    
# pronounce_default(sys.argv[1])
pronounce(sys.argv[1], "ja")
# pronounce(sys.argv[1], "el")

print(sys.argv[1])
