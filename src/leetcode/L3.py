class Solution:
    def lengthOfLongestSubstring(self, s):
        i, j = 0, 0
        n = len(s)
        window = set()
        maxLen = 0

        while i < n and j < n:
            if not window.__contains__(s[j]):
                window.add(s[j])
                maxLen = max(maxLen, j - i + 1)
                j += 1
            else:
                window.remove(s[i])
                i += 1

        return maxLen


sol = Solution()
print(sol.lengthOfLongestSubstring("abcdbcxyzbb"))
