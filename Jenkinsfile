def PROJECT_NAME = 'spharos-0.0.1-SNAPSHOT
pipeline{
    agent any
    stages{
        stage('Prepare'){
            steps {
                sh 'gradle clean'
            }
        }
        stage('Build') {
            steps {
                sh 'gradlew build -x test'
            }
        }
        stage('Test') {
            steps {
                sh 'gradlew test'
            }
        }
        stage('Deploy Prepare'){
            steps{
                sh 'sudo kill $(pgrep -f ${PROJECT_NAME})'
            }
        }
        stage('Deploy') {
            steps {
                sh 'nohup java -jar ./build/libs/${PROJECT_NAME}.jar &'
            }
        }
    }
}