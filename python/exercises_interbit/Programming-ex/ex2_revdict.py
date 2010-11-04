d = {
    "Israel":"Jerusalem",
    "France":"Paris",
    "Italy":"Rome",
    "Egypt":"Cairo",
    }

# Loop solution:
# ==============

rev_d = {}
for (k, v) in d.items():
    rev_d[v] = k
print rev_d

# Build from reverse items:
# =========================

# recall that zip(d.keys(), d.values()) == d.items()
rev_d = dict(zip(d.values(), d.keys()))
print rev_d
