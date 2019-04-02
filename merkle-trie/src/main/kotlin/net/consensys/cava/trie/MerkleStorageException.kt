/*
 * Copyright 2018 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.apache.tuweni.trie

/**
 * This exception is thrown when there is an issue retrieving or decoding values from [MerkleStorage].
 */
class MerkleStorageException : RuntimeException {

  /**
   * Constructs a new exception with the specified detail message.
   * The cause is not initialized, and may subsequently be initialized by a
   * call to {@link #initCause}.
   *
   * @param message The detail message.
   */
  constructor(message: String) : super(message)

  /**
   * Constructs a new exception with the specified detail message and
   * cause.
   *
   * @param message The detail message.
   * @param cause The cause.
   */
  constructor(message: String, cause: Exception) : super(message, cause)
}
