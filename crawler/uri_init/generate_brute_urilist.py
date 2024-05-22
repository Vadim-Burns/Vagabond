#!python3
import threading

ALPH = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9']
MAX_SIZE = 3

def generate_word(created: list, remains: int) -> list:
	"""
	Генерация последовательностей символов заданной длины
	@param created - список уже сгенерированных последовательностей
	@param remains - сколько символов еще нужно добавить
	"""
	if remains == 0:
		return created

	if len(created) == 0:
		return generate_word(ALPH.copy(), remains - 1)
	
	updated = []
	for cur in created:
		for ch in ALPH:
			updated.append(cur + ch)

	return generate_word(updated, remains - 1)

def generate_and_print(size: int):
	generated = generate_word([], size)
	for gen in generated:
		print(gen)

generate_and_print(2)

threads = []
for size in range(1, MAX_SIZE + 1):
	thread = threading.Thread(target=generate_and_print, args=[size,])	
	thread.start()
	threads.append(thread)
		
for thread in threads:
	thread.join()
