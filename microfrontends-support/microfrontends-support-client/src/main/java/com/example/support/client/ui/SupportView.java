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

import static com.example.support.client.ui.SupportClientBundle.BUNDLE;
import static com.example.support.client.ui.SupportClientBundle.CONSTANTS;
import static org.jboss.elemento.Elements.div;

import java.util.logging.Logger;

import com.example.support.client.ui.TodoItem.Priority;

import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.FieldsGrouping;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.StyleType;
import org.dominokit.domino.ui.style.Styles;
import org.jboss.elemento.IsElement;

import elemental2.dom.CustomEvent;
import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.HTMLDivElement;

public class SupportView implements IsElement<HTMLDivElement> {

	private static Logger logger = Logger.getLogger(SupportView.class.getName());

	private static final String BUTTON_CHANGED_EVENT = "buttonChangedEvent";

	private HTMLDivElement root = div().css(BUNDLE.css().contentMargin()).element();

	private TextBox titleTextBox;

	private TextArea descriptionTextArea;

	private ListGroup<TodoItem> todoItemsListGroup;

	private ListGroup<TodoItem> doneItemsListGroup;

	private Select<Priority> prioritySelect;

	private Button addButton;

	private FieldsGrouping fieldsGrouping;

	private int countButton = 0;

	public SupportView() {
		logger.info("Create SupportView");

		DomGlobal.document.addEventListener(BUTTON_CHANGED_EVENT, event -> handleButtonChangedEvent(event));

		fieldsGrouping = FieldsGrouping.create();

		this.titleTextBox = TextBox.create(CONSTANTS.title()).groupBy(fieldsGrouping).setRequired(true)
				.setAutoValidation(true);

		this.descriptionTextArea = TextArea.create(CONSTANTS.description()).groupBy(fieldsGrouping).setRequired(true)
				.setAutoValidation(true).setRows(1);

		this.prioritySelect = Select.ofEnum(CONSTANTS.priority(), Priority.values()).groupBy(fieldsGrouping)
				.setRequired(true).setAutoValidation(true);

		this.todoItemsListGroup = ListGroup.<TodoItem>create().setSelectable(false)
				.setItemRenderer((listGroup, listItem) -> {
					listItem.css(Styles.padding_10)
							.appendChild(
									FlexLayout
											.create().setJustifyContent(
													FlexJustifyContent.SPACE_AROUND)
											.appendChild(
													FlexItem.create().setFlexGrow(1)
															.appendChild(
																	BlockHeader
																			.create(listItem.getValue().getTitle(),
																					listItem.getValue()
																							.getDescription())
																			.css(Styles.m_b_0)))
											.appendChild(FlexItem.create()
													.css(Styles.m_l_10, Styles.m_r_10, Styles.m_t_10)
													.appendChild(priorityBadge(listItem.getValue().getPriority())))
											.appendChild(FlexItem.create()
													.appendChild(Icons.ALL.check_bold_mdi().setColor(Color.GREEN)
															.clickable().addClickListener(
																	evt -> handleCheckOkClick(listItem.getValue())))));
				});

		this.doneItemsListGroup = ListGroup.<TodoItem>create().setSelectable(false)
				.setItemRenderer((listGroup, listItem) -> {
					listItem.css(Styles.padding_10)
							.appendChild(
									FlexLayout
											.create().setJustifyContent(
													FlexJustifyContent.SPACE_AROUND)
											.appendChild(
													FlexItem.create().setFlexGrow(1)
															.appendChild(
																	BlockHeader
																			.create(listItem.getValue().getTitle(),
																					listItem.getValue()
																							.getDescription())
																			.css(Styles.m_b_0)))
											.appendChild(FlexItem.create()
													.css(Styles.m_l_10, Styles.m_r_10, Styles.m_t_10)
													.appendChild(priorityBadge(listItem.getValue().getPriority()))));
				});

		this.addButton = Button.createPrimary(CONSTANTS.add()).styler(style -> style.add(BUNDLE.css().addButton()))
				.addClickListener(evt -> handleAddButtonClick());

		root.appendChild(Card.create(CONSTANTS.new_todo(), CONSTANTS.add_new_todo()).setHeaderBackground(Color.AMBER)
				.appendChild(titleTextBox).appendChild(descriptionTextArea).appendChild(prioritySelect)
				.appendChild(addButton).element());

		root.appendChild(Card.create(CONSTANTS.todo_items()).setHeaderBackground(Color.AMBER)
				.appendChild(todoItemsListGroup).element());

		root.appendChild(Card.create(CONSTANTS.done_items()).setHeaderBackground(Color.AMBER)
				.appendChild(doneItemsListGroup).element());
	}

	@SuppressWarnings("unchecked")
	private void handleButtonChangedEvent(Event event) {
		logger.info("Handle Button ChangedEvent");

		String detail = ((CustomEvent<String>) event).detail;
		logger.info("CustomEvent detail: " + detail);

		countButton++;
		addButton.setTextContent("After buttonChangedEvent" + " - " + detail + " - " + countButton);
		addButton.setButtonType(StyleType.DANGER);
	}

	void handleAddButtonClick() {
		if (fieldsGrouping.validate().isValid()) {
			TodoItem todoItem = new TodoItem(titleTextBox.getValue(), descriptionTextArea.getValue(),
					prioritySelect.getValue());

			todoItemsListGroup.addItem(todoItem);

			fieldsGrouping.clear().clearInvalid();
		}
	}

	private Badge priorityBadge(Priority priority) {
		switch (priority) {
		case High:
			return Badge.create("High").setBackground(Color.RED);
		case Medium:
			return Badge.create("Medium").setBackground(Color.ORANGE);
		case Low:
		default:
			return Badge.create("Low").setBackground(Color.TEAL);
		}

	}

	void handleCheckOkClick(TodoItem todoItem) {
		todoItemsListGroup.removeItem(todoItem);
		doneItemsListGroup.addItem(todoItem);
	}

	@Override
	public HTMLDivElement element() {
		return root;
	}
}
