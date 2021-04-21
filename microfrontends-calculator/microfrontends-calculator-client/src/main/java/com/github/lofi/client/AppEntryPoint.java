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
package com.github.lofi.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.CustomEvent;
import elemental2.dom.CustomEventInit;
import elemental2.dom.DomGlobal;

public class AppEntryPoint implements EntryPoint {

	private static final String CALCULATOR_CREATED_EVENT = "calculatorCreatedEvent";

	private static Logger logger = Logger.getLogger(AppEntryPoint.class.getName());

	@Override
	public void onModuleLoad() {
		// Do nothing, just to load the Java classes
		logger.info("onModuleLoad: create Calculator");

		new Calculator("From Calculator Microfrontends");

		// Create event and dispatch because we are finished...
		createAndDispatchCustomEvent();
	}

	@SuppressWarnings("unchecked")
	private void createAndDispatchCustomEvent() {
		logger.info("createAndDispatchCustomEvent: " + CALCULATOR_CREATED_EVENT);

		CustomEventInit<String> customEventInit = CustomEventInit.create();
		customEventInit.setDetail("Calculator created...");
		CustomEvent<String> customEvent = new CustomEvent<String>(CALCULATOR_CREATED_EVENT, customEventInit);

		DomGlobal.document.dispatchEvent(customEvent);
	}
}
