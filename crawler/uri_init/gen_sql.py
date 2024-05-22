#!python3
"""
Generate sql insert from uri wordlist
"""

ALPH = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-']
wordlist = "result_urilist.txt"

def clear_word(word):
	new_word = ""
	
	for ch in word:
		if ch in ALPH:
			new_word = new_word + ch
		if ch == ' ':
			new_word = new_word + '-'

	return new_word

with open(wordlist, 'r') as file:
	for line in file.readlines():
		uri = clear_word(line.strip().lower())
		if len(uri) == 0:
			continue
		
		#print(uri)
		print(f"('{uri}', 'custom'),")
	
