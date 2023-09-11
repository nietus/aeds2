def is_palindrome(s):
    s = s.lower()
    for i in range(0, int(len(s)/2)):
        if s[i] != s[len(s) - i - 1]:
            return False
    return True

s = input()
while s != "FIM":
    if is_palindrome(s):
        print("SIM")
    else:
        print("NAO")
    s = input()