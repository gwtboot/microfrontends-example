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
package com.example.support.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface SupportClientBundle extends ClientBundle {

    SupportClientBundle BUNDLE = GWT.create(SupportClientBundle.class);

    SupportConstants CONSTANTS = GWT.create(SupportConstants.class);

    interface SupportConstants extends Constants {
        @DefaultStringValue("Support")
        String appTitle();

        @DefaultStringValue("Support Title")
        String title();

        @DefaultStringValue("Support Description")
        String description();

        @DefaultStringValue("Support Priority")
        String priority();

        @DefaultStringValue("Support Add")
        String add();

        @DefaultStringValue("Support Mark Done")
        String mark_done();

        @DefaultStringValue("Support New Todo")
        String new_todo();

        @DefaultStringValue("Support Add a new todo list item")
        String add_new_todo();

        @DefaultStringValue("Support Todo Items")
        String todo_items();

        @DefaultStringValue("Support Done Items")
        String done_items();
    }

    interface SupportCssResource extends CssResource {
        String addButton();

        String doneButton();

        String contentMargin();
    }

    @Source("support.gss")
    SupportCssResource css();
}
