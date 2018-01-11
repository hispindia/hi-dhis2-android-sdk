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

package org.hisp.dhis.client.sdk.core.program;


import org.hisp.dhis.client.sdk.models.program.Program;
import org.hisp.dhis.client.sdk.models.program.ProgramIndicator;
import org.hisp.dhis.client.sdk.models.program.ProgramStage;
import org.hisp.dhis.client.sdk.models.program.ProgramStageSection;
import org.hisp.dhis.client.sdk.utils.Preconditions;

import java.util.List;

public class ProgramIndicatorServiceImpl implements ProgramIndicatorService {
    private ProgramIndicatorStore programIndicatorStore;

    public ProgramIndicatorServiceImpl(ProgramIndicatorStore programIndicatorStore) {
        this.programIndicatorStore = programIndicatorStore;
    }

    @Override
    public ProgramIndicator get(long id) {
        return programIndicatorStore.queryById(id);
    }

    @Override
    public ProgramIndicator get(String uid) {
        return programIndicatorStore.queryByUid(uid);
    }

    @Override
    public List<ProgramIndicator> list() {
        return programIndicatorStore.queryAll();
    }

    @Override
    public boolean remove(ProgramIndicator object) {
        Preconditions.isNull(object, "Object must not be null");
        return programIndicatorStore.delete(object);
    }

    @Override
    public boolean save(ProgramIndicator object) {
        Preconditions.isNull(object, "Object must not be null");
        return programIndicatorStore.save(object);
    }

    @Override
    public List<ProgramIndicator> list(Program program) {
        Preconditions.isNull(program, "Object must not be null");
        return programIndicatorStore.query(program);
    }

    @Override
    public List<ProgramIndicator> list(ProgramStage programStage) {
        Preconditions.isNull(programStage, "Object must not be null");
        return programIndicatorStore.query(programStage);
    }

    @Override
    public List<ProgramIndicator> list(ProgramStageSection programStageSection) {
        Preconditions.isNull(programStageSection, "Object must not be null");
        return programIndicatorStore.query(programStageSection);
    }
}
