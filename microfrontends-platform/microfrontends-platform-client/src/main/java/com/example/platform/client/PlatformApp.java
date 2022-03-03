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
package com.example.platform.client;

import static com.example.platform.client.ui.PlatformClientBundle.CONSTANTS;

import java.util.logging.Logger;

import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.gwtproject.timer.client.Timer;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;

import elemental2.dom.CustomEvent;
import elemental2.dom.CustomEventInit;
import elemental2.dom.DomGlobal;

public class PlatformApp {

	private static Logger logger = Logger.getLogger(PlatformApp.class.getName());

	private static String MICROFRONTENDS_ORDER_CLIENT = "http://localhost:9999/order/order.nocache.js";

	private static String MICROFRONTENDS_SUPPORT_CLIENT = "http://localhost:7777/support/support.nocache.js";

	private static final String HELLO_MENTIONED_EVENT = "helloMentionedEvent";

	public void run() {
		injectScript(MICROFRONTENDS_ORDER_CLIENT);
	}

	private void initLayout() {
		Layout layout = Layout.create(CONSTANTS.appTitle()).removeLeftPanel().show(ColorScheme.BLUE);

		DomGlobal.document.getElementById("platformContainer").appendChild(layout.element());

		// Create event and dispatch...
		createAndDispatchHelloMentionedEvent();
	}

	private void injectScript(String scriptUrl) {
		ScriptInjector.fromUrl(scriptUrl).setCallback(new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				logger.info("Script load failed: " + scriptUrl);
			}

			public void onSuccess(Void result) {
				logger.info("Script load success: " + scriptUrl);
				if (scriptUrl.equals(MICROFRONTENDS_SUPPORT_CLIENT)) {
					// End this call
					initLayout();
				} else {
					// Next JS injection
					injectScript(MICROFRONTENDS_SUPPORT_CLIENT);
				}
			}
		}).setWindow(ScriptInjector.TOP_WINDOW).inject();
	}

	@SuppressWarnings("unchecked")
	private void createAndDispatchHelloMentionedEvent() {
		// Waiting to have all the Microfrontends loaded
		Timer timer = new Timer() {
			@Override
			public void run() {
				logger.info("createAndDispatchCustomEvent: " + HELLO_MENTIONED_EVENT);

				CustomEventInit<String> customEventInit = CustomEventInit.create();
				customEventInit.setDetail("Hello world mentioned...");
				CustomEvent<String> customEvent = new CustomEvent<String>(HELLO_MENTIONED_EVENT, customEventInit);

				DomGlobal.document.dispatchEvent(customEvent);
			}
		};

		timer.schedule(15000);
	}
}
