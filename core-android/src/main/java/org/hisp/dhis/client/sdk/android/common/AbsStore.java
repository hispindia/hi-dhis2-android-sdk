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

package org.hisp.dhis.client.sdk.android.common;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.hisp.dhis.client.sdk.android.api.persistence.flow.BaseModelFlow;
import org.hisp.dhis.client.sdk.core.common.persistence.Store;
import org.hisp.dhis.client.sdk.models.common.base.Model;

import java.util.List;

import static org.hisp.dhis.client.sdk.utils.Preconditions.isNull;


public abstract class AbsStore<ModelType extends Model,
        DatabaseEntityType extends com.raizlabs.android.dbflow.structure.Model & Model> implements Store<ModelType> {
    private final Mapper<ModelType, DatabaseEntityType> mapper;

    public AbsStore(Mapper<ModelType, DatabaseEntityType> mapper) {
        this.mapper = isNull(mapper, "mapper object must not be null");
    }

    @Override
    public boolean insert(ModelType object) {
        isNull(object, "object must not be null");

        DatabaseEntityType databaseEntity = mapper.mapToDatabaseEntity(object);
        if (databaseEntity != null) {
            databaseEntity.insert();

            /* setting id which DbFlows' BaseModel generated after insertion */
            object.setId(databaseEntity.getId());
            return true;
        }

        return false;
    }

    @Override
    public boolean update(ModelType object) {
        isNull(object, "object must not be null");

        DatabaseEntityType databaseEntity = mapper.mapToDatabaseEntity(object);
        if (databaseEntity != null) {
            databaseEntity.update();
            return true;
        }

        return false;
    }

    @Override
    public boolean save(ModelType object) {
        isNull(object, "object must not be null");

        DatabaseEntityType databaseEntity = mapper.mapToDatabaseEntity(object);
        if (databaseEntity != null) {
            databaseEntity.save();

            /* setting id which DbFlows' BaseModel generated after insertion */
            object.setId(databaseEntity.getId());
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(ModelType object) {
        isNull(object, "object must not be null");

        DatabaseEntityType databaseEntity = mapper.mapToDatabaseEntity(object);
        if (databaseEntity != null) {
            databaseEntity.delete();
            return true;
        }

        return false;
    }

    @Override
    public ModelType queryById(long id) {
        DatabaseEntityType databaseEntity = new Select()
                .from(mapper.getDatabaseEntityTypeClass())
                .where(Condition.column(NameAlias
                        .builder(BaseModelFlow.COLUMN_ID).build()).is(id))
                .querySingle();
        return mapper.mapToModel(databaseEntity);
    }

    @Override
    public List<ModelType> queryAll() {
        List<DatabaseEntityType> databaseEntities = new Select()
                .from(mapper.getDatabaseEntityTypeClass())
                .queryList();
        return mapper.mapToModels(databaseEntities);
    }

    @Override
    public boolean deleteAll() {
        Delete.tables(mapper.getDatabaseEntityTypeClass());
        return true;
    }

    protected Mapper<ModelType, DatabaseEntityType> getMapper() {
        return mapper;
    }
}
