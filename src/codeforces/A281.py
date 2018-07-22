def capitalize(input):
    first_char = input[0]

    if 'a' <= first_char <= 'z':
        first_char = str(chr(ord(first_char) - 32))

    return first_char + input[1:]

print(capitalize(input()))
