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

import com.example.platform.client.ui.PlatformView;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;

import elemental2.dom.DomGlobal;

public class PlatformApp {

	private static Logger logger = Logger.getLogger(PlatformApp.class.getName());

	private static String ORDER_JS = "http://localhost:9999/order/order.nocache.js";

	private static String SUPPORT_JS = "http://localhost:7777/support/support.nocache.js";

	public void run() {
		injectScript(ORDER_JS);
	}

	private void initLayout() {
		Layout layout = Layout.create(CONSTANTS.appTitle()).removeLeftPanel().show(ColorScheme.BLUE);

		PlatformView platformView = new PlatformView();

		layout.getContentPanel().appendChild(platformView.element());

		DomGlobal.document.getElementById("platformContainer").appendChild(layout.element());
	}

	private void injectScript(String scriptUrl) {
		ScriptInjector.fromUrl(scriptUrl).setCallback(new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				logger.info("Script load failed: " + scriptUrl);
			}

			public void onSuccess(Void result) {
				logger.info("Script load success: " + scriptUrl);
				if (scriptUrl.equals(SUPPORT_JS)) {
					// End this call
					initLayout();
				} else {
					// Next JS injection
					injectScript(SUPPORT_JS);
				}
			}
		}).setWindow(ScriptInjector.TOP_WINDOW).inject();
	}
}
