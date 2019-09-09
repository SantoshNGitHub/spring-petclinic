def call() {
    timeout(time: 1, unit: 'HOURS') { 
    def qg = waitForQualityGate() 
    return qg
}
}