/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pulsar.functions.worker;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import org.testng.annotations.Test;

/**
 * Unit test of {@link WorkerConfig}.
 */
public class WorkerApiV2ResourceConfigTest {

    private static final String TEST_NAME = "test-worker-config";

    @Test
    public void testGetterSetter() {
        WorkerConfig wc = new WorkerConfig();
        wc.setPulsarServiceUrl("pulsar://localhost:1234");
        wc.setPulsarFunctionsNamespace("sample/standalone/functions");
        wc.setFunctionMetadataTopicName(TEST_NAME + "-meta-topic");
        wc.setNumFunctionPackageReplicas(3);
        wc.setWorkerId(TEST_NAME + "-worker");
        wc.setWorkerPort(1234);

        assertEquals("pulsar://localhost:1234", wc.getPulsarServiceUrl());
        assertEquals(TEST_NAME + "-meta-topic", wc.getFunctionMetadataTopicName());
        assertEquals("sample/standalone/functions", wc.getPulsarFunctionsNamespace());
        assertEquals(3, wc.getNumFunctionPackageReplicas());
        assertEquals(TEST_NAME + "-worker", wc.getWorkerId());
        assertEquals(1234, wc.getWorkerPort());
    }

    @Test
    public void testLoadWorkerConfig() throws Exception {
        URL yamlUrl = getClass().getClassLoader().getResource("test_worker_config.yml");
        WorkerConfig wc = WorkerConfig.load(yamlUrl.toURI().getPath());

        assertEquals("pulsar://localhost:6650", wc.getPulsarServiceUrl());
        assertEquals("test-function-metadata-topic", wc.getFunctionMetadataTopicName());
        assertEquals(3, wc.getNumFunctionPackageReplicas());
        assertEquals("test-worker", wc.getWorkerId());
        assertEquals(7654, wc.getWorkerPort());
    }

}
