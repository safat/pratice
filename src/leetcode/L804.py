class Solution:
    def morse_by_char(self, ch):
        morse = [".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
                 "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."]

        return morse[ord(ch) - ord('a')]

    def uniqueMorseRepresentations(self, words):
        morse_set = set()

        for word in words:
            morses = ''

            for ch in word:
                morses += self.morse_by_char(ch)

            morse_set.add(morses)

        return len(morse_set)
