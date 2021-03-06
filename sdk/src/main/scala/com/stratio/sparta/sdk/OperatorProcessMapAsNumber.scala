/*
 * Copyright (C) 2015 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.sparta.sdk

import java.io.{Serializable => JSerializable}

import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

trait OperatorProcessMapAsNumber {

  val inputSchema: StructType

  val inputField: Option[String]

  def applyFilters(row: Row): Option[Map[String, Any]]

  def processMap(inputFieldsValues: Row): Option[Number] =
    if (inputField.isDefined && inputSchema.fieldNames.contains(inputField.get))
      applyFilters(inputFieldsValues)
        .map(filteredFields => Operator.getNumberFromAny(filteredFields.get(inputField.get).get))
    else None
}
