@Library('shared-lib@master') _

node {   
   def gitBranch = 'master'   
   def gitURL = 'https://github.com/spring-projects/spring-petclinic.git'
   def mvnHome = tool 'm3.6.1'
   def sonarPlugin = 'sonar-4'
   def server = Artifactory.server 'artifactory'
   def artifactorySourceDir = '/var/lib/jenkins/workspace/basic-pipeline2/target/*.jar'
   def artifactoryUploadRepo = 'example-repo-local/'
   def registryURL = 'https://registry.hub.docker.com'
   def dockerCredentialId = 'dockerhub'
   def dockerImageAndTag = 'santosh99y/petclinic:v1'
   def mailBody
   def mailSubject = 'Jenkins CI-CD Pipeline Status'
   try {
   //Checkout repo from git.  
   stage('Checkout') {   
     
     try {
         // cleanWs()
        gitCheckout(
        branch: gitBranch,
        url: gitURL  
        )
     }catch(Exception e) {
         throw new Exception("Unable to checkout from git.")
     }              
   }

   stage('Build') {
       try {
           prepareJar(mvnHome)
       }catch(Exception e) {
           throw new Exception("Unable to build the repo.")
       }
      
    }

    stage('Sonar-Analysis') { 
        try {
           sonarScan(mvnHome)
       }catch(Exception e) {
           throw new Exception("Sonar scanning failed.")
       }
       
     }


     //Sonar quality gate for 70% coverage
     stage("Sonar Quality Gate"){
         def qualityGateStatus = sonarQualityGate()
         if (qualityGateStatus.status != 'OK') {
            throw new Exception("Did not pass sonar quality gate.")
        }
      }  

    //Prepare docker image and push to docker hub.
    stage('Docker Build and Push to Docker hub'){ 
        try {
            createAndPushDockerImage(registryURL, dockerCredentialId, dockerImageAndTag)
        }catch(Exception e) {
            throw new Exception("Unable to build docker image.")
        }  
          
    } 
  
    //Pull image from docker hub and deploy 
     stage('Docker Pull and Deploy'){    
      input 'Proceed to deploy?'
      try {
          deployUsingDockerCompose()
      }catch(Exception e) {
          throw new Exception("Deployment using docker compose failed.")
      }
      
     }   

     //Deploy using ansible in 3rd vm
     stage('Deploy Using Ansible'){      
        input 'Proceed to deploy?'
         try {
             deployUsingAnsible()
        }catch(Exception e) {
          throw new Exception("Deployment using docker compose failed.")
        }      
    
     }   

     stage('Notify') {    
         mailBody = 'The application is deployed.'

   def mailSubject = 'Jenkins CI-CD Pipeline Status'
          sendMailNotification(mailBody, mailSubject) 
    }  

   }catch(Exception e) {
       echo e.getMessage()
       currentBuild.result = "FAILURE" 
       mailBody = 'The jenkins build failed'
       sendMailNotification(mailBody, mailSubject) 

   } 

}