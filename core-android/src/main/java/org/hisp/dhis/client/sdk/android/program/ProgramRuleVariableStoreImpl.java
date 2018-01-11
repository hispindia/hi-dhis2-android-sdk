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

package org.hisp.dhis.client.sdk.android.program;


import com.raizlabs.android.dbflow.sql.language.Select;

import org.hisp.dhis.client.sdk.android.api.persistence.flow.ProgramRuleVariableFlow;
import org.hisp.dhis.client.sdk.android.api.persistence.flow.ProgramRuleVariableFlow_Table;
import org.hisp.dhis.client.sdk.android.common.AbsIdentifiableObjectStore;
import org.hisp.dhis.client.sdk.core.common.utils.ModelUtils;
import org.hisp.dhis.client.sdk.core.program.ProgramRuleVariableStore;
import org.hisp.dhis.client.sdk.models.dataelement.DataElement;
import org.hisp.dhis.client.sdk.models.program.Program;
import org.hisp.dhis.client.sdk.models.program.ProgramRuleVariable;

import java.util.List;
import java.util.Set;

public final class ProgramRuleVariableStoreImpl
        extends AbsIdentifiableObjectStore<ProgramRuleVariable, ProgramRuleVariableFlow>
        implements ProgramRuleVariableStore {

    public ProgramRuleVariableStoreImpl() {
        super(ProgramRuleVariableFlow.MAPPER);
    }

    @Override
    public ProgramRuleVariable query(Program program, DataElement dataElement) {
        List<ProgramRuleVariableFlow> programRuleVariableFlow = new Select()
                .from(ProgramRuleVariableFlow.class)
                .where(ProgramRuleVariableFlow_Table
                        .program.is(program.getUId()))
                .and(ProgramRuleVariableFlow_Table
                        .dataElement.is(dataElement.getUId()))
                .queryList();

        if (programRuleVariableFlow != null && !programRuleVariableFlow.isEmpty()) {
            return getMapper().mapToModel(programRuleVariableFlow.get(0));
        } else {
            return null;
        }
    }

    @Override
    public List<ProgramRuleVariable> query(Program program) {
        List<ProgramRuleVariableFlow> programRuleVariableFlow = new Select()
                .from(ProgramRuleVariableFlow.class)
                .where(ProgramRuleVariableFlow_Table
                        .program.is(program.getUId()))
                .queryList();

        return getMapper().mapToModels(programRuleVariableFlow);
    }

    @Override
    public List<ProgramRuleVariable> query(List<Program> programs) {
        Set<String> programUidSet = ModelUtils.toUidSet(programs);

        List<ProgramRuleVariableFlow> programRuleVariableFlow = new Select()
                .from(ProgramRuleVariableFlow.class)
                .where(ProgramRuleVariableFlow_Table
                        .program.in(programUidSet))
                .queryList();

        return getMapper().mapToModels(programRuleVariableFlow);
    }
}
