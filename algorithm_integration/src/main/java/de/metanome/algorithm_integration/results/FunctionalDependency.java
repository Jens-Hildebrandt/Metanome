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

package de.metanome.algorithm_integration.results;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.metanome.algorithm_integration.ColumnCombination;
import de.metanome.algorithm_integration.ColumnIdentifier;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a functional dependency.
 *
 * @author Jakob Zwiener
 */
@JsonTypeName("FunctionalDependency")
public class FunctionalDependency implements Result {

  public static final String FD_SEPARATOR = "-->";

  protected ColumnCombination determinant;
  protected ColumnIdentifier dependant;

  /**
   * Exists for GWT serialization.
   */
  protected FunctionalDependency() {
    this.determinant = new ColumnCombination();
    this.dependant = new ColumnIdentifier("", "");
  }

  public FunctionalDependency(ColumnCombination determinant,
                              ColumnIdentifier dependant) {
    this.determinant = determinant;
    this.dependant = dependant;
  }

  public static FunctionalDependency fromString(String str) {
    String splitMarker = "#####";
    str = str.replace(FD_SEPARATOR, splitMarker);
    String[] parts = str.split(splitMarker);
    ColumnCombination determinant = ColumnCombination.fromString(parts[0].trim());
    ColumnIdentifier dependant = ColumnIdentifier.fromString(parts[1].trim());
    return new FunctionalDependency(determinant, dependant);
  }

  /**
   * @return determinant
   */
  public ColumnCombination getDeterminant() {
    return determinant;
  }

  public void setDependant(ColumnIdentifier dependant) {
    this.dependant = dependant;
  }

  /**
   * @return dependant
   */
  public ColumnIdentifier getDependant() {
    return dependant;
  }

  public void setDeterminant(ColumnCombination determinant) {
    this.determinant = determinant;
  }

  @Override
  @XmlTransient
  public void sendResultTo(OmniscientResultReceiver resultReceiver)
      throws CouldNotReceiveResultException {
    resultReceiver.receiveResult(this);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder
        .append(determinant)
        .append(FD_SEPARATOR)
        .append(dependant);

    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
             + ((dependant == null) ? 0 : dependant.hashCode());
    result = prime * result
             + ((determinant == null) ? 0 : determinant.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    FunctionalDependency other = (FunctionalDependency) obj;
    if (dependant == null) {
      if (other.dependant != null) {
        return false;
      }
    } else if (!dependant.equals(other.dependant)) {
      return false;
    }
    if (determinant == null) {
      if (other.determinant != null) {
        return false;
      }
    } else if (!determinant.equals(other.determinant)) {
      return false;
    }
    return true;
  }
}
