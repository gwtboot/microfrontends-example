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

import static com.example.order.client.ui.OrderClientBundle.CONSTANTS;

import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.utils.DominoElement;

import com.example.order.client.ui.OrderView;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;

public class OrderApp {

    public void run() {
        Layout layout = Layout.create(CONSTANTS.appTitle())
                .removeLeftPanel()
                .show(ColorScheme.GREEN);

        OrderView orderView = new OrderView();

        DominoElement<HTMLDivElement> view = layout.getContentPanel()
                .appendChild(orderView.element());
        
        DomGlobal.document.getElementById("platformContainer").appendChild(view.element());
    }
}
