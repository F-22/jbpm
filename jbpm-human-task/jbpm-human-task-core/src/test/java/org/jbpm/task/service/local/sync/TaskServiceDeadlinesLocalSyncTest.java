/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.task.service.local.sync;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jbpm.task.service.base.sync.TaskServiceDeadlinesBaseSyncTest;
import org.jbpm.task.service.local.LocalTaskService;
import org.subethamail.wiser.Wiser;

public class TaskServiceDeadlinesLocalSyncTest extends TaskServiceDeadlinesBaseSyncTest {


    protected EntityManagerFactory createEntityManagerFactory() { 
        return Persistence.createEntityManagerFactory("org.jbpm.task.local");
    }
    
    @Override
    protected void setUp() throws Exception {
        setupJTADataSource();       
		super.setUp();

		setConf(new Properties());
		getConf().setProperty("mail.smtp.host", "localhost");
		getConf().setProperty("mail.smtp.port", "2345");
		getConf().setProperty("from", "from@domain.com");
		getConf().setProperty("replyTo", "replyTo@domain.com");
		getConf().setProperty("defaultLanguage", "en-UK");
		
                setWiser(new Wiser());
		getWiser().setHostname(getConf().getProperty("mail.smtp.host"));
		getWiser().setPort(Integer.parseInt(getConf().getProperty("mail.smtp.port")));        
		getWiser().start();
                
		client = new LocalTaskService( taskService);
	}

}
