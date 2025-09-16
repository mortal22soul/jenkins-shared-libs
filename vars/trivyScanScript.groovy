def vulnerabilityScan(Map config = [:]){
    loadScript(name: trivy.sh)
    sh "./trivy.sh $(config.imageName) $(config.imageName) $(config.imageName)}"
}