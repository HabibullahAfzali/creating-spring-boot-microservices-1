node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Default Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=HabibullahAfzali_creating-spring-boot-microservices-1_ce069451-b141-4c86-ac4d-de3f6b3dc190 -Dsonar.projectName='creating-spring-boot-microservices-1'"
    }
  }
}
