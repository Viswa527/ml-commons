/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

 package org.opensearch.ml.common.transport.controller;

import lombok.Getter;
import org.opensearch.action.support.nodes.BaseNodesRequest;
import org.opensearch.cluster.node.DiscoveryNode;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import java.io.IOException;

public class MLUndeployModelControllerNodesRequest extends BaseNodesRequest<MLUndeployModelControllerNodesRequest> {

    @Getter
    private String modelId;

    public MLUndeployModelControllerNodesRequest(StreamInput in) throws IOException {
        super(in);
        this.modelId = in.readString();
    }

    public MLUndeployModelControllerNodesRequest(String[] nodeIds, String modelId) {
        super(nodeIds);
        this.modelId = modelId;
    }

    public MLUndeployModelControllerNodesRequest(DiscoveryNode[] nodeIds, String modelId) {
        super(nodeIds);
        this.modelId = modelId;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(modelId);
    }
}
