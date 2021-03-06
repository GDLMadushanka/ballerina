/*
 *  Copyright (c) 2017 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.ballerinalang.nativeimpl.task.stop;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.BLangVMErrors;
import org.ballerinalang.bre.bvm.BlockingNativeCallableUnit;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.nativeimpl.task.TaskRegistry;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;

/**
 * Native function ballerina.model.task:stopTask.
 */
@BallerinaFunction(
        packageName = "ballerina.task",
        functionName = "stopTask",
        args = {@Argument(name = "taskID", type = TypeKind.STRING)},
        returnType = {@ReturnType(type = TypeKind.STRUCT)},
        isPublic = true
)
public class BalStopTask extends BlockingNativeCallableUnit {

    public void execute(Context context) {
        String taskId = context.getStringArgument(0);
        try {
            TaskRegistry.getInstance().stopTask(taskId);
        } catch (Exception e) {
            context.setReturnValues(BLangVMErrors.createError(context, 0, e.getMessage()));
        }
        context.setReturnValues();
    }
}

