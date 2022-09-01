package com.company.its.screen.task;

import io.jmix.ui.screen.*;
import com.company.its.entity.Task;

@UiController("its_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("tasksTable")
public class TaskBrowse extends StandardLookup<Task> {
}