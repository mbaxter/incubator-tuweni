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
package org.apache.tuweni.devp2p.v5.topic

import org.apache.tuweni.bytes.Bytes

class TicketHolder {

  private val tickets: MutableMap<Bytes, Bytes> = hashMapOf() // requestId to ticket

  fun put(requestId: Bytes, ticket: Bytes) {
    tickets[requestId] = ticket
  }

  fun get(requestId: Bytes): Bytes =
    tickets[requestId] ?: throw IllegalArgumentException("Ticket not found.")

  fun remove(requestId: Bytes): Bytes? = tickets.remove(requestId)

  fun contains(ticket: Bytes): Boolean = tickets.containsValue(ticket)
}
