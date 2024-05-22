#!python3
from loguru import logger

INIT_FILES = ['wordlist.txt', 'wordlist.10000', 'brute_3_urilist.txt', 'first-names.txt', 'russian_names_urilist.txt']
result = set()

def load_file(path: str):
	logger.info(f"Start loading file {path}")
	res = set()
	with open(path, 'r') as file:
		for line in file.readlines():
			res.add(line.strip().lower())

	logger.info(f"Loaded {len(res)} uri-s")
	return res
	


for file in INIT_FILES:
	result.update(load_file(file))

for line in result:
	print(line)
