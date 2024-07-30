# just give your credentialsId for the docker repo that you have saved in the Jenkins credentials manager.
def login() {
  withCredentials([string(credentialsId: 'DOCKER_PASSWORD', variable: 'DOCKER_PASSWORD')]){
     sh "docker -H tcp://172.17.0.1:4200 login -u divyankchauhan -p $DOCKER_PASSWORD"
   
    }
}

def build(String tag) {
   def scriptcontents = libraryResource "Dockerfile"
   writeFile file:"Dockerfile", text: scriptcontents

    sh """
        docker build  -t "${tag}"  .
    """
}

def push(String tag) {
    sh """
        docker push "${tag}"
    """
}
