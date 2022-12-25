node {



    def servers = ['10.52.124.42', '10.52.124.43'];


    stage('BUILD_ID') {
        def buildId = params.get("lastSuccessBuildID")
        echo "BUILD_ID > ${lastSuccessBuildID}"

        if(buildId == null || buildId == "") {
            currentBuild.result = 'FAILURE'
            error("BUILD_ID FAILED")
        }
    }





    stage ('Start') {



        servers.each{ server ->



            sh "ssh service@${server} java -jar -Dspring.application.name=endive-web -Dspring.profiles.active=production -Dservice_mode=real -server -Xms3072m -Xmx3072m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB -XX:+UseThreadPriorities -XX:ThreadPriorityPolicy=42 -Djasypt.encryptor.password=newlogistics /applications/build/${lastSuccessBuildID}/endive-interfaces.jar 2>> /dev/null >> /dev/null &"



            for (i = 0; i <30; i++) {
                sleep(5)
                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/web/web-hello || true").trim()



                println('################ Status : '+response)



                if(response == '200'){

                    return
                }



                if(i == 29 && response != '200'){
                    currentBuild.result = 'FAILURE'
                    error("START FAILED")
                }
            }

        }
    }

}