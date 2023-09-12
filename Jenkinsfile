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
                    sudo apt-get update
                    sudo apt-get install docker.io
                    sudo ln -sf /usr/bin/docker.io /usr/local/bin/docker
                '''
            }
        }
        stage('DockerSize') {
            steps {
                sh '''
                    docker stop ssgpointapp || true
                    docker rm ssgpointapp || true
                    docker rmi ssgpoint_be || true
                    docker build -t ssgpoint_be .
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d —name ssgpointapp -p 8000:8000 ssgpoint_be'
            }
        }
    }
}
