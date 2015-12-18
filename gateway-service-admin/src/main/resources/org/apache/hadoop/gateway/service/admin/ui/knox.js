/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function makeGetRequest(url, callback) {
  var request = new XMLHttpRequest();
  request.withCredentials = true;
  request.onreadystatechange = function () {
    callback(request);
  };
  request.open("GET", url, true);
  request.setRequestHeader("Authorization", "Basic " + btoa("admin:admin-password"));
  request.setRequestHeader("Accept", "application/json");
  request.send();
}

function populateTopologies(request) {
  var topList = document.getElementById("topologies-list");
  while (topList.firstChild) {
    topList.removeChild(topList.firstChild);
  }

  if (request.readyState == 4) {
    if (request.response != ""){
      var js2 = JSON.parse(request.response);
      js2 = js2.topologies.topology;
      for (j = 0; j < js2.length; j++) {

        var e = document.createElement("li");
        var link = document.createElement("a");
        link.setAttribute("onclick", "getTopologyInfo(\"" + js2[j].href + "\")");
        link.innerHTML = js2[j].name;
        e.appendChild(link);
        topList.appendChild(e);

      }
    }
  } else {
    var elem = document.createElement("ul");
    elem.innerHTML = "Loading Topologies...";
    topList.appendChild(elem);
  }
}

function getTopologyInfo(url){
  makeGetRequest(url, populateTopologyInfo);
}

function populateTopologyInfo(request) {
  var textArea = document.getElementById("topology-info")
  if (request.readyState == 4) {
    if (request.response != ""){
      var js2 = JSON.parse(request.response);
      textArea.innerHTML = JSON.stringify(js2);
    }
  } else {
    textArea.innerHTML = "Loading...";
  }
}

function getBaseURI() {
  var uri = "";
  uri += location.protocol + "//";
  uri += location.host + "/";
  var path = location.pathname.substring(1, location.pathname.indexOf("/ui/home"))
  uri += path;
  return uri;
}

function updateTopologies() {
  makeGetRequest("https://localhost:8443/gateway/admin/api/v1/topologies", populateTopologies);
}

updateTopologies();