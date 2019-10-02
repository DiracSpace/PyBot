from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer
from gtts import gTTS
import sys
import os

bot = ChatBot('Fred The Hacker')

trainer = ChatterBotCorpusTrainer(bot)

trainer.train("chatterbot.corpus.english")

while True:
    try:
        user = input('Say something: ')

        if user == "exit":
            print ('Exiting ..')
            sys.exit()
        else:
            response = bot.get_response(user)
            print ('Fred The Hacker says: ',response)
            tts = gTTS(text = str(response), lang = 'en')
            tts.save('response.mp3')
            os.system('mpg321 response.mp3 -quiet')

    except(KeyboardInterrupt, EOFError, SystemExit):
        print ('There is something wrong')
