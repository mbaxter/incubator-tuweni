/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tuweni.devp2p.v5.internal.handler

import org.apache.tuweni.bytes.Bytes
import org.apache.tuweni.devp2p.v5.MessageHandler
import org.apache.tuweni.devp2p.v5.UdpConnector
import org.apache.tuweni.devp2p.v5.packet.FindNodeMessage
import org.apache.tuweni.devp2p.v5.packet.NodesMessage
import java.net.InetSocketAddress

class FindNodeMessageHandler : MessageHandler<FindNodeMessage> {

  override suspend fun handle(
    message: FindNodeMessage,
    address: InetSocketAddress,
    srcNodeId: Bytes,
    connector: UdpConnector
  ) {
    if (0 == message.distance) {
      val response = NodesMessage(message.requestId, 1, listOf(connector.getEnrBytes()))
      connector.send(address, response, srcNodeId)
      return
    }

    val nodes = connector.getNodesTable().nodesOfDistance(message.distance)

    nodes.chunked(MAX_NODES_IN_RESPONSE).forEach {
      val response = NodesMessage(message.requestId, nodes.size, it)
      connector.send(address, response, srcNodeId)
    }
  }

  companion object {
    private const val MAX_NODES_IN_RESPONSE: Int = 4
  }
}
