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

package org.hisp.dhis.client.sdk.android.api.network;

import org.hisp.dhis.client.sdk.core.common.network.Header;
import org.hisp.dhis.client.sdk.core.common.network.Response;

import java.io.IOException;
import java.util.List;

public final class ResponseMapper {

    private ResponseMapper() {
        // private constructor
    }

    public static Response fromRetrofitResponse(retrofit2.Response retrofitResponse) {
        if (retrofitResponse == null) {
            return null;
        }

        List<Header> headers = HeaderMapper.fromOkHeaders(retrofitResponse.headers());
        byte[] responseBody = new byte[0];

        try {
            if (retrofitResponse.isSuccessful()) {
                responseBody = retrofitResponse.raw().body().bytes();
            } else {
                responseBody = retrofitResponse.errorBody().bytes();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return new Response(retrofitResponse.raw().request().toString(),
                retrofitResponse.code(), retrofitResponse.message(),
                headers, responseBody);
    }
}
