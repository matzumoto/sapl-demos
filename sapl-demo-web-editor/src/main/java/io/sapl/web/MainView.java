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
package io.sapl.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import io.sapl.web.views.javabasedview.JsonEditorView;
import io.sapl.web.views.javabasedview.SaplEditorView;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends AppLayout {

	private final Tabs menu;

	public MainView() {
		menu = createMenuTabs();
		addToNavbar(menu);
	}

	private static Tabs createMenuTabs() {
		final Tabs tabs = new Tabs();
		tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
		tabs.add(getAvailableTabs());
		return tabs;
	}

	private static Tab[] getAvailableTabs() {
		final List<Tab> tabs = new ArrayList<>();
		tabs.add(createTab("SAPL Editor Demo", SaplEditorView.class));
		tabs.add(createTab("JSON Editor Demo", JsonEditorView.class));
		return tabs.toArray(new Tab[tabs.size()]);
	}

	private static Tab createTab(String title, Class<? extends Component> viewClass) {
		RouterLink routerLink = new RouterLink(null, viewClass);
		routerLink.add(title);
		return createTab(routerLink);
	}

	private static Tab createTab(Component content) {
		final Tab tab = new Tab();
		tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		tab.add(content);
		return tab;
	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		selectTab();
	}

	private void selectTab() {
		String target = RouteConfiguration.forSessionScope().getUrl(getContent().getClass());
		Optional<Component> tabToSelect = menu.getChildren().filter(tab -> {
			Component child = tab.getChildren().findFirst().get();
			return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
		}).findFirst();
		tabToSelect.ifPresent(tab -> menu.setSelectedTab((Tab) tab));
	}

}
