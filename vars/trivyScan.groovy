def vulnerabilityScan(String imageName){
    sh """
        trivy image $imageName \
            --severity LOW,MEDIUM,HIGH \
            --exit-code 0 \
            --quiet \
            --format json -o trivy-image-HIGH-results.json
        
        trivy image $imageName \
            --severity CRITICAL \
            --exit-code 1 \
            --quiet \
            --format json -o trivy-image-CRITICAL-results.json
    """
}

def reportsConverter(){
    sh '''
        trivy convert \
        --format template --template "@/usr/local/share/trivy/templates/html.tpl" \
        --output trivy-image-HIGH-results.html trivy-image-HIGH-results.json
        
        trivy convert \
        --format template --template "@/usr/local/share/trivy/templates/html.tpl" \
        --output trivy-image-CRITICAL-results.html trivy-image-CRITICAL-results.json
        
        trivy convert \
        --format template --template "@/usr/local/share/trivy/templates/junit.tpl" \
        --output trivy-image-HIGH-results.xml trivy-image-HIGH-results.json

        trivy convert \
        --format template --template "@/usr/local/share/trivy/templates/junit.tpl" \
        --output trivy-image-CRITICAL-results.xml trivy-image-CRITICAL-results.json
    '''
}