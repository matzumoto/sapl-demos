/*
 * Copyright © 2019-2021 Dominic Heutelbeck (dominic@heutelbeck.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sapl.playground.views;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.UIScope;

import io.sapl.playground.views.content.ContentView;

@Component
@UIScope
public class ExampleSelectedViewBus {

	private ContentView contentView;

	public ExampleSelectedViewBus() {
	}

	public ContentView getContentView() {
		return this.contentView;
	}

	public void setContentView(ContentView contentView) {
		this.contentView = contentView;
	}

}
