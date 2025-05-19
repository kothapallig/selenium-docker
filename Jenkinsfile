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
                sh 'docker build -t=jessk/selenium:latest .'
            }
        }
        stage('Push docker Image') {
			environment{
				DOCKER_HUB = credentials('DOCKERHUB-CREDENTIALS')
			}
            steps {
				sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push jessk/selenium:latest'
                sh "docker tag jessk/selenium:latest jessk/selenium:${env.BUILD_NUMBER}"
                sh "docker push jessk/selenium:${env.BUILD_NUMBER}"
            }
        }
    }
    post {
		always {
			sh 'docker logout'
		}
	}
   
}