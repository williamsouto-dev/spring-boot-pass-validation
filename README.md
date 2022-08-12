# Service Password Validation

<p>
    Service responsible for evaluating new registered passwords
</p>

Project Repository:

[My Github](https://github.com/williamsouto-dev)

<hr>

## About Project

<p>
    The project was based on the concept of clean architecture, with the aim of separating concerns into layers. Proposing a testable service, independent of external agents, independent of databases, frameworks, and above all, offering an easy and fast development.
</p>
<p>
   Isolation ensures that any changes to one layer do not affect another. And this is achieved through the dependency rule. The rule says that no source code should use implemented dependencies, but their abstractions. That is, the inner layers must not mention or know the components of the outer layers, in the same way that the outer layers do not know the components of the inner layers.
</p>

<p>The project is structured as follows:</p>
<pre>├── <font color="#E87056"><b>adapter</b></font>
│   ├── <font color="#0883FF"><b>in</b></font>
│   │   └── <font color="#0883FF"><b>controller</b></font>
│   │       └── <font color="#0883FF"><b>dto</b></font>
│   └── <font color="#0883FF"><b>out</b></font>
│       └── <font color="#0883FF"><b>metrics</b></font>
├── <font color="#E87056"><b>application</b></font>
│   └── <font color="#0883FF"><b>in</b></font>
├── <font color="#E87056"><b>commons</b></font>
│   ├── <font color="#0883FF"><b>enums</b></font>
│   └── <font color="#0883FF"><b>exception</b></font>
└── <font color="#E87056"><b>domain</b></font>
    └── <font color="#0883FF"><b>validation</b></font>
</pre>

Explanation:
- adapter: Set of external agents of the application, which somehow need to interact with the service, representing the adapter layer and is divided into two categories, the first dedicated to input flows, known as in, and the second to output flows. Example: database, 3rd party APIs, gateway, distributed streaming, file repository, etc.
- application: Collection of use cases, this folder will contain the classes responsible for the business rules, normally the core of the solution is found in this folder, representing the use cases layer. Examples: Registry Validation Service.
- domain: Folder that will store the pojos classes, responsible for transporting data from one layer to another, without causing coupling.
- commons: Set of classes common to all layers, usually bean configurations, components, enums, exceptions, etc. Example: Configuration of kafka listeners.

### Solution of the problem
<p>
    To document the API, the OpenAPI was used, which internally uses swagger. This is an easy and intuitive way to document existing endpoints.
</p>
<p>
    The password validation API was created in the adapter layer, responsible for receiving the data and passing it to the inner layer. Any external agent of this application must use an adpater to communicate with the internal components of the service.
</p>

<p>
    In the project, a controller called RegistrationController was created to illustrate an account registration api and mainly to simulate a password validation.
</p>

<p>
    There was the possibility of verifying the password at the beginning of the flow using the @Valid annotation in the request, but this validation was implemented at a more internal layer, to better explore the architecture and allow better handling in case of errors or invalid passwords. But nothing prevents this validation from being done in the controller(Adapter In).
</p>

<p>
    The application folder contains the class RegistrationService that will validate the passwords received by the API.
</p>

<p>
    Password validation was inserted in this layer to facilitate data manipulation and error handling, one of the advantages of this approach is the abstraction of logic within the service, making it possible to combine it with other validations and flows. Another great advantage was to complement the validation with a monitoring and generation of metrics, through the Prometheus tool.
</p>

<p>
    Two types of metrics are being generated, the total of password validation requests and also the total of validations by status (Valid - Not valid). This will give us insights into how the api is behaving, volume, processing time, etc.
</p>

<p>
    As a complement, Log4j is being used to record the relevant flow information and generate requisition tracking, so that in the future it will facilitate the work of analyzing problems in the production environment, searching for evidence and understanding flows that can be very confusing.
</p>

<p>
    In order not to create coupling between the layers, interface (abstractions) was used in the dependency injections, respecting the Dependency Inversion Principle. This will facilitate future maintenance and the addition of new features.
</p>

<p>
    Finally, the password is being validated in the Password class by a regex, combined with the spring @Pattern annotation, which makes it possible to apply a pattern check to any attribute of a class. This approach was used to achieve clean and objective validation without the need for a lot of if's and elses or checks in parts. If the password is within the expected standards, the value true is returned and in case of failure, the boolean value false will be returned.
Regardless of the scenario, a metric will be created, as mentioned earlier.
</p>

<hr>

## Guide to run service

### requirements

- Java 11 (sudo apt-get install openjdk-11-jdk)
- [Docker](https://docs.docker.com/engine/install/ubuntu/)
- Docker Compose (sudo apt install docker-compose)

<p>
    To run the project, your machine must have OpenJDK installed, because the service is coded in java.
    It will also be necessary to install docker, an open source platform that provides resources to create an environment, without having to worry about the infrastructure of a server.
</p>

<p>
    Docker compose is being used to run two auxiliary services, Prometheus and Grafana. These tools are being used for illustrative purposes only, but if you want to see the graphics, they will be available in a simple way to access them.
</p>

### Starting the service

<p> Run the sh file with the name of <b>start-service.sh</b> located at the root of the project </p>

Bash command line:

`sh start-service.sh`

Services url:

* [Service Password Validation - Swagger](http://localhost:8088/swagger-ui.html)
* [Prometheus](http://localhost:9090)
* [Grafana](http://localhost:3000)

<a href="https://i.postimg.cc/fysnzmtP/1-prometheus.png"><img src="https://i.postimg.cc/fysnzmtP/1-prometheus.png" width="200" height="120" alt="Grafana"/></a>
<a href="https://i.postimg.cc/qMyk7rLC/2-prometheus.png"><img src="https://i.postimg.cc/qMyk7rLC/2-prometheus.png" width="200" height="120" alt="Grafana"/></a>
<a href="https://i.postimg.cc/4xGX70L2/3-prometheus.png"><img src="https://i.postimg.cc/4xGX70L2/3-prometheus.png" width="200" height="120" alt="Grafana"/></a>

### Optional - View metrics in grafana

<p>Prometheus monitors the spring boot application. Grafana views Prometheus as a data source.</p>

<p>Open the <a href="http://localhost:3000/">Grafana</a> link and on the login screen, enter the <i>admin-admin credentials</i></p>

<p>
    Steps

- Configure data sources: Click Configuration -> Data Sources
  - Click add data source -> Prometheus
  - Fill in the fields with the following parameters and click Save & test:


| Field  |         Value          |
|:-------|:----------------------:|
| URL    | http://172.17.0.1:9090 |
| Access |        Browser         |
| Http   |          GET           |

_Other parameters can be left with default value. According to the attached print_

- Import Dashboard: Click on Dashboards -> Browse -> Import -> Upload JSON file
    - Select the json file in the project root called board-grafana.json.
    - Select the newly configured Prometheus data source and click Import.

_All steps are attached in order_

</p>


<a href="https://i.postimg.cc/8krGyGxn/4-grafana.png"><img src="https://i.postimg.cc/8krGyGxn/4-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/3xZYgZz8/5-grafana.png"><img src="https://i.postimg.cc/3xZYgZz8/5-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/T2ydZg50/6-grafana.png"><img src="https://i.postimg.cc/T2ydZg50/6-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/RhJDXMF1/7-grafana.png"><img src="https://i.postimg.cc/RhJDXMF1/7-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/bvN8LtjQ/8-grafana.png"><img src="https://i.postimg.cc/bvN8LtjQ/8-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/j5Bbs3nX/9-grafana.png"><img src="https://i.postimg.cc/j5Bbs3nX/9-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/R0smYZM2/10-grafana.png"><img src="https://i.postimg.cc/R0smYZM2/10-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/Y9v7BB00/11-grafana.png"><img src="https://i.postimg.cc/Y9v7BB00/11-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/D0sKmQqK/12-grafana.png"><img src="https://i.postimg.cc/D0sKmQqK/12-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/HkTCsynf/13-grafana.png"><img src="https://i.postimg.cc/HkTCsynf/13-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/5NBVV34B/14-grafana.png"><img src="https://i.postimg.cc/5NBVV34B/14-grafana.png" width="150" height="80" alt="Grafana"/></a>
<a href="https://i.postimg.cc/c1w947CT/board.png"><img src="https://i.postimg.cc/c1w947CT/board.png" width="150" height="80" alt="Grafana"/></a>

<hr>

## Project Technologies

![Java](https://img.shields.io/badge/Java-de4341?style=flat&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-4EAA25?style=flat&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2391e6?style=flat&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/Git-e94e31?style=flat&logo=git&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-0175C2?style=flat&logo=maven&logoColor=white)