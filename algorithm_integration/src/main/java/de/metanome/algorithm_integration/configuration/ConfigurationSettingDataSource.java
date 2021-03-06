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

package de.metanome.algorithm_integration.configuration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;


/**
 * InputParameters correspond to a ConfigurationSpecification and ConfigurationValue type. It is
 * used for frontend input of the configuration value, so generally, a ConfigurationSpecification
 * will be converted to an InputParameter, which is used to get the user's value input, and then
 * converted to the ConfigurationValue handed back to the algorithm.
 *
 * @author Claudia Exeler
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ConfigurationSettingFileInput.class, name = "ConfigurationSettingFileInput"),
    @JsonSubTypes.Type(value = ConfigurationSettingTableInput.class, name = "ConfigurationSettingTableInput"),
    @JsonSubTypes.Type(value = ConfigurationSettingDatabaseConnection.class, name = "ConfigurationSettingDatabaseConnection")
})
public interface ConfigurationSettingDataSource extends ConfigurationSetting, Serializable {

  public abstract String getValueAsString();

}
