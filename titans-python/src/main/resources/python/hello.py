#!/usr/bin/python
# -*- coding: utf-8 -*-
import sys

print(len(sys.argv[2:]));

argv_len = len(sys.argv[2:])

print(argv_len);


list_2d=[[0 for i in range(11)] for i in range(1)]

for i in range(argv_len):
    list_2d[0][i]=float(sys.argv[i+2])

print(list_2d)