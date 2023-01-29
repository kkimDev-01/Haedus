package com.work.jenkins.stop.api

node {



    def servers = ['10.52.124.86','10.52.124.87','10.52.124.88','10.52.124.89']



    stage ('Stop') {



        servers.each{ server ->



            def stopSqs = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/sqs/stopQueue || true").trim()



            if(stopSqs != '200'){
                error( server + " SQS STOP FAILED")
            }




            for (i = 0; i < 3; i++) {
                sh "ssh service@${server} /usr/sbin/fuser -k -TERM 8080/tcp || true"
                sleep(3)
            }



            sh "ssh service@${server} /usr/sbin/fuser -k 8080/tcp || true"

            for (i = 0; i < 6; i++) {
                sleep(3)

                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/api-hello || true").trim()



                println('################ SHUTDOWN WATING : '+ i)



                if(response != '200'){
                    currentBuild.result = 'SUCCESS'
                    break
                }



                if(i == 5 && response == '200'){
                    currentBuild.result = 'FAILURE'
                    error("SHUTDOWN FAILED")
                }
            }

        }



    }


}