/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 *
 */

package org.opensearch.ml.action.training;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

import org.opensearch.action.ActionListener;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.HandledTransportAction;
import org.opensearch.common.inject.Inject;
import org.opensearch.ml.common.transport.training.MLTrainingTaskRequest;
import org.opensearch.ml.common.transport.training.MLTrainingTaskResponse;
import org.opensearch.ml.task.MLTrainingTaskRunner;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;

@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MLTrainingTaskExecutionTransportAction extends HandledTransportAction<MLTrainingTaskRequest, MLTrainingTaskResponse> {
    MLTrainingTaskRunner mlTrainingTaskRunner;

    @Inject
    public MLTrainingTaskExecutionTransportAction(
        TransportService transportService,
        ActionFilters actionFilters,
        MLTrainingTaskRunner mlTrainingTaskRunner
    ) {
        super(MLTrainingTaskExecutionAction.NAME, transportService, actionFilters, MLTrainingTaskRequest::new);
        this.mlTrainingTaskRunner = mlTrainingTaskRunner;
    }

    @Override
    protected void doExecute(Task task, MLTrainingTaskRequest request, ActionListener<MLTrainingTaskResponse> listener) {
        mlTrainingTaskRunner.startTrainingTask(request, listener);
    }
}