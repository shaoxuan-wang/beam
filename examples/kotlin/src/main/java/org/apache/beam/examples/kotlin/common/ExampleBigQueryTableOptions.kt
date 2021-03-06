/*
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
package org.apache.beam.examples.kotlin.common

import com.google.api.services.bigquery.model.TableSchema
import org.apache.beam.sdk.extensions.gcp.options.GcpOptions
import org.apache.beam.sdk.options.Default
import org.apache.beam.sdk.options.DefaultValueFactory
import org.apache.beam.sdk.options.Description
import org.apache.beam.sdk.options.PipelineOptions

/**
 * Options that can be used to configure BigQuery tables in Beam examples. The project defaults to
 * the project being used to run the example.
 */
interface ExampleBigQueryTableOptions : GcpOptions {
    @get:Description("BigQuery dataset name")
    @get:Default.String("beam_examples")
    var bigQueryDataset: String

    @get:Description("BigQuery table name")
    @get:Default.InstanceFactory(BigQueryTableFactory::class)
    var bigQueryTable: String

    @get:Description("BigQuery table schema")
    var bigQuerySchema: TableSchema

    /** Returns the job name as the default BigQuery table name.  */
    class BigQueryTableFactory : DefaultValueFactory<String> {
        override fun create(options: PipelineOptions): String {
            return options.jobName.replace('-', '_')
        }
    }
}
