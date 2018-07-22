import math


def findMinNumOfFlagStone(m, n, a):
    return (math.ceil(m / a) * math.ceil(n / a))


m, n, a = map(int, input().split())
print(findMinNumOfFlagStone(m, n, a))
