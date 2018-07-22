class Solution:
    def numJuwelsInStones(self, j, s):
        """
        :param j: perls
        :param s: stones
        :return pc: number of perls in the stones
        """

        perl_set = set(j)
        perl_count = 0

        for stone in s:
            if perl_set.__contains__(stone):
                perl_count = perl_count + 1

        return perl_count

    def numJewelsInStones(self, j, s):
        return sum(map(j.count, s))

# x = Solution()
# print(x.numJuwelsInStone('aA', 'aAAbbbb'))
