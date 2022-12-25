package com.work.jenkins.build.api

node {



    def drainingSeconds = 60



    def servers = ['10.52.124.86','10.52.124.87','10.52.124.88','10.52.124.89']
    //def servers = ['10.52.124.86']




    stage('Init') {
        sh 'rm -rf ${WORKSPACE}/*'
    }



    stage('Checkout') {
        git (branch: 'master'
                ,credentialsId: 'kang.tw'
                ,url: 'http://10.52.221.227/newlogistics/begonia.git'
        )
    }



    stage('Build') {
        // Run the maven build
        if (isUnix()) {
            sh 'whoami'
            sh './gradlew clean begonia-interfaces:build -x test'
        } else {
            bat 'gradlew.bat clean begonia-interfaces:build -x test'
        }
    }



    stage ('Deployment') {



        servers.each{ server ->



            sh "ssh service@${server} mkdir -p /applications/build/${BUILD_ID}"
            sh "scp ${WORKSPACE}/begonia-interfaces/build/libs/* service@${server}:/applications/build/${BUILD_ID}/begonia-interfaces.jar"
            sh "ssh service@${server} rm -f /applications/nginx/html/l4-check.html"
            def stopSqs = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/sqs/stopQueue || true").trim()



            if(stopSqs != '200'){
                error( server + " SQS STOP FAILED")
            }



            print "${server} Draning....${drainingSeconds} seconds "
            sleep(drainingSeconds)



            for (i = 0; i < 3; i++) {
                sh "ssh service@${server} /usr/sbin/fuser -k -TERM 8080/tcp || true"
                sleep(5)
            }



            sh "ssh service@${server} /usr/sbin/fuser -k 8080/tcp || true"



            sh "#!/bin/sh -e\n ssh service@${server} java -jar -Dspring.profiles.active=production -Dservice_mode=real -server -Xms3072m -Xmx3072m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB -XX:+UseThreadPriorities -XX:ThreadPriorityPolicy=42 -Djasypt.encryptor.password=newlogistics /applications/build/${BUILD_ID}/begonia-interfaces.jar 2>> /dev/null >> /dev/null &"



            for (i = 0; i <30; i++) {
                sleep(5)
                def hello = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/api-hello || true").trim()



                println('################ Status : '+hello)



                if(hello == '200'){



                    sh "ssh service@${server} 'touch /applications/nginx/html/l4-check.html'"
                    def sqsStart = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/sqs/startQueue || true").trim()



                    if(sqsStart != '200'){
                        error(server + " START SQS Failed")
                    }



                    return
                }



                if(i == 29 && hello != '200'){
                    error("Buld Failed")
                }
            }

        }



    }
}