def call() {
    sh 'sudo ansible-playbook /home/devopsinfra/deploy-petclinic.yaml'
}