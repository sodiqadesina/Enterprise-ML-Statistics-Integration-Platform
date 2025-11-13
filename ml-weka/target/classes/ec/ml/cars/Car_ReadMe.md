**Date**
20/6/2018

**Author**
Lubna Rahal

**Dataset**
car.arff


**URL**
https://github.com/renatopp/arff-datasets/blob/master/classification/car.arff


Car Evaluation Database for a simple hierarchical decision model. 
The model evaluates cars according to the following concept structure:

CAR                      car acceptability
. PRICE                  overall price
. . buying               buying price
. . maint                price of the maintenance
. TECH                   technical characteristics
. . COMFORT              comfort
. . . doors              number of doors
. . . persons            capacity in terms of persons to carry
. . . lug_boot           the size of luggage boot
. . safety               estimated safety of the car


java files:
------------
<<Note: to work with weka library, you should add weka.jar to project build path.>>

1- create_svm_model.java:
train an SVM classifier on the car dataset

2- use_svm_model.java:
load the saved model and use it for prediction on a test data


3- create_knn_model.java:
train an KNN classifier on the car dataset

4- use_knn_model.java:
load the saved model and use it for prediction on a test data


5- create_bayes_model.java:
train an Naive Bayes classifier on the car dataset

6- use_bayes_model.java:
load the saved model and use it for prediction on a test data


Prediction
------------

This guide will show you how to use Predict.jar that's deployed as a Docker service.
Predict.jar is used to predict:

**1** A car acceptability based on some data regarding the car situation:

CAR                      car acceptability
. PRICE                  overall price 
. . buying               buying price **{vhigh,high,med,low}**
. . maint                price of the maintenance **{vhigh,high,med,low}**
. TECH                   technical characteristics
. . COMFORT              comfort
. . . doors              number of doors  **{2,3,4,5more}**
. . . persons            capacity in terms of persons to carry  **{2,4,more}**
. . . lug_boot           the size of luggage boot  **{small,med,big}**
. . safety               estimated safety of the car  **{low,med,high}**


the result class should be one of the following:  **{unacc,acc,good,vgood}**


a sample instance file is **car_instance.arff**
you can enter your car attributes under the @data tag then use the file to predict car class.


**USAGE**

examples

java -jar Predict.jar cars svm data1/tae_predict.arff data1/tae_result_predict.arff


