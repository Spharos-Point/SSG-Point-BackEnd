pipeline{
    agent any
    stages{
        stage('SSH Connection'){
            steps {
                sh 'ssh root@3.38.123.173'
            }
        }
        stage('Directory Change'){
            steps {
                sh 'cd /home/ssgpoint/jenkins/workspace/POINT-APP_SSG-Point-BackEnd_main'
            }
        }
        stage('docker-compose up'){
            steps {
                sh 'docker-composer up -d'
            }
        }
    }
}