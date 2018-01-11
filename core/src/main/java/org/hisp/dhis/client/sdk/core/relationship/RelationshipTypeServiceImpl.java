/*
 * Copyright (c) 2016, University of Oslo
 *
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.hisp.dhis.client.sdk.core.relationship;

import org.hisp.dhis.client.sdk.core.common.persistence.IdentifiableObjectStore;
import org.hisp.dhis.client.sdk.models.relationship.RelationshipType;
import org.hisp.dhis.client.sdk.utils.Preconditions;

import java.util.List;

public class RelationshipTypeServiceImpl implements RelationshipTypeService {
    private IdentifiableObjectStore<RelationshipType> relationshipTypeStore;

    public RelationshipTypeServiceImpl(IdentifiableObjectStore<RelationshipType>
                                           relationshipTypeStore) {
        this.relationshipTypeStore = relationshipTypeStore;
    }

    @Override
    public RelationshipType get(long id) {
        return relationshipTypeStore.queryById(id);
    }

    @Override
    public RelationshipType get(String uid) {
        return relationshipTypeStore.queryByUid(uid);
    }

    @Override
    public List<RelationshipType> list() {
        return relationshipTypeStore.queryAll();
    }

    @Override
    public boolean remove(RelationshipType object) {
        Preconditions.isNull(object, "Object must not be null");
        return relationshipTypeStore.delete(object);
    }

    @Override
    public boolean save(RelationshipType object) {
        Preconditions.isNull(object, "Object must not be null");
        return relationshipTypeStore.save(object);
    }
}
