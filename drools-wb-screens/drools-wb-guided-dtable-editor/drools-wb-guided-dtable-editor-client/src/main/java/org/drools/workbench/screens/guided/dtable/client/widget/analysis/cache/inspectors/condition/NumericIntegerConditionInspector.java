/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.inspectors.condition;

import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.Operator;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.FieldCondition;

public class NumericIntegerConditionInspector
        extends ComparableConditionInspector<Integer> {

    public NumericIntegerConditionInspector( final FieldCondition<Integer> fieldCondition ) {
        super( fieldCondition );
    }

    @Override
    public boolean subsumes( Object other ) {
        if ( other instanceof NumericIntegerConditionInspector ) {
            final NumericIntegerConditionInspector anotherPoint = ( NumericIntegerConditionInspector ) other;
            if ( anotherPoint != null ) {
                if ( (anotherPoint.getOperator().equals( Operator.LESS_THAN ) && operator.equals( Operator.LESS_OR_EQUAL )) ) {
                    return covers( anotherPoint.getValue() - 1 );
                } else if ( (anotherPoint.getOperator().equals( Operator.GREATER_OR_EQUAL ) && operator.equals( Operator.GREATER_THAN )) ) {
                    if ( getValue().equals( anotherPoint.getValue() - 1 ) ) {
                        return true;
                    }
                } else if ( (anotherPoint.getOperator().equals( Operator.GREATER_THAN ) && operator.equals( Operator.GREATER_OR_EQUAL )) ) {
                    return covers( anotherPoint.getValue() + 1 );
                } else if ( (anotherPoint.getOperator().equals( Operator.LESS_OR_EQUAL ) && operator.equals( Operator.LESS_THAN )) ) {
                    if ( getValue().equals( anotherPoint.getValue() + 1 ) ) {
                        return true;
                    }
                }
            }
        }

        return super.subsumes( other );
    }

}
