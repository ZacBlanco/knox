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
package org.apache.hadoop.gateway.service.admin.ui;

import org.eclipse.persistence.oxm.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static javax.ws.rs.core.MediaType.TEXT_HTML;
import static javax.ws.rs.core.Response.ok;

@Path("/ui")
public class UIResource {
  @Context
  private HttpServletRequest request;

  @GET
  @Produces({TEXT_HTML})
  @Path("home")
  public Response getHome() throws FileNotFoundException{
    InputStream i = this.getClass().getResourceAsStream("index.html");

    return ok().entity(i).header("Access-Control-Allow-Origin", "*").allow("OPTIONS").build();
  }




}

