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
package org.apache.tuweni.devp2p.v5.storage

import com.google.common.math.IntMath
import org.apache.tuweni.bytes.Bytes
import org.apache.tuweni.crypto.Hash
import org.apache.tuweni.kademlia.KademliaRoutingTable
import org.apache.tuweni.kademlia.xorDist
import java.math.RoundingMode

class RoutingTable(
  private val selfEnr: Bytes
) {

  private val selfNodeId = key(selfEnr)
  private val nodeIdCalculation: (Bytes) -> ByteArray = { enr -> key(enr) }

  private val table = KademliaRoutingTable(
    selfId = selfNodeId,
    k = BUCKET_SIZE,
    nodeId = nodeIdCalculation,
    distanceToSelf = {
      val xorResult = key(it) xorDist selfNodeId
      if (xorResult == 0) 0 else IntMath.log2(xorResult, RoundingMode.FLOOR)
    })

  val size: Int
    get() = table.size

  fun getSelfEnr(): Bytes = selfEnr

  fun add(enr: Bytes) {
    if (enr != selfEnr) {
      table.add(enr)
    }
  }

  fun nearest(targetId: Bytes, limit: Int = BUCKET_SIZE): List<Bytes> = table.nearest(key(targetId), limit)

  fun distanceToSelf(targetId: Bytes): Int = table.logDistToSelf(targetId)

  fun evict(enr: Bytes): Boolean = table.evict(enr)

  fun random(): Bytes = table.getRandom()

  fun isEmpty(): Boolean = table.isEmpty()

  fun nodesOfDistance(distance: Int): List<Bytes> = table.peersOfDistance(distance)

  fun clear() = table.clear()

  private fun key(enr: Bytes): ByteArray = Hash.sha2_256(enr).toArray()

  companion object {
    private const val BUCKET_SIZE: Int = 16
  }
}
