package com.work.jenkins.stop.web


node {


    def servers = ['10.52.124.42', '10.52.124.43'];


    stage('Stop') {
        servers.each { server ->


            for (i = 0; i < 3; i++) {
                sh "ssh service@${server} /usr/sbin/fuser -k -TERM 8080/tcp || true"
                sleep(3)
            }

            sh "ssh service@${server} /usr/sbin/fuser -k 8080/tcp || true"

            for (i = 0; i < 30; i++) {
                sleep(5)
                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/web/web-hello || true").trim()


                println('################ Status : ' + response)


                if (i == 29 && response != '200') {
                    currentBuild.result = 'SUCCESS'
                    error("WEB STOP SUCCESS")
                }
            }

        }
    }
}