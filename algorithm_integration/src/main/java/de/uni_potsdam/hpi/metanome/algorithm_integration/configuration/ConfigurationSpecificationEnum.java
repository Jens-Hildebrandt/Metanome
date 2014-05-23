/*
 * Copyright 2014 by the Metanome project
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

package de.uni_potsdam.hpi.metanome.algorithm_integration.configuration;


/**
 * Concrete {@link ConfigurationSpecification} for list of strings (enum).
 *
 * @author Tanja Bergmann
 * @see ConfigurationSpecification
 */
public class ConfigurationSpecificationEnum extends ConfigurationSpecification {

	private ConfigurationSettingEnum[] settings;

	/**
	 * Construct a ConfigurationSpecificationEnum, requesting 1 value.
	 *
	 * @param identifier the specification's identifier
	 */
	public ConfigurationSpecificationEnum(String identifier) {
		super(identifier);
	}


	/**
	 * Constructs a {@link ConfigurationSpecificationEnum}, potentially requesting several values.
	 *
	 * @param identifier     the specification's identifier
	 * @param numberOfValues the number of values expected
	 */
	public ConfigurationSpecificationEnum(String identifier,
										  int numberOfValues) {

		super(identifier, numberOfValues);
	}

	@Override
	public ConfigurationSettingEnum[] getSettings() {
		return this.settings;
	}

	public void setSettings(ConfigurationSettingEnum... settings) {
		this.settings = settings;
	}
}