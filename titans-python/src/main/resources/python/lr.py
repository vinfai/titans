#!/usr/bin/python
# -*- coding: UTF-8 -*-

import sys
from numpy import *
#from sklearn.datasets import load_iris     # import datasets
#
## load the dataset: iris
#iris = load_iris()
#samples = iris.data
##print samples
#target = iris.target
#
# import the LogisticRegression
from sklearn.linear_model import LogisticRegression

#LR = LogisticRegression(C=0.001, class_weight='balanced', dual=False,
#                       fit_intercept=True, intercept_scaling=1, max_iter=1000,
#                       multi_class='ovr', n_jobs=1, penalty='12', random_state=None,
#                       solver='liblinear', tol=0.0001, verbose=0, warm_start=False)

#x = LR.predict([5, 3, 5, 2.5])  # 测试数据，分类返回标记
#print x

print(sys.argv)
model_path = sys.argv[1];
print(model_path)
from sklearn.externals import joblib
LR= joblib.load(model_path)

argv_len = len(sys.argv[2:])
# import pandas as pd
#data = [[0.115680,0.012392,-0.043846,0.008704,-0.176962,-0.486509,0.211209,0.186518,-0.037117,-0.214790,0.063028]]
data=[[0 for i in range(11)] for i in range(1)]

for i in range(argv_len):
    data[0][i]=float(sys.argv[i+2])

print(data)
#p=20/log(15/30)
#q=600-p*log(15)
p = 28.8
q = 633
p_default=LR.predict_proba(data)[:,1]  #(模型判断这个用户违约的概率)
p_nodefalut=LR.predict_proba(data)[:,0]  #(模型判断这个用户不违约的概率)
odds=p_nodefalut/p_default
score =q+p*log(odds)
print(score)

