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
package org.apache.hadoop.gateway.hbase;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.gateway.dispatch.DefaultDispatch;

/**
 * This used to be a specialized dispatch providing HBase specific features to the
 * default dispatch. Now it is just a marker class for backwards compatibility
 */
@Deprecated
public class HBaseDispatch extends DefaultDispatch {

  // KNOX-709: HBase can't handle URL encoded paths.
  public URI getDispatchUrl( HttpServletRequest request) {
    String base = request.getRequestURI();
    StringBuffer str = new StringBuffer();
    try {
      str.append( URLDecoder.decode( base, "UTF-8" ) );
    } catch( UnsupportedEncodingException e ) {
      str.append( base );
    } String query = request.getQueryString();
    if ( query != null ) {
      str.append( '?' );
      str.append( query );
    }
    URI uri = URI.create( str.toString() );
    return uri;
  }

}

