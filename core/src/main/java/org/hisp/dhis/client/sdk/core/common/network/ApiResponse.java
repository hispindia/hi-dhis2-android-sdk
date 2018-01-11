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

package org.hisp.dhis.client.sdk.core.common.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hisp.dhis.client.sdk.models.common.importsummary.ImportCount;
import org.hisp.dhis.client.sdk.models.common.importsummary.ImportSummary;
import org.hisp.dhis.client.sdk.models.common.importsummary.ImportSummary.Status;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    @JsonProperty("responseType")
    private String responseType;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("importCount")
    private ImportCount importCount;

    @JsonProperty("importSummaries")
    private List<ImportSummary> importSummaries;

    public ApiResponse() {
        // explicit empty constructor
    }

    public String getResponseType() {
        return responseType;
    }

    public Status getStatus() {
        return status;
    }

    public ImportCount getImportCount() {
        return importCount;
    }

    public List<ImportSummary> getImportSummaries() {
        return importSummaries;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "responseType='" + responseType + '\'' +
                ", status=" + status +
                ", importCount=" + importCount +
                ", importSummaries=" + importSummaries +
                '}';
    }
}
