#P
orgAmount = float(input("How much is in the account at the start? "))

#r
intrestRate = float(input("How much is the annual interest rate? "))/100

#n
amountDone = int(input("How many times per year is interest componded? "))

#t
years = float(input("How many years is it? "))

interest = orgAmount*(1+(intrestRate/amountDone))**(amountDone*years)
print("$", format(interest, '8.2f'), sep="")
