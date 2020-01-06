pipeline{
    agent any

    triggers{
        upstream(
            upstreamProjects: 'Spikot,SpikotClassLocator',
            threshold: hudson.model.Result.SUCCESS
        )
    }

    environment{
        MAVEN_CREDENTIAL = credentials('heartpattern-maven-repository')
    }

    stages{
            stage('start daemon'){
                steps{
                    sh './gradlew --daemon'
                }
            }
            stage('compile'){
                steps{
                    sh './gradlew -PnexusUser=${MAVEN_CREDENTIAL_USR} -PnexusPassword=${MAVEN_CREDENTIAL_PSW} clean build'
                }
            }
            stage('test'){
                steps{
                    sh './gradlew -PnexusUser=${MAVEN_CREDENTIAL_USR} -PnexusPassword=${MAVEN_CREDENTIAL_PSW} test'
                }
            }
            stage('create plugin'){
                steps{
                    sh './gradlew -PnexusUser=${MAVEN_CREDENTIAL_USR} -PnexusPassword=${MAVEN_CREDENTIAL_PSW} createPlugin'
                }
            }
        }
    post{
        always{
            archiveArtifacts artifacts: 'build/libs/SpikotAdapter-S1122-Plugin.jar'
        }
    }
}