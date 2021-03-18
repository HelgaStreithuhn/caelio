''' Hilfe im Konsolen-Dialog '''

import random

# Rekursive Funktion zur Berechnung einer Fakultät
def fak(n: int) -> int:
    if n > 1:
        return n * fak(n - 1)
    return 1

# Dialog
print('Ich bin eine interaktive Hilfe zu Caelio.')
print('Eine leere Eingabe beendet das Programm.')
eingabe: str = input('Was ist Ihr Anliegen?\n> ')
while eingabe:
  print('Ich bin primär zur Statistikschönung gebaut.')
  n: int = random.randint(2, 10)
  print('Die Fakultät von {} ist {}.'.format(n, fak(n)))
  eingabe = input('Was ist Ihr Anliegen?\n> ')
print('Ich hoffe, ich konnte Ihnen helfen.')
