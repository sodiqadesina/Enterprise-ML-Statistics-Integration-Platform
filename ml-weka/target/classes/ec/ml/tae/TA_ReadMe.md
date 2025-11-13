**Date**
20/6/2018

**Author**
Lubna Rahal


**Dataset**
tae.arff

**URL**
https://github.com/renatopp/arff-datasets/blob/master/classification/tae.arff


This dataset consists of Teaching Assistants Evaluation

The model evaluates TAs according to the following attributes:

1. Whether of not the TA is a native English speaker (binary)
     1=English speaker, 2=non-English speaker
2. Course instructor (categorical, 25 categories)
3. Course (categorical, 26 categories)
4. Summer or regular semester (binary) 1=Summer, 2=Regular
5. Class size (numerical)
6. Class attribute (categorical) 1=Low, 2=Medium, 3=High



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

**2** A Teacher Assistant evaluation based on following attributes:

1. Whether of not the TA is a native English speaker (binary)
     1=English speaker, 2=non-English speaker
2. Course instructor (categorical, 25 categories)
3. Course (categorical, 26 categories)
4. Summer or regular semester (binary) 1=Summer, 2=Regular
5. Class size (numerical)
6. Class attribute (categorical) **1=Low, 2=Medium, 3=High**

a sample instance file is **ta_instance.arff**
you can enter your TA attributes under the @data tag then use the file to predict TA evaluation.


**USAGE**

call Prediction service by executing this command:\\
java -jar Predict.jar <service> <model> <input_file> <output_file>

@@ arguments @@
1. prediction service: ta
ta: to predict TA evaluation

2. machine learning prediction model to use: {svm, knn, bayes}
3. path to instance file (sample files are car_predict.arff and tae_arff)
4. path to file where to save the result

**examples**
java -jar Predict.jar ta knn data1/tae_predict.arff data1/tae_result_predict.arff





