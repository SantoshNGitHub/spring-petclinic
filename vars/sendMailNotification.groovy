def call(String mailBody, String mailSubject) {
 
     emailext body: mailBody, recipientProviders: [[$class: 'DevelopersRecipientProvider'], 
          [$class: 'RequesterRecipientProvider']], subject: mailSubject
  }