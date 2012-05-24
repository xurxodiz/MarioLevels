#!/usr/bin/env python

from __future__ import with_statement

import numpy as np

print "lol"

data = np.loadtxt('data/logprob.csv', delimiter=',', dtype=('S5, i4, S8'),)

x, y, z = map(None,*data) # transpose