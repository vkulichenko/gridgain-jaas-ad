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

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.events.EventType;
import org.apache.ignite.plugin.security.SecuritySubject;
import org.gridgain.grid.GridGain;

public class Server {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start("server.xml");

        ignite.events().localListen(event -> {
            System.out.println("Authenticated subjects:");

            GridGain gg = ignite.plugin(GridGain.PLUGIN_NAME);

            for (SecuritySubject subject : gg.security().authenticatedSubjects())
                System.out.println("    " + subject.login() + ": " + subject.permissions());

            return true;
        },
        EventType.EVT_NODE_JOINED);

        IgniteCache<Integer, Integer> cache = ignite.cache("TestCache");

        for (int i = 0; i < 10; i++)
            cache.put(i, i);
    }
}
