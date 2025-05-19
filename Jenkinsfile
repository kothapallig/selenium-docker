pipeline{
    agent any
    stages {
        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t=gkdocker/selenium .'
            }
        }
        stage('Push docker Image') {
            steps {
                sh 'docker push gkdocker/selenium'
            }
        }
    }
   
}