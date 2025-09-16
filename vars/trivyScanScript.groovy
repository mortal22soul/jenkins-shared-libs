def vulnerabilityScan(Map config = [:]) {
    // Load trivy.sh from resources/scripts
    loadScript(name: 'trivy.sh')
    // Execute the script with user-supplied arguments
    sh "./trivy.sh ${config.imageName} ${config.severity} ${config.exitCode}"
}