def max_min_avg(* num):
    "return a tuple containing the maximnum, minimum and avrage of the given numbrs"
    return max(num), min(num), sum(num)/len(num)

print max_min_avg(2,3,4)
