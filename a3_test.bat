rem batch test
echo A3 batch test
echo username:%USERNAME%
echo CD

start "mysql" cmd /k mysql_start
sleep 5
start "wildfly" cmd /k standalone -c standalone-full.xml
sleep 10

cd stats-app
call mvn clean install -q
call mvn wildfly:undeploy -q
call mvn wildfly:deploy  -q

cd ../stats-client
call mvn clean package -q
call java -cp target/stats-client.jar ec.stats.ws.StatsWSClient

call curl http://localhost:8080/stats-ws/StatsWSImpl?wsdl > null
call curl http://localhost:8080/stats-web/statsws
call curl http://localhost:8080/stats-rs/rest/count
call curl http://localhost:8080/stats-rs/rest/max
call curl http://localhost:8080/stats-rs/rest/mean
call curl http://localhost:8080/stats-rs/rest/stats


call java -cp target/stats-client.jar ec.weka.ModelFileGenerate -datafile data/house.arff -modelfile C:/enterprise/tmp/model/weka_regression.bin

call java -cp target/stats-client.jar ec.weka.ModelFileGenerate -datafile data/house.arff -jsonmodelfile C:/enterprise/tmp/model/weka_regression.json

call java -cp target/stats-client.jar ec.weka.ModelFileTest -modelfile C:/enterprise/tmp/model/weka_regression.bin -datafile data/house.arff  -testfile data/house_test.arff -outfile test_result.txt

call java -cp target/stats-client.jar ec.weka.ModelFilePredict -modelfile C:/enterprise/tmp/model/weka_regression.bin -predictfile data/house_predict.arff -outfile result.txt

call java -cp target/stats-client.jar ec.weka.ModelFilePredict -jsonmodelfile C:/enterprise/tmp/model/weka_regression.json -predictfile data/house_predict.arff -outfile result.txt

call java -cp target/stats-client.jar ec.stats.db.StatsDBDelete -table ecmodel -name weka-lr

call java -cp target/stats-client.jar ec.stats.db.StatsDBInsert -table ecmodel -name weka-lr -modelfile C:/enterprise/tmp/model/weka_regression.bin

call java -cp target/stats-client.jar ec.weka.ModelDBPredict -table ecmodel -name "weka-lr" -query "3198,9669,5,1,1,0"

call curl -X GET http://localhost:8080/stats-web/predict -d "name=lr&query=3198,9669,5,1,1,0"


cd ../stats-app
call mvn wildfly:undeploy -q
call mvn clean -q

cd ../stats-client
call mvn clean  -q
cd ..


taskkill /FI "WindowTitle eq mysql*"  /T /F >null
taskkill /FI "WindowTitle eq wildfly*"  /T /F >null

echo END
