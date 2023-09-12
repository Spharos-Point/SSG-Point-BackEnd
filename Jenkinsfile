// pipeline{
//     agent any
//     stages{
//         stage('SSH Connection'){
//             steps {
//                 sh 'ssh root@3.38.123.173'
//                 sh 'ls -al'
//                 sh 'cd /home/ssgpoint/jenkins/workspace/POINT-APP_SSG-Point-BackEnd_main'
//                 sh 'docker-composer up -d'
//             }
//         }
//         stage('Directory Change'){
//             steps {
//                 sh 'cd /home/ssgpoint/jenkins/workspace/POINT-APP_SSG-Point-BackEnd_main'
//             }
//         }
//         stage('docker-compose up'){
//             steps {
//                 sh 'docker-composer up -d'
//             }
//         }
//     }
// }

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh '''
                    chmod +x gradlew
                    ./gradlew build -x test
                '''
            }
        }
        stage('DockerSize') {
            steps {
                sh '''
                    docker stop ssgpointapp || true
                    docker rm ssgpointapp || true
                    docker rmi ssgpoint-be || true
                    docker build -t ssgpoint-be .
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d --name ssgpointapp -p 8000:8000 -e DATASOURCE_URL=jdbc:mysql://3.38.123.173:3306/ssgpoint -e DATASOURCE_PASS=ssgpointdocker ssgpoint-be'
            }
        }
    }
}
