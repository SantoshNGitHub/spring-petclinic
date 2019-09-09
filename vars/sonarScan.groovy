def call(String mvnHome) {
withSonarQubeEnv() {
           withEnv(["MVN_HOME=$mvnHome"]) {         
              sh '"$MVN_HOME/bin/mvn" sonar:sonar'       
        }  
      }
}