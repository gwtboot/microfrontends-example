/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.example.support.client;

import java.util.logging.Logger;

import com.example.support.client.ui.SupportView;

import elemental2.dom.CustomEvent;
import elemental2.dom.DomGlobal;
import elemental2.dom.Event;

public class SupportApp {
	
	private static Logger logger = Logger.getLogger(SupportApp.class.getName());
	
	private static final String HELLO_MENTIONED_EVENT = "helloMentionedEvent";

	public void run() {
		SupportView supportView = new SupportView();

		DomGlobal.document.getElementById("supportContainer").appendChild(supportView.element());
		
		DomGlobal.document.addEventListener(HELLO_MENTIONED_EVENT, event -> handleHelloMentionedEvent(event));
	}

	@SuppressWarnings("unchecked")
	private void handleHelloMentionedEvent(Event event) {
		logger.info("Event handled: " + HELLO_MENTIONED_EVENT);
		
		String detail = ((CustomEvent<String>) event).detail;
		logger.info("CustomEvent detail: " + detail);
	}
}
