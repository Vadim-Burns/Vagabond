#!python3
"""
Скрипт для транслитерации имен
"""
import transliterate

def trans(word):
	try:
		return transliterate.translit(word, reversed=True)
	except Exception:
		return f"ERROR: {word}"

FILES = ['ru-pnames-list/lists/female_names_rus.txt', 'ru-pnames-list/lists/male_names_rus.txt', 'ru-pnames-list/lists/male_surnames_rus.txt'] 

for path in FILES:
	with open(path, 'r') as file:
		for line in file.readlines():
			print(trans(line).strip().lower())
