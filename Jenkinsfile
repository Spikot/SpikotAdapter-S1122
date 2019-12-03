pipeline{
    agent any

    environment{
        MAVEN_CRED = credentials('heartpattern-maven-repository')
    }

    stages{
        stage('test'){
            steps{
                sh './gradlew -PnexusUser=${MAVEN_CRED_USR} -PnexusPassword=${MAVEN_CRED_PWD} clean test'
            }
        }
        stage('create plugin'){
            steps{
                sh './gradlew -PnexusUser=${MAVEN_CRED_USR} -PnexusPassword=${MAVEN_CRED_PWD} createPlugin'
            }
        }
    }
    post{
        always{
            archiveArtifacts artifacts: 'build/libs/Spikot-S1122-Plugin.jar'
        }
    }
}