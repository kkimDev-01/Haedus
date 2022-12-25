package com.work
//https://www.baeldung.com/groovy-lists
//https://www.baeldung.com/groovy-maps
node {


    def ec2cmd = null
    def servers = null
    def tag = "Deploy"
    def profile = "prod"
    def values = ["logistics-prd-azalea-api",
                  "logistics-prd-azalea-web",
                  "logistics-prd-crocus-api",
                  "logistics-prd-crocus-web",
                  "logistics-prd-endive-api",
                  "logistics-prd-hornet-api",
                  "logistics-prd-hornet-web" ,
                  "logistics-prd-ivy-api",
                  "logistics-prd-ivy-web"]
    def map = [:]
    def serverGroup = []

    stage('EC2 IP') {

        for (String value : values) {
            script {
                ec2cmd = sh(returnStdout : true, script : "aws ec2 describe-instances --profile ${profile} --filters Name=tag:${tag},Values=${value} --query Reservations[*].Instances[*].[PrivateIpAddress] --output text") // | paste -sd,
            }
            servers = ec2cmd.split('\n')

            if(servers != null && servers.size() > 0) {

                serverGroup = []

                servers.each{ server ->
                    echo "server ip of ${value} > ${server}"
                }

                for (String server : servers){
                    serverGroup.add(server)
                }

                map[value] = serverGroup

                for (String v : serverGroup){
                    echo "server ip from List > ${v}"
                }


            } else {
                currentBuild.result = 'FAILURE'
                error("EC2 IP FAILED")
            }

        }

        map["prod.begonia.api"] = ["10.52.124.86", "10.52.124.87", "10.52.124.88", "10.52.124.89"]
        map["prod.endive.server"] = ["10.52.124.42", "10.52.124.43"]

        for (String serverName : map.keySet()){
            def group = map.get(serverName)
            for (String v : group) {
                echo "server ip from map with key ${serverName} > ${v}"
            }
        }


    }


    stage('Stop WAS'){
        for (String serverName : map.keySet()) {
            def group = map.get(serverName)
            for (String v : group) {
                for (int i = 0; i < 3; i++) {
                    sh "ssh service@${v} /usr/sbin/fuser -k -TERM 8080/tcp || true"
                    sleep(3)
                }

                sh "ssh service@${v} /usr/sbin/fuser -k 8080/tcp || true"

                for (int i = 0; i < 6; i++) {
                    sleep(3)

                    def response = sh(returnStdout: true, script: "ssh service@${v} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/api-hello || true").trim()



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

}