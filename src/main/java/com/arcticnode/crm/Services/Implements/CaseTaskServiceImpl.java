package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.CaseStatus;
import com.arcticnode.crm.Entities.CaseTasksEntity;
import com.arcticnode.crm.Entities.TaskStatus;
import com.arcticnode.crm.Repository.ICaseTaskRepository;
import com.arcticnode.crm.Services.ICaseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaseTaskServiceImpl implements ICaseTaskService {

    @Autowired
    private ICaseTaskRepository taskRepository;

    @Override
    public void saveTask(CaseTasksEntity task) {
        taskRepository.save(task);
    }

    @Override
    public Optional<CaseTasksEntity> findById(int taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public List<CaseTasksEntity> findByAuthId(int authId) {
        return taskRepository.findByAuthId(authId).stream()
                .filter(caseTasks ->
                        caseTasks.getTask_status() == TaskStatus.EN_PROGRESO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CaseTasksEntity> findByCaseId(int caseId) {
        return taskRepository.findByCaseId(caseId);
    }

    @Override
    public Page<CaseTasksEntity> findByCaseTasksStatusIn(List<TaskStatus> statuses, Pageable pageable) {
        return taskRepository.findByCaseTasksStatusIn(statuses, pageable);
    }
    //faltan tareas agregar metodo para tareas sin asignar o en progreso o finalizadas
}
