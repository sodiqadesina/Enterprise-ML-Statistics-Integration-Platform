# 🧠 Enterprise ML–Statistics Integration Platform


**Tech Stack:** Java EE (EJB, JPA, JMS, JAX-WS, JAX-RS) | Weka API | WildFly 18 | MySQL | Maven  

---

## 📘 Overview

This is a comprehensive Java EE platform that unifies enterprise computing with machine learning. It integrates EJBs, JMS, SOAP, and REST services with Weka-based regression models for predictive analytics. Deployed on WildFly with JPA persistence and MySQL, it delivers end-to-end intelligent data processing and web service interaction.

This project integrates **machine-learning analytics** with a **distributed enterprise statistics platform**.  

It demonstrates how Weka-based regression models can be trained, stored, and served through standard enterprise middleware — including **EJBs**, **SOAP/REST web services**, and **JPA persistence** — within a modular WildFly deployment.

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

###  Model Generation

![Linear Regression Generation](images/lr-model-genetation-by-weka-api.PNG)

**Explanation:**  
The ML module trains a **Linear Regression** model (`weka_lr`) on the `house.arff` dataset.  
Results (correlation = 0.897, RMSE ≈ 3276) confirm successful training and evaluation.  
Both binary (`.bin`) and JSON versions are produced for portability.

---

###  Model Persistence

![Model in Database](images/lr-model-in-db.PNG)

**Explanation:**  
The serialized model is inserted as a BLOB in MySQL’s `ecmodel` table through the DAO layer (`StatsDBInsert`).  
This establishes persistent ML asset storage accessible to all enterprise modules.

---

###  EJB Integration

![LR Session Bean](images/lr-session-bean-component.PNG)

**Explanation:**  
`LRStateless` EJB retrieves the stored model, reconstructs the Weka classifier, and performs predictions using `classifyInstance()`.  
Predicted price ≈ 225,173 CAD, matching the standalone ML result — verifying model consistency across layers.

---

###  Web Interface

![LR Web Component](images/lr-web-component.PNG)

**Explanation:**  
The servlet form (`index-lr.html`) invokes the `LRStateless` EJB through JNDI lookup.  
Users input feature values and receive real-time predictions in the browser.  
Confirms proper Servlet → EJB → Model → Response chain.

---

## ☁️ SOAP & REST Web Services

###  SOAP Service (WSDL)

![SOAP WS](images/soap-ws.PNG)
![SOAP Client](images/soap-ws-client.PNG)

**Explanation:**  
The `StatsWSImpl` class exposes SOAP endpoints (`getCount`, `getMean`, `getSTD`, etc.).  
The generated WSDL defines all operations and bindings.  
`StatsWSClient` successfully consumes the service, displaying correct statistical results (Count = 1, Mean = 10).  

---

###  RESTful Service (JAX-RS)

![RESTful Service](images/restful-web-service.PNG)

**Explanation:**  
`StatsRS` exposes `/rest/max`, `/rest/min`, `/rest/mean`, and `/rest/std` endpoints that return JSON responses such as:  
```json
{"max":"10.0"}
{"mean":"10.0"}
```

These endpoints provide quick, stateless access to statistics computed by StatsStatelessLocal EJBs.

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

Add REST endpoints for uploading and testing multiple ML models.

Implement role-based access control via Elytron.

Introduce batch prediction and model versioning.

Containerize with Docker Compose (WildFly + MySQL + Weka).

### 📚 Summary

This project demonstrates the unification of enterprise computing and machine learning:
statistical services are extended with predictive power through Weka, accessible via SOAP, REST, and EJB layers — all under a clean, modular Jakarta EE architecture.
