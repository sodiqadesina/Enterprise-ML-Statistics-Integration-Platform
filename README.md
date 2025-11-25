# 🧠 Enterprise ML–Statistics Integration Platform


**Tech Stack:** Java EE (EJB, JPA, JMS, JAX-WS, JAX-RS) | Weka API | WildFly 18 | MySQL | Maven  

---

## 📘 Overview

The Enterprise ML–Statistics Integration Platform is a full Java EE system that brings together enterprise computing, statistical analysis, and machine learning into a unified, production-style environment. Its purpose is to demonstrate how real organizations build, deploy, and operationalize predictive models and statistical services across multiple interconnected components.

The platform begins by exposing core statistical operations such as min, max, mean, count, and standard deviation through both REST and SOAP web services. These services are backed by Enterprise JavaBeans that encapsulate the business logic, allowing external clients, browsers, and other applications to request analytics through stable interfaces. This mirrors how enterprise systems offer standardized and reusable data services internally and externally.

On the machine learning side, the platform uses Weka’s linear regression capabilities to train predictive models from structured datasets. The model is generated programmatically, evaluated for accuracy, and exported in both binary and JSON formats. This dual representation ensures that the model can be inspected by humans while still being efficiently loaded by software. The trained model is then stored inside a MySQL database as a persistent asset, enabling long-term reuse, versioning, and integration with other enterprise components.

Once stored, the model becomes part of the prediction workflow. A dedicated stateless session bean loads the model from the database, reconstructs it in memory, and performs predictions based on incoming data. A web interface allows users to enter feature values and receive real-time model predictions, demonstrating a complete end-to-end flow from user input to machine-generated output. This closely reflects how predictive services are embedded into enterprise applications such as pricing engines, risk calculators, and recommendation systems.

The combination of EJBs, JPA persistence, REST and SOAP endpoints, servlets, and Weka-based machine learning demonstrates how statistical and predictive analytics can be operationalized inside a modular WildFly deployment. The system shows how data, models, and services can work together reliably, consistently, and at scale, which is the core goal of modern enterprise analytics platforms.

---

## 🏗️ Project Structure

```bash
Enterprise-ML–Statistics-Integration-Platform/
├── images/ # Documentation screenshots
├── ml-weka/ # Stand-alone ML module using Weka API
│ ├── data/ # Training & test ARFF datasets
│ ├── model/ # Serialized .bin and .json model files
│ └── src/main/java/ec/weka/ # Core Weka training & evaluation logic
│
├── stats-app/ # Multi-module enterprise application
│ ├── stats-ejb/ # Business logic, DAOs, ML–EJB integration
│ ├── stats-web/ # Servlets, SOAP endpoints, web pages
│ ├── stats-ws/ # SOAP Web-Service definition (WSDL)
│ └── stats-rs/ # RESTful JSON endpoints
│
└── stats-client/ # CLI clients for DB ops, SOAP, ML, and REST tests
```

Each module is independently deployable and communicates through standardized enterprise interfaces.

---

## ⚙️ Key Components

| Layer | Purpose |
|--------|----------|
| **EJB (stats-ejb)** | Hosts business logic, database persistence, and ML model invocation. |
| **SOAP (stats-ws)** | Exposes statistical computations over WSDL. |
| **REST (stats-rs)** | Provides JSON endpoints for lightweight service consumption. |
| **ML (ml-weka)** | Handles dataset parsing, model training, evaluation, and serialization using Weka. |
| **Client (stats-client)** | Runs model generation, testing, and predictions externally via CLI. |

---

## 🧩 Integration Workflow

1. **Train & Serialize ML Models** using Weka API.  
2. **Persist Models** into the enterprise database (`ecmodel` table) through JPA DAOs.  
3. **Expose Predictions** via EJBs, SOAP, and REST services.  
4. **Consume Services** through web dashboards and command-line clients.

---

## 🤖 Machine Learning Pipeline

###  Model Training & Evaluation (Weka API)

![Linear Regression Generation](images/lr-model-genetation-by-weka-api.PNG)

**Explanation:**  

The platform begins with automatic model training using the Weka Java API:

  - The system loads the dataset (house.arff)
  
  - Builds a Linear Regression model (weka_lr)
  
  - Evaluates the model (Correlation ≈ 0.897, RMSE ≈ 3276)

  - Saves two artifacts:

      - A binary model (weka_regression.bin) for fast EJB deserialization
      
      - A JSON descriptor for transparency and reproducibility

This proves the ML workflow is working entirely in Java with no manual steps, ensuring the model can be deployed programmatically.
---

###  Model Persistence

![Model in Database](images/lr-model-in-db.PNG)

**Explanation:**  

After training, the model is serialized and stored in MySQL using the StatsDBInsert client:

  - Stored as a BLOB inside the ecmodel table

  - Metadata includes: model name, class, timestamp

  - Enables persistent model management like a real production ML system

This ensures the application server can load ML models on demand, even after restarts, deployments, or scaling.

---

###  Enterprise Java Integration (EJB Prediction Engine)

![LR Session Bean](images/lr-session-bean-component.PNG)

**Explanation:**  

The LRStateless EJB forms the core prediction engine:

  - Retrieves the serialized Weka model from the database

  - Reconstructs the model in memory

  - Accepts feature vectors from the UI or service layers

  - Calls classifyInstance() to compute the prediction

  - Returns the exact same result as the offline Weka test run (e.g., Predicted Price ≈ 225,173 CAD)

This proves the ML model executes correctly inside the application server, not just locally.

---

###  Browser-Based Prediction UI (Servlet Layer)

![LR Web Component](images/lr-web-component.PNG)

**Explanation:** 

The web interface provides a simple prediction form:

  - Users enter the model name (e.g., weka_lr)

  - Provide comma-separated feature values

  - The servlet performs a JNDI lookup for the EJB

  - The EJB returns the computed prediction

  - The result is displayed instantly in the browser

This demonstrates the full flow:
Servlet → EJB → Weka Model → Response, showing end-to-end integration for real-time predictions.
---

## ☁️ Web Service Integration Layer

###  SOAP Web Service (WSDL + Operations)

![SOAP WS](images/soap-ws.PNG)
![SOAP Client](images/soap-ws-client.PNG)

**Explanation:**  

The platform exposes statistical analytics through a fully compliant SOAP API:

  - Implemented via StatsWSImpl
  
  - Auto-generates a WSDL describing operations:
  
      - getCount
      
      - getMin
      
      - getMax
      
      - getMean
      
      - getSTD
  
  - A Java SOAP client invokes these operations
  
  - Browser output confirms successful responses:
  
      - Count: 1
      
      - Mean: 10
      
      - STD: 0

This demonstrates compatibility with traditional enterprise systems that still rely on SOAP/XML integrations.

---

###  RESTful Analytics API (JAX-RS)

![RESTful Service](images/restful-web-service.PNG)

**Explanation:**  

A parallel REST interface exposes the same statistical capabilities:

`StatsRS` exposes `/rest/max`, `/rest/min`, `/rest/mean`, and `/rest/std` endpoints that return JSON responses such as:  

```json
{"max":"10.0"}
{"mean":"10.0"}
```

This provides lightweight, stateless access for modern systems, frontend apps, CI pipelines, and mobile clients.

### 🔬 SOAP–REST–ML Convergence

The platform merges descriptive statistics and predictive analytics into one cohesive system:

SOAP/REST → Provide standardized interfaces for remote access.

EJB Layer → Bridges data persistence and ML model execution.

ML-Weka Module → Enables predictive intelligence.

Together they transform a traditional enterprise statistics engine into a service-oriented machine-learning platform.

# 🗄️ Persistence Configuration (JPA)
```bash
<persistence-unit name="primary" transaction-type="JTA">
  <jta-data-source>java:/MySqlDS</jta-data-source>
  <class>ec.stats.model.User</class>
  <class>ec.stats.model.Model</class>
  <properties>
    <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
    <property name="hibernate.hbm2ddl.auto" value="update"/>
    <property name="hibernate.show_sql" value="true"/>
  </properties>
</persistence-unit>
```


⚠️ Important Setup Note

- 1 Keep the Project Directory Structure Intact
  This platform relies on relative paths across modules (ml-weka, stats-app, stats-client).
  Do not rename or move the project root folder — keep it in the same structure:
  C:\enterprise\workspace\ec-git-projects\a3.

  If you move it, Maven builds and WildFly deployments may fail to locate the Weka models, ARFF files, or generated binaries.

- 2 Set System Environment Variables for Required Tools
  To ensure Weka, Java, and WildFly commands work globally:

  Add their bin directories to your Windows System PATH.

  Example setup:
  
  JAVA_HOME = C:\Program Files\Java\jdk-17
  WEKA_HOME = C:\Program Files\Weka-3-8
  WILDFLY_HOME = C:\wildfly-18.0.1.Final
  MAVEN_HOME = C:\apache-maven-3.9.9
  
  
  Then append these to your system Path:
  
  %JAVA_HOME%\bin;
  %WEKA_HOME%\;
  %WILDFLY_HOME%\bin;
  %MAVEN_HOME%\bin;
  
  
  Restart your terminal afterward to apply changes.

- 3 Verify Installations
  Run these to confirm everything is accessible:
  
  java -version
  mvn -v
  weka --help
  %WILDFLY_HOME%\bin\standalone.bat --version

----

### 🧪 Build & Run Instructions

1 Build all modules
mvn clean package

2 Deploy EAR to WildFly
cd stats-app
mvn wildfly:deploy

3 Access Services
http://localhost:8080/stats-web/          # Web UI
http://localhost:8080/stats-ws/StatsWS?wsdl  # SOAP WSDL
http://localhost:8080/stats-rs/rest/mean     # REST endpoint

4 Run ML Clients
cd ../stats-client
java -cp target/stats-client.jar ec.weka.ModelFileGenerate
java -cp target/stats-client.jar ec.weka.ModelDBPredict

5 MySQL setup (once)

```bash
1 Create DB + user

  CREATE DATABASE ec_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
  CREATE USER 'ec_user'@'%' IDENTIFIED BY 'StrongPassword!123';
  GRANT ALL PRIVILEGES ON ec_platform.* TO 'ec_user'@'%';
  FLUSH PRIVILEGES;


2 (Optional) Verify connectivity from your machine:

mysql -h <MYSQL_HOST> -P 3306 -u ec_user -p ec_platform -e "SELECT 1;"

```

6 WildFly: add MySQL driver + datasource

- Do this inside your WildFly install dir. Replace X.Y.Z with your MySQL Connector/J version (e.g., 8.3.0) and WILDFLY_HOME with your path.

7 Install MySQL JDBC driver as a module

```bash
mkdir -p %WILDFLY_HOME%\modules\system\layers\base\com\mysql\main
copy mysql-connector-j-X.Y.Z.jar %WILDFLY_HOME%\modules\system\layers\base\com\mysql\main\
```

8 Create %WILDFLY_HOME%\modules\system\layers\base\com\mysql\main\module.xml:

<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.9" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-j-X.Y.Z.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>


9 Start WildFly (standalone)

```bash
%WILDFLY_HOME%\bin\standalone.bat
```

10 Add the JDBC driver & datasource via CLI (new terminal):

```bash
%WILDFLY_HOME%\bin\jboss-cli.bat --connect
```
Register the driver
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-class-name=com.mysql.cj.jdbc.Driver)

Add the datasource expected by persistence.xml
data-source add \
  --name=MySqlDS \
  --jndi-name=java:/MySqlDS \
  --driver-name=mysql \
  --connection-url=jdbc:mysql://<MYSQL_HOST>:3306/ec_platform?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC \
  --user-name=ec_user \
  --password=StrongPassword!123 \
  --min-pool-size=5 \
  --max-pool-size=20 \
  --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker \
  --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter

Test the datasource
/subsystem=datasources/data-source=MySqlDS:test-connection-in-pool

Confirm JPA picks it up

- The persistence.xml uses:

```bash
<jta-data-source>java:/MySqlDS</jta-data-source>
<property name="hibernate.hbm2ddl.auto" value="update"/>
```

- So on first deploy, Hibernate will auto-create/update tables (ecuser, ecmodel, etc.) in ec_platform. Check logs for Hibernate: create table… lines, or verify in MySQL:

  SHOW TABLES FROM ec_platform;
  SELECT * FROM ecmodel LIMIT 5;

Secure secrets (recommended)

- Prefer env vars + CLI expressions rather than plain passwords:

  /subsystem=datasources/data-source=MySqlDS:write-attribute(name=password,value="${VAULT_DB_PASSWORD:StrongPassword!123}")


Or use WildFly Elytron credential store for production.


### 🧾 Validation Summary

| Component | Technology             | Output Verified                    |
| --------- | ---------------------- | ---------------------------------- |
| ML Model  | Weka API               | Correlation = 0.897  RMSE = 3276   |
| DB Layer  | JPA / MySQL            | `ecmodel` contains `weka_lr` entry |
| EJB       | Stateless Session Bean | Prediction = 225173 CAD            |
| SOAP WS   | JAX-WS                 | XML WSDL + valid service responses |
| REST API  | JAX-RS                 | JSON stats `{ "mean": "10.0" }`    |
| Web UI    | HTML + Servlet         | Live prediction workflow           |

### 🚀 Future Enhancements

- Add REST endpoints for uploading and testing multiple ML models.

- Implement role-based access control via Elytron.

- Introduce batch prediction and model versioning.

- Containerize with Docker Compose (WildFly + MySQL + Weka).

### 📚 Summary

This project demonstrates the unification of enterprise computing and machine learning:
statistical services are extended with predictive power through Weka, accessible via SOAP, REST, and EJB layers — all under a clean, modular Jakarta EE architecture.
