# Jenkins CI CD Pipeline Using Pipeline as Code #

Clone or fork the sample spring boot [spring-petclinic](https://github.com/spring-projects/spring-petclinic) project which is used to CI CD.

Three Linux Ubuntu VMs have been used for this Hands On:
* dfaos20322dns0.eastus2.cloudapp.azure.com ----> vm0
* dfaos20322dns1.eastus2.cloudapp.azure.com ----> vm1
* dfaos20322dns2.eastus2.cloudapp.azure.com ----> vm2

## Goal ##

Create Jenkins shared library for templatization.

With every commit to the source repo the jenkins job has to be triggered which will execute the following pipeline stages:

1. **Checkout** : It will pull the code from the source repository. 

2. **Sonar-Analysis**  : It will scan the repository for checking Code quality.

3. **Sonar Quality Gate**  : If the sonar coverage is more than 70% then the stage passes otherwise fails.

4. **Build** : It builds the spring boot project and package it as jar file.

5. **Artifact upload using Artifactory** : It uploads the the jar to the Artifcatory.

6. **Artifact download using Artifactory** : It downloads the jar from artifactory
to the vm2.

6. **Docker Build and Push to Docker hub** : It creates the docker image of spring-petclinic app and push to docker hub.

7. **Docker Pull and Deploy** : Pull image from docker hub and deploy the app to vm0 using docker compose.

8. **Deploy Using Ansible** : Copy the petclinic artifact from vm0 to vm2 and deploy.

9. **Notify** : Notify user in case of failed/successful build.

## Tools and Plugins Configurations in Jenkins

The Jenkins, Docker and Ansible are installed and configured in vm0.
SonarQube is installed and configured in vm1.
Artifactory is installed and configured in vm2.

1. **Jenkins Job Configuration** </br>

2. **Shared Library** </br>
    Create var folder in the repo to store groovy script library.

    Configure Jenkins shared library  
    

2. **Git** </br>
    
    
3. **Maven Configuration**
   

4. **Sonar Configuration**

5. **Artifactory Configuration**


6. **Docker Configuration** </br>
Create Docker Hub credentials in Jenkins.  
Jenkins -> Credentials -> System -> Global credentials -> Add Credentials -> Fill the required details -> OK  

7. **Ansible Configuration** </br>
    Create connection between vm0 and vm2. Mention vm2 in ansible inventory(Edit etc/ansible/hosts).</br> 
    `[vms]` </br>
    `dfaos20322dns2.eastus2.cloudapp.azure.com`  

    Check the connection.  
    `sudo ansible -i hosts -m ping all`
   
8. **Mail Configuration** </br>
    Install and configure Email Extension plugin.
   

## Webhooks Creation

* **GitHub** </br>
    Open Source repo -> Settings -> Webhooks -> Add webhook </br>
    Payload URL: `http://dfaos20322dns0.eastus2.cloudapp.azure.com:8080/github-webhook/`
    
* **SonarQube** </br> 
    Open Sonarqube -> Administration -> Webhooks </br>
    Name : `jenkins` </br>
    URL: `http://dfaos20322dns0.eastus2.cloudapp.azure.com:8080/github-webhook/`

## Jenkins Stages Result Logs
1. **Checkout** </br>
![Checkout Logs](screenshots/CheckoutResult.png)

2. **Sonar-Analysis** </br>
![Sonar Scan Result](screenshots/SonarScanResult.png)

3. **Sonar Quality Gate** </br>
![Sonar Quality Gates Result](screenshots/SonarQualityGateResult.png)

4. **Build** </br>
![Build Result](screenshots/BuildResult.png)

5. **Artifact upload using Artifactory** </br>
![Artifactory Upload Result](screenshots/ArtifactoryUploadResult.png)  

![Artifactory Upload Result](screenshots/spring-petclinic-artifactory.png)

6. **Artifact download using Artifactory** </br>
![Artifactory Upload Result](screenshots/ArtifactoryDownloadResult.png)

7. **Docker Build and Push to Docker hub** </br>



