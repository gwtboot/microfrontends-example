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
package com.example.order.client;

import java.util.logging.Logger;

import com.example.order.client.ui.OrderView;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;

import elemental2.dom.DomGlobal;

public class OrderApp {

	private static Logger logger = Logger.getLogger(OrderApp.class.getName());

	private static String MICROFRONTENDS_CALCULATOR_CLIENT = "http://localhost:9899/calculator/calculator.nocache.js";

	public void run() {
		injectScript(MICROFRONTENDS_CALCULATOR_CLIENT);
	}

	private void injectScript(String scriptUrl) {
		ScriptInjector.fromUrl(scriptUrl).setCallback(new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				logger.info("Script load failed: " + scriptUrl);
			}

			public void onSuccess(Void result) {
				logger.info("Script load success: " + scriptUrl);

				initLayout();
			}
		}).setWindow(ScriptInjector.TOP_WINDOW).inject();
	}

	protected void initLayout() {
		// Too early: I havent run it, but your calculator.nocache.js is
		// probably a gwt app by the name, and it won't run instantly after it is loaded
		// on the other hand your calculator-withwait.js is deferring over
		// and over until the Calculator constructor is ready
		// you could defer in the same way in java, or modify calculator.nocache.js to
		// run instantly (if you have no permutations and solve caching some other way)
		// by using the sso linker

		// I think that instead of "waiting" like you do (by polling every 10ms),
		// I'd rather make the apps "collaborate" by firing an event from the entry
		// point;
		// that way other apps listen for that event, knowing that it's only fired once
		// the other app is fully loaded.
		// I'd use a bare CustomEvent with the same event type everywhere, and the
		// module name in the details so every app can discriminate on the events for
		// the one module they're waiting for:
		// https://developer.mozilla.org/en-US/docs/Web/API/CustomEvent/CustomEvent

		Calculator calculator = new Calculator("From Order");

		OrderView orderView = new OrderView(calculator);

		DomGlobal.document.getElementById("orderContainer").appendChild(orderView.element());
	}
}
