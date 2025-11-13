**Date**
22/6/2018

**Author**
Lubna Rahal

**Title**
Predcit.jar Docker service.

Predict.jar is the exported jar file of Predict class in project ML_Models.
This guide shows how to use Predict.jar that's deployed as a Docker service.


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

1. if docker image is not built yet, build the docker image by running following command in the directory that contains 
predict.df file, Predict.jar file and data folder:


docker build -f predict.df -t prediction_service .


2. run docker container:

docker run -i --rm --name predict -h predict -v "$PWD"/new_instances/:/prediction/ prediction_service

**<by mounting your current directory to container data file you will be able to call 
Predict.jar container's serviice on a local file in this directory, so create a new_instances 
directory inside the directory you run this command from.>

**<new_instances directory is mounted to /prediction directory inside the container.
so when you you run the following command to do predictions, you will use the path /prediction>

**<in new_instanes directory, there are two test examples: ta_instance.arff and car_instance.arff.
you can change your instance data inside those files>

3. call Prediction service by executing this command:
get sure that your new instance arff file is in new_instances directory, and then run the following command:

docker exec -it predict sh -c "/usr/bin/java -jar Predict.jar <service> <model> <input_file> <output_file> "

**examples**
docker exec -it predict sh -c "/usr/bin/java -jar Predict.jar cars knn prediction/car_instance.arff prediction/car_result_predict.arff"

docker exec -it predict sh -c "/usr/bin/java -jar Predict.jar ta svm prediction/ta_instance.arff prediction/ta_result_predict.arff"

@@ arguments @@
1. prediction service: {ta, cars}
ta: to predict TA evaluation
cars: to predict car class

2. machine learning prediction model to use: {svm, knn, bayes}
3. path to instance file (sample files are car_predict.arff and tae_arff)
4. path to file where to save the result


**to enter container shell execute following command** docker exec -it predict sh
inside the container you will find:
1. Predict.jar: the service jar file
2. data folder: contains datasets and model files
3. prediction folder: the folder mounted to new_instances local directory



