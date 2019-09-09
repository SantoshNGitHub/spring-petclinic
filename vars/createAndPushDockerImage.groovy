def call(String registryURL, String dockerCredentialId, String dockerImageAndTag) {
 
     docker.withRegistry(registryURL, dockerCredentialId) {
          def customImage = docker.build(dockerImageAndTag)       
         customImage.push()
        
     }  
  }