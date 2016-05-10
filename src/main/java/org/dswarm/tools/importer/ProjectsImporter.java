/**
 * Copyright (C) 2016 SLUB Dresden (<code@dswarm.org>)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dswarm.tools.importer;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import org.dswarm.common.types.Tuple;
import org.dswarm.tools.DswarmToolsStatics;
import org.dswarm.tools.apiclients.DswarmProjectsAPIClient;
import org.dswarm.tools.utils.DswarmToolUtils;

/**
 * @author tgaengler
 */
public final class ProjectsImporter extends AbstractImporter<DswarmProjectsAPIClient> {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectsImporter.class);

	public ProjectsImporter(final String dswarmBackendAPIBaseURI) {

		super(new DswarmProjectsAPIClient(dswarmBackendAPIBaseURI), DswarmToolsStatics.PROJECT);
	}

	@Override
	protected Observable<Tuple<String, String>> executeImport(final Observable<Tuple<String, String>> importObjectTupleObservable) {

		return apiClient.importProjects(importObjectTupleObservable);
	}

	@Override
	protected JsonNode deserializeObject(final String importObjectJSONString, final String errorMessage) {

		return DswarmToolUtils.deserializeAsObjectNode(importObjectJSONString, errorMessage);
	}
}
