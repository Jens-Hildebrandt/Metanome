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

package de.uni_potsdam.hpi.metanome.configuration;

import de.uni_potsdam.hpi.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.uni_potsdam.hpi.metanome.algorithm_integration.algorithm_types.EnumParameterAlgorithm;
import de.uni_potsdam.hpi.metanome.algorithm_integration.configuration.ConfigurationSpecificationEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConfigurationValueEnumTest {

	/**
	 * Test method for {@link ConfigurationValueEnum#triggerSetValue(de.uni_potsdam.hpi.metanome.algorithm_integration.Algorithm, java.util.Set)}
	 * <p/>
	 * Parameters should be set on the algorithm through triggerSetValue. This is the last call in a double
	 * dispatch call to determine the parameters type.
	 *
	 * @throws de.uni_potsdam.hpi.metanome.algorithm_integration.AlgorithmConfigurationException
	 */
	@Test
	public void testTriggerSetValue() throws AlgorithmConfigurationException {
		// Setup
		EnumParameterAlgorithm algorithm = mock(EnumParameterAlgorithm.class);
		Set<Class<?>> interfaces = new HashSet<>();
		interfaces.add(EnumParameterAlgorithm.class);
		// Expected values
		String expectedIdentifier = "configId1";
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("first");
		list1.add("second");
		ArrayList<String> list2 = new ArrayList<>();
		list1.add("first");
		ArrayList<String>[] expectedConfigurationValue = (ArrayList<String>[]) new ArrayList[2];
		expectedConfigurationValue[0] = list1;
		expectedConfigurationValue[1] = list2;

		// Execute functionality
		ConfigurationValueEnum configValue = new ConfigurationValueEnum(
				new ConfigurationSpecificationEnum(expectedIdentifier).getIdentifier(), expectedConfigurationValue);
		configValue.triggerSetValue(algorithm, interfaces);

		// Check result
		verify(algorithm).setConfigurationValue(expectedIdentifier, expectedConfigurationValue);
	}

}