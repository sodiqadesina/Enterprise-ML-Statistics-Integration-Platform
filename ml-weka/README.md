# Weka ML Classifiers 
Author: HBF  
Date: 2022-07-04

## What's about?

Waikato Environment for Knowledge Analysis (Weka) is a suite of machine learning software written in Java, developed at the University of Waikato, New Zealand. It is free software licensed under the GNU General Public License. It can be used to develop ML components. 

This project presents examples of using Weka's machine learning/data mining classifier APIs in Java programs.  

1. ec.weka.data folder contains test programs for weka's arff data handling APIs. Weka uses the arff data format to store data. 

2. ec.weka.cls contains programming examples using weka's classifiers APIs,  including linear regression, native bayes, J48 (C4.5) and ANN. 

The linear regression test example uses the same house data as in lab0 ec-regression, but in arff format. 

3. ec.ml.tae contains examples of using Weka's classifiers (KNN, NB, and SVM) for teaching assistance evaluation data. Check Car_ReadMe.md for more details.

4. ec.ml.car contains examples of using Weka's classifiers (KNN, NB, and SVM) for teaching assistance evaluation data.  Check TA_ReadMe.md for more details.


## What to do?

1. Read WekaManual.pdf for the arff data file format. We will use arff as standard data format with Weka API programming.
 
2. Try to run the test programs in ec.weka.cls, ec.weka.data, and check the output results, i.e. Run As Java Application. 

3. For example, run LinearRegressionTest.java as Java Application. It first computes and generates the linear regression model and saves as file model/house_regression.bin, then it load the model from model file and does the prediction computing.

4. For programs in ec.ml.cars, first run create_bayes_model.java, which will generate a NB model and save it at model/car_bayes.bin, secondly ran use_bayes_model.java, which will read in model/car_bayes.bin and do testing/predicting for specified input data. Do the same to knn, and svm. 

5. For programs in ec.ml.tae, first run create_bayes_model.jar, which will generate a NB model and save it at model/tae_bayes.bin, secondly ran use_bayes_model.jar, which will read in model/tae_bayes.bin and do testing/predicting for specified input data. Do the same for knn and svm. 

