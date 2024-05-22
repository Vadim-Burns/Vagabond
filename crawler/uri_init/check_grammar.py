#!python3
# Scipt only for checking how to spell correctly multiple telegraph
# same title pages on same date
import requests
import threading

def check_url(url):
	resp = requests.head(url)
	if resp.status_code == 200:
		print(url + " OK")
		for copy in range(1, 10):
			nurl = url + '-' + str(copy)
			print(nurl)
				
			resp = requests.head(nurl)
			if resp.status_code == 200:
				print("Found")
				print(nurl)
				quit()


WORKERS = 10
uri = "https://telegra.ph/a-"
threads = []

for month in range(1, 13):
	for day in range (1, 32):
		if month // 10 == 0:
			sm = '0' + str(month)
		else:
			sm = str(month)

		if day // 10 == 0:
			sd = '0' + str(day)
		else:
			sd = str(day)

		url = uri + sm + "-" + sd
		print(url)

		if len(threads) == WORKERS:
			for thread in threads:
				thread.join()
		thread = threading.Thread(target = check_url, args=[url,])
		thread.start()
		threads.append(thread)

for thread in threads:
	thread.join()
		
