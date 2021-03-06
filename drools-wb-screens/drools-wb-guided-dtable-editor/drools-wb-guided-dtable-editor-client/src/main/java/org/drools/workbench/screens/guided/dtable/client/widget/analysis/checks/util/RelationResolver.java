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

package org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util;

import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.util.maps.InspectorList;

public class RelationResolver {

    private final ConflictResolver    conflictResolver;
    private final SubsumptionResolver subsumptionResolver;

    private final InspectorList list;

    public RelationResolver( final InspectorList list ) {
        this( list,
              false );
    }

    public RelationResolver( final InspectorList list,
                             final boolean record ) {
        this.list = list;
        conflictResolver = new ConflictResolver( list,
                                                 record );
        subsumptionResolver = new SubsumptionResolver( list,
                                                       record );
    }

    public boolean isConflicting( final InspectorList otherCollection ) {
        return resolveConflict( otherCollection ).foundIssue();
    }

    public Conflict resolveConflict( final InspectorList otherCollection ) {
        return conflictResolver.resolveConflict( otherCollection );
    }

    public boolean subsumes( final InspectorList otherList ) {

        if ( list == null || otherList == null ) {
            return false;
        }

        if ( isConflicting( otherList ) ) {
            return false;
        } else {
            return subsumptionResolver.listSubsumesOther( otherList );
        }
    }

    public boolean isRedundant( final InspectorList otherList ) {
        return subsumes( otherList )
                && otherList.subsumes( list );
    }

}
