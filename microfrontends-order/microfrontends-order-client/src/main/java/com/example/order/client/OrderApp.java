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
		Calculator calculator = new Calculator();
		OrderView orderView = new OrderView(calculator);

		DomGlobal.document.getElementById("orderContainer").appendChild(orderView.element());
	}
}
