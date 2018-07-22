class Solution:
    def maxIncreaseKeepingSkyline(self, grid):
        r_max = [0 for x in range(len(grid[0]))]
        c_max = [0 for x in range(len(grid))]
        total_before = 0
        total_after = 0

        for i in range(len(grid)):
            for j in range(len(grid[0])):
                c_max[i] = max(c_max[i], grid[i][j])
                r_max[i] = max(r_max[i], grid[j][i])
                total_before += grid[i][j]


        for i in range(len(grid)):
            for j in range(len(grid[0])):
                total_after += min(r_max[j], c_max[i])

        return total_after - total_before


sol = Solution()
print(sol.maxIncreaseKeepingSkyline([[59, 88, 44], [3, 18, 38], [21, 26, 51]]))
