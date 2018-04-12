/*
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
package org.ops4j.pax.transx.jdbc.bench;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.ops4j.pax.transx.jdbc.ManagedDataSourceBuilder;
import org.ops4j.pax.transx.jdbc.stubs.StubDataSource;

import javax.resource.spi.TransactionSupport;
import javax.sql.DataSource;

@State(Scope.Benchmark)
public class BenchBase
{
    @Param({ "32" })
    public int maxPoolSize;

    public static DataSource DS;

    @Setup(Level.Trial)
    public void setup(BenchmarkParams params) throws Exception
    {
        DS = ManagedDataSourceBuilder.builder()
                .dataSource(new StubDataSource())
                .userName("gnodet")
                .password("")
                .transaction(TransactionSupport.TransactionSupportLevel.NoTransaction)
                .minIdle(0)
                .maxPoolSize(maxPoolSize)
                .connectionTimeout(8000)
                .build();
    }

    @TearDown(Level.Trial)
    public void teardown() throws Exception
    {
        ((AutoCloseable) DS).close();
    }

}
