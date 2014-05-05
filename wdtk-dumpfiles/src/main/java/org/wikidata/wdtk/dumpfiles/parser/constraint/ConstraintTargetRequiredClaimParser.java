package org.wikidata.wdtk.dumpfiles.parser.constraint;

/*
 * #%L
 * Wikidata Toolkit Dump File Handling
 * %%
 * Copyright (C) 2014 Wikidata Toolkit Developers
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.wikidata.wdtk.datamodel.implementation.DataObjectFactoryImpl;
import org.wikidata.wdtk.datamodel.interfaces.ItemIdValue;
import org.wikidata.wdtk.datamodel.interfaces.PropertyIdValue;
import org.wikidata.wdtk.dumpfiles.constraint.ConstraintTargetRequiredClaim;
import org.wikidata.wdtk.dumpfiles.parser.template.Template;

/**
 * 
 * @author Julian Mendez
 * 
 */
class ConstraintTargetRequiredClaimParser implements ConstraintParser {

	public ConstraintTargetRequiredClaimParser() {
	}

	public ConstraintTargetRequiredClaim parse(Template template) {
		ConstraintTargetRequiredClaim ret = null;
		String page = template.getPage();
		String propertyStr = template.get(ConstraintParserConstant.P_PROPERTY);
		if (page != null && propertyStr != null) {
			DataObjectFactoryImpl factory = new DataObjectFactoryImpl();
			PropertyIdValue constrainedProperty = factory.getPropertyIdValue(
					page, ConstraintMainParser.DEFAULT_BASE_IRI);
			PropertyIdValue property = factory.getPropertyIdValue(propertyStr,
					ConstraintMainParser.DEFAULT_BASE_IRI);
			String itemStr = template.get(ConstraintParserConstant.P_ITEM);
			if (itemStr != null) {
				ItemIdValue item = factory.getItemIdValue(itemStr,
						ConstraintMainParser.DEFAULT_BASE_IRI);
				ret = new ConstraintTargetRequiredClaim(constrainedProperty,
						property, item);
			} else {
				ret = new ConstraintTargetRequiredClaim(constrainedProperty,
						property);
			}
		}
		return ret;
	}

}
