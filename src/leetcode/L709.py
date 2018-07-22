class Solution:
    def toLowerCase(self, str):
        output = ''

        for ch in str:
            if ch >= 'A' and ch <= 'Z':
                output += chr(ord(ch) + 32)
            else:
                output += ch

        return output

#
# solution = Solution()
# print(solution.toLowerCase('AaA123Ba'))
