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
package org.apache.tuweni.config;

/**
 * An exception thrown when a requested configuration property is not found.
 *
 * <p>
 * This exception can be avoided by using a schema that provides a default value or asserts that a value has been
 * provided in the configuration.
 */
public final class NoConfigurationPropertyException extends RuntimeException {

  NoConfigurationPropertyException(String message) {
    super(message);
  }
}
