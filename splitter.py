file = open("novoSecurity_en_limpo.json")
output = open('tweets.txt', 'w')
for line in file:
	tweet = line.split("\",\"", 1)[0].split("\":\"", 1)[1]
	output.write(tweet)
	output.write("\n")
output.close()